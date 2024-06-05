package spaceinvaders.gui;

import javax.swing.JLabel;
import java.awt.Graphics;
import spaceinvaders.game.GameLabelLayer;
import spaceinvaders.game.StartLayer;
import spaceinvaders.handler.InputHandler;

public class GameLabel extends JLabel {

    // Aktuelle Spiel-Layer
    private GameLabelLayer gameLayer;

    // Konstruktor für das Spiel-Label
    public GameLabel(InputHandler inputHandler) {
        init(inputHandler); // Initialisierung des Spiel-Labels mit dem InputHandler
    }

    // Aktualisiert den Inhalt des Labels
    public synchronized void update() {
        gameLayer.update(); // Aktualisiert den aktuellen Spiel-Layer
    }

    // Initialisiert das Spiel-Label mit dem InputHandler
    private void init(InputHandler inputHandler) {
        gameLayer = new StartLayer(inputHandler); // Initialisiert das Spiel mit dem Start-Layer
    }

    // Zeichnet den Inhalt des Labels
    @Override
    protected synchronized void paintComponent(Graphics g) {
        gameLayer.draw(g); // Zeichnet den aktuellen Spiel-Layer
    }

    // Ändert den aktuellen Spiel-Layer
    public synchronized void changeGameLayer(GameLabelLayer gameLayer) {
        this.gameLayer = gameLayer; // Setzt den neuen Spiel-Layer
    }
}
