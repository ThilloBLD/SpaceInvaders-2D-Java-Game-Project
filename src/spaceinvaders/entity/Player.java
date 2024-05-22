package spaceinvaders.entity;

import spaceinvaders.game.GameLayer;
import spaceinvaders.texture.Texture;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends LivingEntity {

    //Wann du das nächste Mal schießen darfst
    private long allowedShoot;
    public static final long shootDelay = 750;

    public Player(double x, double y, double health, double speed, Texture texture) {
        super(x, y, health, speed, texture);
        allowedShoot = 0;
    }

    public void moveLeft() {
        double newX = getX() - getSpeed();
        if (newX < 0) {
            newX = 0;
        }
        setX(newX);
    }

    public void moveRight(int mapWidth) {
        double newX = getX() + getSpeed();
        if((newX + getWidth()) > mapWidth) {
            newX = mapWidth - getWidth();
        }
        setX(newX);
    }

    @Override
    public void update(GameLayer game) {
        for(Bullet b: game.getBullets()) {
            if(collide(b)) {
                b.registerHit();
                damage(b.getDamage());
            }
        }

        if (isDead()){
            return;
        }

        if (game.isKeyPressed(KeyEvent.VK_RIGHT)) {
            moveRight(game.getLabelWidth());
        }
        if (game.isKeyPressed(KeyEvent.VK_LEFT)) {
            moveLeft();
        }
        if (game.isKeyPressed(KeyEvent.VK_SPACE)) {
            shootBullet(game);
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawString(getHealth() + "/" + getMaxHealth(), 10, 20);
    }

    private void shootBullet(GameLayer game) {
        if (System.currentTimeMillis() >= allowedShoot) {
            game.addBullet(new Bullet(getX() + getWidth() / 2, getY(), 1, true));
            allowedShoot = System.currentTimeMillis() + shootDelay;
        }
    }
}
