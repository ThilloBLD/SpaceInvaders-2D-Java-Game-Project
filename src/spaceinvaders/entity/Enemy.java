package spaceinvaders.entity;

import spaceinvaders.game.GameLayer;
import spaceinvaders.texture.Texture;

public class Enemy extends LivingEntity {

    // Zeitpunkt, ab dem der Feind wieder schießen darf
    private long allowedShoot;
    // Mindestverzögerung zwischen zwei Schüssen in Millisekunden
    public static final long minShootDelay = 750;

    // Konstruktor für den Feind
    public Enemy(double x, double y, double health, double speed, Texture texture) {
        // Initialisiert die Superklasse LivingEntity mit Position, Gesundheit, Geschwindigkeit und Textur
        super(x, y, health, speed, texture);
    }

    @Override
    public void update(GameLayer game) {
        // Überprüft, ob eine Kollision mit einem Projektil vorliegt
        for (Bullet b : game.getBullets()) {
            if (collide(b)) {
                if (b.isMovingUp()) {
                    // Wenn das Projektil nach oben fliegt und trifft, registriere den Treffer und verursache Schaden
                    b.registerHit();
                    damage(b.getDamage());
                } else {
                    // Wenn das Projektil nach unten fliegt, setze es hinter den Feind
                    b.setY(b.getY() + b.getHeight() + getHeight());
                }
            }
        }

        // Wenn der Feind tot ist, entferne ihn aus dem Spiel
        if (isDead()) {
            game.removeEnemy(this);
            return;
        }

        // Der Feind schießt ein Projektil
        shootBullet(game);

        // Berechne die neue X-Position des Feindes
        double newX = getX() + getSpeed();
        if (newX <= 0) {
            // Wenn der Feind den linken Rand erreicht, ändere die Richtung
            newX = 0;
            setSpeed(-getSpeed());
        } else if ((newX + getWidth()) >= game.getLabelWidth()) {
            // Wenn der Feind den rechten Rand erreicht, ändere die Richtung
            newX = (game.getLabelWidth() - getWidth());
            setSpeed(-getSpeed());
        } else {
            // Zufällige Richtungsänderung
            if (GameLayer.gameRandomizer.nextInt(600) == 0) {
                setSpeed(-getSpeed());
            }
        }
        // Setze die neue X-Position des Feindes
        setX(newX);
    }

    // Methode, die es dem Feind erlaubt, ein Projektil zu schießen
    public void shootBullet(GameLayer game) {
        // Überprüft, ob der Feind wieder schießen darf
        if (System.currentTimeMillis() >= allowedShoot) {
            // Fügt ein neues Projektil zum Spiel hinzu
            game.addBullet(new Bullet(getX() + getWidth() / 2, getY() + getHeight(), 1, false, game.getTheme()));
            // Setzt den Zeitpunkt für den nächsten Schuss
            allowedShoot = System.currentTimeMillis() + GameLayer.gameRandomizer.nextLong(750) + minShootDelay;
        }
    }
}
