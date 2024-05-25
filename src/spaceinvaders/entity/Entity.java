package spaceinvaders.entity;

import spaceinvaders.game.GameLayer;
import spaceinvaders.texture.Texture;

import java.awt.*;

public abstract class Entity {
    private double x; // X-Koordinate des Entity
    private double y; // Y-Koordinate des Entity
    private double speed; // Geschwindigkeit des Entity
    private Texture texture; // Textur des Entity

    // Konstruktor für das Entity-Objekt
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

    // Methode zur Bewegung des Entity
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    // Getter für die Textur
    public Texture getTexture() {
        return texture;
    }

    // Getter für die Geschwindigkeit
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

    // Methode zum Zeichnen des Entity
    public void draw(Graphics g) {
        g.drawImage(texture.getBufferedImage(), (int)x, (int)y, texture.getWidth(), texture.getHeight(), null);
    }

    // Abstrakte Methode zur Aktualisierung des Entity
    public abstract void update(GameLayer game);

    // Getter für die Breite der Textur
    public int getWidth() {
        return texture.getWidth();
    }

    // Getter für die Höhe der Textur
    public int getHeight() {
        return texture.getHeight();
    }

    // Setter für die Geschwindigkeit
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    // Methode zur Überprüfung der Kollision mit eines anderen Entity
    public boolean collide(Entity e) {
        return (e.getX() >= getX() && e.getY() >= getY() && e.getX() <= (getX() + getWidth()) && e.getY() <= (getY() + getHeight()));
    }

    // Methode zur Berechnung der Entfernung zu eines anderen Entity
    public double getDistance(Entity flash) {
        return Math.sqrt(Math.pow(flash.getX() - getX(), 2) + Math.pow(flash.getY() - getY(), 2));
    }
}
