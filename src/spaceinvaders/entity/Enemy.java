package spaceinvaders.entity;

import spaceinvaders.game.Game;
import spaceinvaders.texture.Texture;

public class Enemy extends LivingEntity {

    private final double minX;
    private final double maxX;
    private long allowedShoot;
    public static final long minShootDelay = 750;

    public Enemy(double x, double y, double health, double speed, Texture texture) {
        super(x, y, health, speed, texture);
        minX = x - 30;
        maxX = x + 30;
    }

    @Override
    public void update(Game game) {
        
        for(Bullet b: game.getBullets()) {
            if(collide(b)) {
                b.registerHit();
                damage(b.getDamage());
            }
        }

        if(isDead()) {
            game.removEnemies(this);
            return;
        }

        shootBullet(game);
        

        double newX = getX() + getSpeed();
        if(newX >= maxX) {
            newX = maxX;
            setSpeed(-getSpeed());
        }
        if (newX <= minX) {
            newX = minX;
            setSpeed(-getSpeed());
        }
        setX(newX);

        
        
    }

    public void shootBullet(Game game) {
        if (System.currentTimeMillis() >= allowedShoot) {
            game.addBullet(new Bullet(getX() + getWidth() / 2, getY() + getHeight(), 1, false));
            allowedShoot = System.currentTimeMillis() + Game.gamerandomizer.nextLong(750) + minShootDelay;
        }
    }
}
