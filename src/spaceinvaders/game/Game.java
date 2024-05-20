package spaceinvaders.game;

import org.w3c.dom.Text;
import spaceinvaders.entity.Bullet;
import spaceinvaders.entity.Enemy;
import spaceinvaders.entity.Player;
import spaceinvaders.handler.InputHandler;
import spaceinvaders.main.SpaceInvaders;
import spaceinvaders.texture.Texture;

import java.awt.*;
import java.util.ArrayList;

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
        Texture texture = new Texture(20, 20);
        texture.setColor(java.awt.Color.RED);
        player = new Player(375, 400, 100, 1, texture);
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
        Texture t = new Texture(5, 5);
        t.setColor(Color.BLUE);
        return t;
    }
}
