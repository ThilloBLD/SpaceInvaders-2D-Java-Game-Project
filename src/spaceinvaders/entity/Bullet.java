package spaceinvaders.entity;

import spaceinvaders.game.Game;
import spaceinvaders.texture.Texture;

public class Bullet extends Entity{

    private final int damage;
    private boolean hit = false;
    private boolean moveUp;

    public Bullet(double x, double y, double speed, Texture texture, boolean moveUp) {
        super(x, y, speed, texture);
        damage = Game.gamerandomizer.nextInt(15) + 1;
        this.moveUp = moveUp;
    }

    @Override
    public void update(Game game) {
        if (hit) {
            game.removeBullet(this);
            return;
        }
        if (getY() < 0) {
            game.removeBullet(this);
            return;
        }
        if (getY() > (game.getLabelHeight() - getHeight())) {
            game.removeBullet(this);
            return;
        }
        if (moveUp) {
            move(0, -getSpeed());
        } else {
            move(0, getSpeed());
        }
    }

    public int getDamage() {
        return damage;
    }

    public void registerHit() {
        if (!hit) {
            hit = true;
        }
    }
}
