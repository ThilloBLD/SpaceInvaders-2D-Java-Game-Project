package spaceinvaders.gui;

import spaceinvaders.handler.InputHandler;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    // Breite und Höhe des Fensters
    private int width;
    private int height;
    // Spiel-Label und Input-Handler
    public GameLabel label;
    public InputHandler inputHandler;

    // Konstruktor für das Spiel-Fenster
    public GameFrame(int width, int height) {
        this.width = width;
        this.height = height;
        init(); // Initialisiert den InputHandler und das Label
        initFrame(); // Initialisiert die Frame-Eigenschaften
    }

    // Initialisiert den InputHandler und das Label
    private void init() {
        inputHandler = new InputHandler(); // Initialisierung des InputHandlers
        label = new GameLabel(inputHandler); // Initialisierung des GameLabels mit dem InputHandler
    }

    // Setzt die Eigenschaften des Frames
    private void initFrame() {
        setName("Space Invaders"); // Setzt den Namen des Fensters
        setTitle("Space Invaders"); // Setzt den Titel des Fensters
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Schließt das Fenster bei Klick auf 'X'
        setSize(width, height); // Setzt die Größe des Fensters
        setLocationRelativeTo(null); // Zentriert das Fenster auf dem Bildschirm
        setResizable(false); // Verhindert die Größenänderung des Fensters
        addKeyListener(inputHandler); // Fügt den KeyListener hinzu
        addWindowListener(inputHandler); // Fügt den WindowListener hinzu
        add(label); // Fügt das Label zum Frame hinzu
        setVisible(true); // Macht das Fenster sichtbar
    }

    // Aktualisiert den Inhalt des Spiels
    public void update() {
        label.update(); // Weitergabe der Aktualisierung an das Label
    }

    // Getter für die Breite des Labels
    public int getLabelWidth() {
        return label.getWidth();
    }

    // Getter für die Höhe des Labels
    public int getLabelHeight() {
        return label.getHeight();
    }

}
