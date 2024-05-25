package spaceinvaders.entity;

import spaceinvaders.texture.Texture;

public abstract class LivingEntity extends Entity{
    private double health;
    private double maxHealth;
    private boolean alive;

    public LivingEntity(double x, double y, double health, double speed, Texture texture) {
        super(x, y, speed, texture);
        this.health = health;
        this.maxHealth = health;
        alive = true;
    }

    public double getHealth() {
        return health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDead() {
        return !alive;
    }

    public void damage(double damage) {
        health -= damage;
        if (health <= 0)
        {
            alive = false;
        }
    }
}
