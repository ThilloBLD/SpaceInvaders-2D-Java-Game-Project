package spaceinvaders.entity;

import spaceinvaders.game.GameLayer;
import spaceinvaders.texture.Texture;

public class Enemy extends LivingEntity {

    private long allowedShoot; // Zeitpunkt, ab dem der Feind wieder schießen darf
    public static final long minShootDelay = 750; // Mindestzeit zwischen zwei Schüssen

    // Konstruktor für das Enemy-Objekt
    public Enemy(double x, double y, double health, double speed, Texture texture) {
        super(x, y, health, speed, texture);
    }

    @Override
    public void update(GameLayer game) {

        // Überprüft, ob das Enemy mit einer Kugel kollidiert
        for(Bullet b: game.getBullets()) {
            if(collide(b)) {
                if(b.isMovingUp()) {
                    b.registerHit(); // Registriert den Treffer
                    damage(b.getDamage()); // Fügt dem Enemy Schaden zu
                } else {
                    b.setY(b.getY() + b.getHeight() + getHeight()); // Bewegt die Kugel nach unten, falls sie nicht nach oben fliegt
                }
            }
        }

        if(isDead()) {
            game.removeEnemy(this); // Entfernt das Enemy, wenn es tot ist
            return;
        }

        shootBullet(game); // Schießt eine Kugel

        double newX = getX() + getSpeed(); // Berechnet die neue X-Position
        if (newX <= 0) {
            newX = 0;
            setSpeed(- getSpeed()); // Ändert die Richtung, wenn der linke Rand erreicht ist
        } else if ((newX + getWidth()) >= game.getLabelWidth()) {
            newX = (game.getLabelWidth() - getWidth());
            setSpeed(- getSpeed()); // Ändert die Richtung, wenn der rechte Rand erreicht ist
        } else {
            if (GameLayer.gameRandomizer.nextInt(600) == 0) {
                setSpeed(- getSpeed()); // Zufällige Richtungsänderung
            }
        }
        setX(newX); // Setzt die neue X-Position
    }

    // Methode zum Schießen einer Kugel
    public void shootBullet(GameLayer game) {
        if (System.currentTimeMillis() >= allowedShoot) {
            game.addBullet(new Bullet(getX() + getWidth() / 2, getY() + getHeight(), 1, false, game.getTheme())); // Fügt eine neue Kugel zum Spiel hinzu
            allowedShoot = System.currentTimeMillis() + GameLayer.gameRandomizer.nextLong(750) + minShootDelay; // Setzt den Zeitpunkt für den nächsten Schuss
        }
    }
}
