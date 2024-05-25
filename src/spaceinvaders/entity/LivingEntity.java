package spaceinvaders.entity;

import spaceinvaders.texture.Texture;

// Abstrakte Klasse für lebende Entitys
public abstract class LivingEntity extends Entity {
    private double health; // Aktuelle Gesundheit des Entity
    private double maxHealth; // Maximale Gesundheit des Entity
    private boolean alive; // Gibt an, ob der Entity noch lebt

    // Konstruktor für das LivingEntity-Objekt
    public LivingEntity(double x, double y, double health, double speed, Texture texture) {
        super(x, y, speed, texture);
        this.health = health;
        this.maxHealth = health;
        alive = true;
    }

    // Getter für die aktuelle Gesundheit
    public double getHealth() {
        return health;
    }

    // Getter für die maximale Gesundheit
    public double getMaxHealth() {
        return maxHealth;
    }

    // Gibt zurück, ob der Entity lebt
    public boolean isAlive() {
        return alive;
    }

    // Gibt zurück, ob der Entity tot ist
    public boolean isDead() {
        return !alive;
    }

    // Fügt dem Entity Schaden zu
    public void damage(double damage) {
        health -= damage;
        if (health <= 0) {
            alive = false; // Setzt alive auf false, wenn die Gesundheit auf 0 oder weniger sinkt
        }
    }
}
