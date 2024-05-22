package spaceinvaders.entity;

import spaceinvaders.game.Game;
import spaceinvaders.texture.Texture;

public class Enemy extends LivingEntity {

    private long allowedShoot;
    public static final long minShootDelay = 750;

    public Enemy(double x, double y, double health, double speed, Texture texture) {
        super(x, y, health, speed, texture);
    }

    @Override
    public void update(Game game) {
        
        for(Bullet b: game.getBullets()) {
            if(collide(b)) {
                if(b.isMovingUp()) {
                    b.registerHit();
                    damage(b.getDamage());
                } else {
                    b.setY(b.getY() + b.getHeight() + getHeight());
                }
            }
        }

        if(isDead()) {
            game.removeEnemy(this);
            return;
        }

        shootBullet(game);

        double newX = getX() + getSpeed();
        if (newX <= 0) {
            newX = 0;
            setSpeed(- getSpeed());
        } else
        if ((newX + getWidth()) >= game.getLabelWidth()) {
            newX = (game.getLabelWidth() - getWidth());
            setSpeed(- getSpeed());
        } else {
            if (Game.gameRandomizer.nextInt(600) == 0) {
                setSpeed(- getSpeed());
            }
        }
        setX(newX);
    }

    public void shootBullet(Game game) {
        if (System.currentTimeMillis() >= allowedShoot) {
            game.addBullet(new Bullet(getX() + getWidth() / 2, getY() + getHeight(), 1, false));
            allowedShoot = System.currentTimeMillis() + Game.gameRandomizer.nextLong(750) + minShootDelay;
        }
    }
}
