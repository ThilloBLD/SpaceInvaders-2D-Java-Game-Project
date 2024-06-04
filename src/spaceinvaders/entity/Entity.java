package spaceinvaders.entity;

import spaceinvaders.game.GameLayer;
import spaceinvaders.texture.Texture;

import java.awt.*;

public abstract class Entity {
    // Koordinaten der Entität
    private double x;
    private double y;
    // Geschwindigkeit der Entität
    private double speed;
    // Textur der Entität
    private Texture texture;

    // Konstruktor für die Entität
    public Entity(double x, double y, double speed, Texture texture) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = texture;
    }

    // Getter für die X-Koordinate
    public double getX() {
        return x;
    }

    // Getter für die Y-Koordinate
    public double getY() {
        return y;
    }

    // Bewegt die Entität um dx und dy
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    // Getter für die Textur der Entität
    public Texture getTexture() {
        return texture;
    }

    // Getter für die Geschwindigkeit der Entität
    public double getSpeed() {
        return speed;
    }

    // Setter für die X-Koordinate
    public void setX(double x) {
        this.x = x;
    }

    // Setter für die Y-Koordinate
    public void setY(double y) {
        this.y = y;
    }

    // Zeichnet die Entität
    public void draw(Graphics g) {
        // Zeichnet das Bild der Textur an den Koordinaten x und y mit der Breite und Höhe der Textur
        g.drawImage(texture.getBufferedImage(), (int)x, (int)y, texture.getWidth(), texture.getHeight(), null);
    }

    // Abstrakte Methode zur Aktualisierung der Entität muss von Unterklassen implementiert werden
    public abstract void update(GameLayer game);

    // Getter für die Breite der Entität (basierend auf der Textur)
    public int getWidth() {
        return texture.getWidth();
    }

    // Getter für die Höhe der Entität (basierend auf der Textur)
    public int getHeight() {
        return texture.getHeight();
    }

    // Setter für die Geschwindigkeit der Entität
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    // Überprüft, ob diese Entität mit einer anderen kollidiert
    public boolean collide(Entity e) {
        // Prüft, ob die Koordinaten der anderen Entität innerhalb der Grenzen dieser Entität liegen
        return (e.getX() >= getX() && e.getY() >= getY() && e.getX() <= (getX() + getWidth()) && e.getY() <= (getY() + getHeight()));
    }

    // Berechnet die Entfernung zu einer anderen Entität
    public double getDistance(Entity flash) {
        // Verwenden des Satzes des Pythagoras, um die Entfernung zu berechnen
        return Math.sqrt(Math.pow(flash.getX() - getX(), 2) + Math.pow(flash.getY() - getY(), 2));
    }
}
