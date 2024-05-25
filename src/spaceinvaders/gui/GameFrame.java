package spaceinvaders.gui;

import spaceinvaders.handler.InputHandler;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    private int width;
    private int height;
    public GameLabel label;
    public InputHandler inputHandler;

    // Konstruktor für GameFrame
    public GameFrame(int width, int height) {
        this.width = width;
        this.height = height;
        init(); // Initialisierung des Frames
        initFrame(); // Initialisierung der Frame-Eigenschaften
    }

    // Initialisierung von InputHandler und GameLabel
    private void init() {
        inputHandler = new InputHandler(); // InputHandler initialisieren
        label = new GameLabel(inputHandler); // GameLabel initialisieren
    }

    // Initialisierung der Frame-Eigenschaften
    private void initFrame() {
        setName("Space Invaders"); // Setzen des Namens des Frames
        setTitle("Space Invaders"); // Setzen des Titels des Frames
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Festlegen der Schließaktion
        setSize(width, height); // Festlegen der Größe des Frames
        setLocationRelativeTo(null); // Zentrieren des Frames auf dem Bildschirm
        setResizable(false); // Deaktivieren der Größenänderung des Frames
        addKeyListener(inputHandler); // Hinzufügen des KeyListeners zum Frame
        addWindowListener(inputHandler); // Hinzufügen des WindowListeners zum Frame
        add(label); // Hinzufügen des GameLabels zum Frame
        setVisible(true); // Frame sichtbar machen
    }

    // Aktualisierungsmethode für den Frame
    public void update() {
        label.update(); // Weitergabe der Aktualisierung an das GameLabel
    }

    // Rückgabe der Breite des GameLabels
    public int getLabelWidth() {
        return label.getWidth();
    }

    // Rückgabe der Höhe des GameLabels
    public int getLabelHeight() {
        return label.getHeight();
    }

}
