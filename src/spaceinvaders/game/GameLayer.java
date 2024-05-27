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
    // Spieler-Instanz
    private Player player;
    // Listen für Gegner, Geschosse und zu entfernende Elemente
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Bullet> removeBullets = new ArrayList<>();
    private ArrayList<Enemy> removeEnemies = new ArrayList<>();
    // Hintergrundbild
    private ImageIcon background;
    // Aktuelles Theme
    private Theme theme;
    // Zufallsgenerator für das Spiel
    public static final Random gameRandomizer = new Random();

    // Instanzvariable für den Input-Handler
    private InputHandler inputHandler;

    // Konstruktor, der den Input-Handler, die Hintergrund-ID und das Theme übernimmt
    public GameLayer(InputHandler inputHandler, int backgroundID, Theme theme) {
        this.inputHandler = inputHandler;
        this.theme = theme;
        init(backgroundID); // Initialisiert das Spiel
    }

    // Initialisiert das Spiel mit einer bestimmten Hintergrund-ID
    private void init(int backgroundID) {
        background = ResourceManager.getImageIconByBackgroundID(backgroundID);
        player = new Player(375, 400, 100, 0.5, AdvancedResourceManager.getTextureForType(AdvancedTextureType.player, theme));
        spawnAllEnemys(); // Spawnt alle Gegner
    }

    @Override
    public synchronized void update() {
        // Aktualisiert alle Geschosse
        for (Bullet bullet : bullets) {
            bullet.update(this);
        }
        // Entfernt markierte Geschosse
        if (!removeBullets.isEmpty()) {
            bullets.removeAll(removeBullets);
            removeBullets.clear();
        }
        // Aktualisiert den Spieler
        player.update(this);
        // Aktualisiert alle Gegner
        for (Enemy enemy : enemies) {
            enemy.update(this);
        }
        // Entfernt markierte Gegner
        if(!removeEnemies.isEmpty()) {
            enemies.removeAll(removeEnemies);
            removeEnemies.clear();
        }

        checkGameEnding(); // Überprüft, ob das Spiel beendet ist
    }

    // Alles was gezeichnet wird
    @Override
    public synchronized void draw(Graphics g) {
        g.drawImage(background.getImage(), 0, 0, null); // Zeichnet den Hintergrund
        // Zeichnet alle Geschosse
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        // Zeichnet den Spieler
        player.draw(g);
        // Zeichnet alle Gegner
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    // Überprüft, ob eine bestimmte Taste gedrückt wurde
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

    // Fügt ein neues Geschoss hinzu
    public synchronized void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    // Markiert ein Geschoss zum Entfernen
    public void removeBullet(Bullet bullet) {
        removeBullets.add(bullet);
    }

    // Markiert einen Gegner zum Entfernen
    public void removeEnemy(Enemy enemy) {
        removeEnemies.add(enemy);
    }

    // Gibt die Liste aller Geschosse zurück
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    // Überprüft, ob das Spiel beendet ist
    private void checkGameEnding() {
        if (enemies.isEmpty() || player.isDead()) {
            SpaceInvaders.instance.frame.label.changeGameLayer(new EndLayer(enemies.isEmpty(), inputHandler));
        }
    }

    // Gegner hinzufügen, entfernen, etc.
    public void spawnAllEnemys() {
        double x = 160;
        double y = 75;
        Texture textureEnemy = AdvancedResourceManager.getTextureForType(AdvancedTextureType.enemy, theme);
        // Spawnt eine bestimmte Anzahl von Gegnern
        for (int i = 0; i < 4; i++) {
            enemies.add(new Enemy(x, y, 50, 0.3, textureEnemy));
            x+= textureEnemy.getWidth() + 30;
            y+= textureEnemy.getHeight() + 10;
        }
    }

    // Gibt das aktuelle Theme zurück
    public Theme getTheme() {
        return theme;
    }
}
