package spaceinvaders.game;

import spaceinvaders.entity.Bullet;
import spaceinvaders.entity.Enemy;
import spaceinvaders.entity.Player;
import spaceinvaders.handler.InputHandler;
import spaceinvaders.main.SpaceInvaders;
import spaceinvaders.texture.Texture;
import spaceinvaders.texture.Theme;
import spaceinvaders.texture.AdvancedResourceManager;
import spaceinvaders.texture.AdvancedTextureType;
import spaceinvaders.texture.ResourceManager;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.util.Random;

public class GameLayer implements GameLabelLayer {
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Bullet> removeBullets = new ArrayList<>();
    private ArrayList<Enemy> removeEnemies = new ArrayList<>();
    private ImageIcon background;
    private Theme theme;
    public static final Random gameRandomizer = new Random();

    private InputHandler inputHandler;

    public GameLayer(InputHandler inputHandler, int backgroundID, Theme theme) {
        this.inputHandler = inputHandler;
        this.theme = theme;
        init(backgroundID);
    }

    private void init(int backgroundID) {
        background = ResourceManager.getImageIconByBackgroundID(backgroundID);
        player = new Player(375, 400, 100, 0.5, AdvancedResourceManager.getTextureForType(AdvancedTextureType.player, theme));
        spawnAllEnemys();
    }

    @Override
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
        if(!removeEnemies.isEmpty()) {
            enemies.removeAll(removeEnemies);
            removeEnemies.clear();
        }

        checkGameEnding();
    }

    // Alles was gezeichnet wird
    @Override
    public synchronized void draw(Graphics g) {
        g.drawImage(background.getImage(), 0, 0, null); // getLabelWidth(), getLabelHeight(),
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

    public synchronized void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        removeBullets.add(bullet);
    }

    public void removeEnemy(Enemy enemy) {
        removeEnemies.add(enemy);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    private void checkGameEnding() {
        if (enemies.isEmpty() || player.isDead()) {
            SpaceInvaders.instance.frame.label.changeGameLayer(new EndLayer(enemies.isEmpty(), inputHandler));
        }
    }

    //Gegner hinzufügen, entfernen, etc.
    public void spawnAllEnemys() {
        double x = 160;
        double y = 75;
        Texture textureEnemy = AdvancedResourceManager.getTextureForType(AdvancedTextureType.enemy, theme);
        for (int i = 0; i < 4; i++) {
            enemies.add(new Enemy(x, y, 50, 0.3, textureEnemy));
            x+= textureEnemy.getWidth() + 30;
            y+= textureEnemy.getHeight() + 10;
        }
    }

    public Theme getTheme() {
        return theme;
    }

}