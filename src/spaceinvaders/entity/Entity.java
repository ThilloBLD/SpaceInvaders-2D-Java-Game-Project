package spaceinvaders.entity;

import spaceinvaders.game.Game;
import spaceinvaders.texture.Texture;

import java.awt.*;

public abstract class Entity {
    private double x;
    private double y;
    private double speed;
    private Texture texture;

    public Entity(double x, double y, double speed, Texture texture) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = texture;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public Texture getTexture() {
        return texture;
    }

    public double getSpeed() {
        return speed;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void draw(Graphics g) {
        // Draw the entity
        g.drawImage(texture.getBufferedImage(), (int)x, (int)y, texture.getWidth(), texture.getHeight(), null);
    }

    public abstract void update(Game game);

    public int getWidth() {
        return texture.getWidth();
    }

    public int getHeight() {
        return texture.getHeight();
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
