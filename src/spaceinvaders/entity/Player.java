package spaceinvaders.entity;

import spaceinvaders.game.GameLayer;
import spaceinvaders.texture.Texture;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends LivingEntity {

    // Zeitpunkt, ab dem der Spieler wieder schießen darf
    private long allowedShoot;
    public static final long shootDelay = 750; // Verzögerung zwischen zwei Schüssen

    // Konstruktor für das Player-Objekt
    public Player(double x, double y, double health, double speed, Texture texture) {
        super(x, y, health, speed, texture);
        allowedShoot = 0; // Initialisierung des Schusszeitpunkts
    }

    // Bewegt den Spieler nach links
    public void moveLeft() {
        double newX = getX() - getSpeed();
        if (newX < 0) {
            newX = 0; // Verhindert das Verlassen des linken Spielfeldrands
        }
        setX(newX);
    }

    // Bewegt den Spieler nach rechts
    public void moveRight(int mapWidth) {
        double newX = getX() + getSpeed();
        if ((newX + getWidth()) > mapWidth) {
            newX = mapWidth - getWidth(); // Verhindert das Verlassen des rechten Spielfeldrands
        }
        setX(newX);
    }

    // Aktualisiert den Zustand des Spielers
    @Override
    public void update(GameLayer game) {
        // Überprüft Kollisionen mit Kugeln
        for (Bullet b : game.getBullets()) {
            if (collide(b)) {
                b.registerHit(); // Registriert den Treffer
                damage(b.getDamage()); // Fügt dem Spieler Schaden zu
            }
        }

        if (isDead()) {
            return; // Wenn der Spieler tot ist, wird die Methode beendet
        }

        // Überprüft Tasteneingaben und bewegt den Spieler entsprechend
        if (game.isKeyPressed(KeyEvent.VK_RIGHT)) {
            moveRight(game.getLabelWidth());
        }
        if (game.isKeyPressed(KeyEvent.VK_LEFT)) {
            moveLeft();
        }
        if (game.isKeyPressed(KeyEvent.VK_SPACE)) {
            shootBullet(game); // Schießt eine Kugel, wenn die Leertaste gedrückt wird
        }
    }

    // Zeichnet den Spieler und dessen Gesundheitsanzeige
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawString(getHealth() + "/" + getMaxHealth(), 10, 20); // Zeichnet die aktuelle und maximale Gesundheit
    }

    // Methode zum Schießen einer Kugel
    private void shootBullet(GameLayer game) {
        if (System.currentTimeMillis() >= allowedShoot) {
            game.addBullet(new Bullet(getX() + getWidth() / 2, getY(), 1, true, game.getTheme())); // Fügt eine neue Kugel zum Spiel hinzu
            allowedShoot = System.currentTimeMillis() + shootDelay; // Setzt den Zeitpunkt für den nächsten Schuss
        }
    }
}
