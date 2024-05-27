package spaceinvaders.entity;

import spaceinvaders.game.GameLayer;
import spaceinvaders.texture.AdvancedResourceManager;
import spaceinvaders.texture.AdvancedTextureType;
import spaceinvaders.texture.Theme;

public class Bullet extends Entity {

    // Variable für den Schaden, den das Projektil verursacht
    private final int damage;
    // Variable, die anzeigt, ob das Projektil getroffen hat
    private boolean hit = false;
    // Variable, die anzeigt, ob sich das Projektil nach oben bewegt
    private boolean moveUp;

    // Konstruktor für das Projektil
    public Bullet(double x, double y, double speed, boolean moveUp, Theme theme) {
        // Initialisiert die Superklasse Entity mit Position, Geschwindigkeit und der entsprechenden Textur
        // ? + : ist eine ternäre Bedingung, die entscheidet, ob die Textur für ein nach oben oder nach unten geschossenes Projektil verwendet wird
        super(x, y, speed, AdvancedResourceManager.getTextureForType(moveUp ? AdvancedTextureType.bullet : AdvancedTextureType.reversedbullet, theme));
        // Setzt den Schaden des Projektils auf einen zufälligen Wert zwischen 1 und 15
        damage = GameLayer.gameRandomizer.nextInt(15) + 1;
        // Setzt die Bewegung Richtung (nach oben oder nach unten)
        this.moveUp = moveUp;
    }

    // Aktualisiert den Zustand des Projektils
    @Override
    public void update(GameLayer game) {
        // Wenn das Projektil getroffen hat, entferne es aus dem Spiel
        if (hit) {
            game.removeBullet(this);
            return;
        }
        // Wenn das Projektil den oberen Rand des Spielfeldes erreicht, entferne es
        if (getY() < 0) {
            game.removeBullet(this);
            return;
        }
        // Wenn das Projektil den unteren Rand des Spielfeldes erreicht, entferne es
        if (getY() > (game.getLabelHeight() - getHeight())) {
            game.removeBullet(this);
            return;
        }
        // Bewegt das Projektil nach oben oder unten basierend auf der moveUp-Variable
        if (moveUp) {
            move(0, -getSpeed());
        } else {
            move(0, getSpeed());
        }
    }

    // Gibt den Schaden des Projektils zurück
    public int getDamage() {
        return damage;
    }

    // Markiert das Projektil als getroffen
    public void registerHit() {
        if (!hit) {
            hit = true;
        }
    }

    // Gibt zurück, ob sich das Projektil nach oben bewegt
    public boolean isMovingUp() {
        return moveUp;
    }
}
