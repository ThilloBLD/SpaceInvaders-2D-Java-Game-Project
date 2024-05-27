package spaceinvaders.entity;

import spaceinvaders.texture.Texture;

public abstract class LivingEntity extends Entity {
    // Aktuelle Gesundheit der Entität
    private double health;
    // Maximale Gesundheit der Entität
    private double maxHealth;
    // Gibt an, ob die Entität am Leben ist
    private boolean alive;

    // Konstruktor für die lebende Entität
    public LivingEntity(double x, double y, double health, double speed, Texture texture) {
        // Initialisiert die Superklasse Entity mit Position, Geschwindigkeit und Textur
        super(x, y, speed, texture);
        this.health = health;
        this.maxHealth = health;
        // Setzt den Status der Entität auf lebendig
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

    // Überprüft, ob die Entität am Leben ist
    public boolean isAlive() {
        return alive;
    }

    // Überprüft, ob die Entität tot ist
    public boolean isDead() {
        return !alive;
    }

    // Verursacht Schaden an der Entität und aktualisiert den Status
    public void damage(double damage) {
        health -= damage;
        // Wenn die Gesundheit 0 oder weniger beträgt, setze alive auf false
        if (health <= 0) {
            alive = false;
        }
    }
}
