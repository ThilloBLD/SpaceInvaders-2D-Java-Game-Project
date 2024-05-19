package spaceinvaders.entity;

import spaceinvaders.game.Game;
import spaceinvaders.texture.Texture;

public class Enemy extends LivingEntity {


    public Enemy(double x, double y, double health, double speed, Texture texture) {
        super(x, y, health, speed, texture);

    }

    @Override
    public void update(Game game) {

    }
}
