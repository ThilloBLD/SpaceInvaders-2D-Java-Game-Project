package spaceinvaders.game;

import spaceinvaders.entity.Bullet;
import spaceinvaders.entity.Enemy;
import spaceinvaders.entity.Player;
import spaceinvaders.handler.InputHandler;
import spaceinvaders.main.SpaceInvaders;
import spaceinvaders.texture.Texture;

import java.awt.*;
import java.util.ArrayList;
import spaceinvaders.texture.ResourceManager;
import spaceinvaders.texture.TextureType;

public class Game {
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Bullet> removeBullets = new ArrayList<>();

    private InputHandler inputHandler;

    public Game(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        init();
    }

    private void init() {
        ResourceManager loadTextures = ResourceManager.getInstance() ;
        player = new Player(375, 400, 100, 1, loadTextures.getTexture(TextureType.player));
        spawnAllEnemys();
    }

    public synchronized void update() {
        for (Bullet bullet : bullets) {
            bullet.update(this);
        }
        if (!removeBullets.isEmpty()) {
            bullets.removeAll(removeBullets);
            removeBullets.clear();
        }
        player.update(this);
        for (Enemy enemy : enemies) {
            enemy.update(this);
        }
    }

    // Alles was gezeichnet wird
    public synchronized void draw(Graphics g) {
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        player.draw(g);
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    public boolean isKeyPressed(int keyCode) {
        return inputHandler.isKeyDown(keyCode);
    }

    public int getLabelWidth() {
        return SpaceInvaders.instance.frame.getLabelWidth();
    }

    public int getLabelHeight() {
        return SpaceInvaders.instance.frame.getLabelHeight();
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        removeBullets.add(bullet);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    private void checkGameEnding() {
        if (enemies.isEmpty()) {
            SpaceInvaders.instance.running = false;
        }
    }

    public static Texture getBulletTexture() {
        ResourceManager loadTextures = ResourceManager.getInstance() ;
        return loadTextures.getTexture(TextureType.bullet);
    }

    //Gegner hinzufügen, entfernen, etc.
    public void spawnAllEnemys() {
        ResourceManager loadTextures = ResourceManager.getInstance();
        double x = 160;
        double y = 75;
        Texture textureEnemy = loadTextures.getTexture(TextureType.enemy);
        for (int i = 0; i < 4; i++) {
            enemies.add(new Enemy(x, y, 50, 0.3, textureEnemy));
            x+= textureEnemy.getWidth() + 30;
            y+= textureEnemy.getHeight() + 10;
        }
    }
}