package spaceinvaders.game;

import spaceinvaders.entity.Bullet;
import spaceinvaders.entity.Enemy;
import spaceinvaders.entity.Player;
import spaceinvaders.handler.InputHandler;
import spaceinvaders.main.SpaceInvaders;
import spaceinvaders.texture.Texture;
import spaceinvaders.texture.Theme;
import spaceinvaders.texture.AdvancedResourceManager;
import spaceinvaders.texture.AdvancedTextureType;
import spaceinvaders.texture.ResourceManager;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.util.Random;

public class GameLayer implements GameLabelLayer {
    private Player player; // Der Spieler
    private ArrayList<Enemy> enemies = new ArrayList<>(); // Liste der Gegner
    private ArrayList<Bullet> bullets = new ArrayList<>(); // Liste der Kugeln
    private ArrayList<Bullet> removeBullets = new ArrayList<>(); // Liste der zu entfernenden Kugeln
    private ArrayList<Enemy> removeEnemies = new ArrayList<>(); // Liste der zu entfernenden Gegner
    private ImageIcon background; // Hintergrundbild
    private Theme theme; // Thema des Spiels
    public static final Random gameRandomizer = new Random(); // Zufallsgenerator für das Spiel

    private InputHandler inputHandler; // Eingabebehandler

    // Konstruktor für das GameLayer-Objekt
    public GameLayer(InputHandler inputHandler, int backgroundID, Theme theme) {
        this.inputHandler = inputHandler;
        this.theme = theme;
        init(backgroundID); // Initialisiert das Spielfeld
    }

    // Initialisiert das Spielfeld mit Hintergrund und Spieler
    private void init(int backgroundID) {
        background = ResourceManager.getImageIconByBackgroundID(backgroundID); // Hintergrundbild laden
        player = new Player(375, 400, 100, 0.5, AdvancedResourceManager.getTextureForType(AdvancedTextureType.player, theme)); // Spieler erstellen
        spawnAllEnemys(); // Gegner spawnen
    }

    // Aktualisiert den Zustand des Spielfelds
    @Override
    public synchronized void update() {
        // Aktualisiert jede Kugel
        for (Bullet bullet : bullets) {
            bullet.update(this);
        }
        // Entfernt getroffene Kugeln
        if (!removeBullets.isEmpty()) {
            bullets.removeAll(removeBullets);
            removeBullets.clear();
        }
        // Aktualisiert den Spieler
        player.update(this);
        // Aktualisiert jeden Gegner
        for (Enemy enemy : enemies) {
            enemy.update(this);
        }
        // Entfernt getötete Gegner
        if (!removeEnemies.isEmpty()) {
            enemies.removeAll(removeEnemies);
            removeEnemies.clear();
        }

        // Überprüft, ob das Spiel beendet werden muss
        checkGameEnding();
    }

    // Zeichnet alle Elemente auf dem Spielfeld
    @Override
    public synchronized void draw(Graphics g) {
        // Hintergrund zeichnen
        g.drawImage(background.getImage(), 0, 0, null);
        // Jede Kugel zeichnen
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        // Spieler zeichnen
        player.draw(g);
        // Jeden Gegner zeichnen
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    // Überprüft, ob eine bestimmte Taste gedrückt ist
    public boolean isKeyPressed(int keyCode) {
        return inputHandler.isKeyDown(keyCode);
    }

    // Gibt die Breite des Spielfelds zurück
    public int getLabelWidth() {
        return SpaceInvaders.instance.frame.getLabelWidth();
    }

    // Gibt die Höhe des Spielfelds zurück
    public int getLabelHeight() {
        return SpaceInvaders.instance.frame.getLabelHeight();
    }

    // Fügt eine Kugel zur Liste hinzu
    public synchronized void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    // Markiert eine Kugel zur Entfernung
    public void removeBullet(Bullet bullet) {
        removeBullets.add(bullet);
    }

    // Markiert einen Gegner zur Entfernung
    public void removeEnemy(Enemy enemy) {
        removeEnemies.add(enemy);
    }

    // Gibt die Liste der Kugeln zurück
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    // Überprüft, ob das Spiel beendet ist
    private void checkGameEnding() {
        if (enemies.isEmpty() || player.isDead()) {
            // Wechsel zur Endebene, wenn alle Gegner besiegt oder der Spieler tot ist
            SpaceInvaders.instance.frame.label.changeGameLayer(new EndLayer(enemies.isEmpty(), inputHandler));
        }
    }

    // Spawnt alle Gegner
    public void spawnAllEnemys() {
        double x = 160;
        double y = 75;
        Texture textureEnemy = AdvancedResourceManager.getTextureForType(AdvancedTextureType.enemy, theme); // Textur für Gegner laden
        for (int i = 0; i < 4; i++) {
            enemies.add(new Enemy(x, y, 50, 0.3, textureEnemy)); // Gegner erstellen und zur Liste hinzufügen
            x += textureEnemy.getWidth() + 30;
            y += textureEnemy.getHeight() + 10;
        }
    }

    // Gibt das Thema des Spiels zurück
    public Theme getTheme() {
        return theme;
    }

}
