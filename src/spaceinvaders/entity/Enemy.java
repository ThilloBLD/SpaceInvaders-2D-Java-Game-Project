package spaceinvaders.entity;

import spaceinvaders.game.Game;
import spaceinvaders.texture.Texture;

public class Enemy extends LivingEntity {

    private final double minX;
    private final double maxX;

    public Enemy(double x, double y, double health, double speed, Texture texture) {
        super(x, y, health, speed, texture);
        minX = x - 30;
        maxX = x + 30;
    }

    @Override
    public void update(Game game) {
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
}
