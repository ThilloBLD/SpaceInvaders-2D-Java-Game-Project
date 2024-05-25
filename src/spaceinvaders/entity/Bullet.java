package spaceinvaders.entity;

import spaceinvaders.game.GameLayer;
import spaceinvaders.texture.AdvancedResourceManager;
import spaceinvaders.texture.AdvancedTextureType;
import spaceinvaders.texture.Theme;

public class Bullet extends Entity {

    private final int damage;
    private boolean hit = false;
    private boolean moveUp;

    // Konstruktor für das Bullet-Objekt
    public Bullet(double x, double y, double speed, boolean moveUp, Theme theme) {
        // Schaut, ob die Textur nach oben oder nach unten geschossen werden soll
        // ? + : ist eine if-Abfrage
        super(x, y, speed, AdvancedResourceManager.getTextureForType(moveUp ? AdvancedTextureType.bullet : AdvancedTextureType.reversedbullet, theme));
        damage = GameLayer.gameRandomizer.nextInt(15) + 1; // Zufälligen Schaden zwischen 1 und 15 setzen
        this.moveUp = moveUp; // Setzt die Richtung der Kugel
    }

    @Override
    public void update(GameLayer game) {
        if (hit) {
            game.removeBullet(this); // Entfernt die Kugel, wenn sie getroffen hat
            return;
        }
        if (getY() < 0) {
            game.removeBullet(this); // Entfernt die Kugel, wenn sie den oberen Rand erreicht
            return;
        }
        if (getY() > (game.getLabelHeight() - getHeight())) {
            game.removeBullet(this); // Entfernt die Kugel, wenn sie den unteren Rand erreicht
            return;
        }
        if (moveUp) {
            move(0, -getSpeed()); // Bewegt die Kugel nach oben
        } else {
            move(0, getSpeed()); // Bewegt die Kugel nach unten
        }
    }

    public int getDamage() {
        return damage; // Gibt den Schaden der Kugel zurück
    }

    public void registerHit() {
        if (!hit) {
            hit = true; // Markiert die Kugel als getroffen
        }
    }

    public boolean isMovingUp() {
        return moveUp; // Gibt zurück, ob die Kugel sich nach oben bewegt
    }
}
