package spaceinvaders.entity;

import spaceinvaders.game.GameLayer;
import spaceinvaders.texture.Texture;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends LivingEntity {

    // Zeitpunkt, ab dem der Spieler wieder schießen darf
    private long allowedShoot;
    // Verzögerung zwischen zwei Schüssen in Millisekunden
    public static final long shootDelay = 750;

    // Konstruktor für den Spieler
    public Player(double x, double y, double health, double speed, Texture texture) {
        // Initialisiert die Superklasse LivingEntity mit Position, Gesundheit, Geschwindigkeit und Textur
        super(x, y, health, speed, texture);
        allowedShoot = 0; // Setzt den Zeitpunkt für den nächsten Schuss auf 0
    }

    // Bewegt den Spieler nach links
    public void moveLeft() {
        double newX = getX() - getSpeed();
        if (newX < 0) {
            newX = 0; // Verhindert, dass der Spieler aus dem Bildschirmbereich heraus bewegt
        }
        setX(newX);
    }

    // Bewegt den Spieler nach rechts
    public void moveRight(int mapWidth) {
        double newX = getX() + getSpeed();
        if ((newX + getWidth()) > mapWidth) {
            newX = mapWidth - getWidth(); // Verhindert, dass der Spieler aus dem Bildschirmbereich heraus bewegt
        }
        setX(newX);
    }

    // Aktualisiert den Zustand des Spielers
    @Override
    public void update(GameLayer game) {
        // Überprüft, ob eine Kollision mit einem Projektil vorliegt
        for (Bullet b : game.getBullets()) {
            if (collide(b)) {
                b.registerHit(); // Registriert den Treffer des Projektils
                damage(b.getDamage()); // Verursacht Schaden am Spieler
            }
        }

        if (isDead()) {
            return; // Wenn der Spieler tot ist, wird die Methode beendet
        }

        // Überprüft, welche Tasten gedrückt sind und bewegt den Spieler entsprechend
        if (game.isKeyPressed(KeyEvent.VK_RIGHT)) {
            moveRight(game.getLabelWidth());
        }
        if (game.isKeyPressed(KeyEvent.VK_LEFT)) {
            moveLeft();
        }
        if (game.isKeyPressed(KeyEvent.VK_SPACE)) {
            shootBullet(game); // Schießt ein Projektil, wenn die Leertaste gedrückt wird
        }
    }

    // Zeichnet den Spieler und seine Gesundheitsanzeige
    @Override
    public void draw(Graphics g) {
        super.draw(g); // Zeichnet den Spieler
        // Setzt die Farbe auf Weiß
        g.setColor(Color.WHITE);
        g.drawString(getHealth() + "/" + getMaxHealth(), 10, 20); // Zeichnet die Gesundheitsanzeige
    }

    // Methode, die es dem Spieler erlaubt, ein Projektil zu schießen
    private void shootBullet(GameLayer game) {
        // Überprüft, ob der Spieler wieder schießen darf
        if (System.currentTimeMillis() >= allowedShoot) {
            // Fügt ein neues Projektil zum Spiel hinzu
            game.addBullet(new Bullet(getX() + getWidth() / 2, getY(), 1, true, game.getTheme()));
            // Setzt den Zeitpunkt für den nächsten Schuss
            allowedShoot = System.currentTimeMillis() + shootDelay;
        }
    }
}
