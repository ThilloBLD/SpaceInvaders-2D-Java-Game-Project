package spaceinvaders.entity;

import spaceinvaders.game.GameLayer;
import spaceinvaders.texture.AdvancedResourceManager;
import spaceinvaders.texture.AdvancedTextureType;
import spaceinvaders.texture.Theme;

public class Bullet extends Entity {

    private final int damage;
    private boolean hit = false;
    private boolean moveUp;

    public Bullet(double x, double y, double speed, boolean moveUp, Theme theme) {
        // schaut, ob die Textur nach oben oder nach Unten geschossen werden soll -> ? + : ist eine if abfrage 
        super(x, y, speed, AdvancedResourceManager.getTextureForType(moveUp ? AdvancedTextureType.bullet : AdvancedTextureType.reversedbullet, theme));
        damage = GameLayer.gameRandomizer.nextInt(15) + 1;
        this.moveUp = moveUp;
    }

    @Override
    public void update(GameLayer game) {
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

    public boolean isMovingUp() {
        return moveUp;
    }
}
