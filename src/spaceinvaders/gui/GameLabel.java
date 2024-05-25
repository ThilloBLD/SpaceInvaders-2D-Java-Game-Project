package spaceinvaders.gui;

import javax.swing.JLabel;
import java.awt.Graphics;
import spaceinvaders.game.GameLabelLayer; // Importiere das GameLabelLayer-Interface
import spaceinvaders.game.StartLayer; // Importiere die StartLayer
import spaceinvaders.handler.InputHandler;

public class GameLabel extends JLabel {

    private GameLabelLayer gameLayer; // Deklariere eine Variable vom Typ GameLabelLayer

    // Konstruktor für GameLabel
    public GameLabel(InputHandler inputHandler) {
        init(inputHandler); // Initialisiere das GameLabel
    }

    // Aktualisiere die GameLayer
    public void update() {
        gameLayer.update(); // Rufe die Update-Methode der aktuellen GameLayer auf
    }

    // Initialisierung des GameLabels
    private void init(InputHandler inputHandler) {
        gameLayer = new StartLayer(inputHandler); // Erstelle einen neue StartLayer als Standard-Gamelayer
    }

    // Überschreibe die paintComponent-Methode, um die Grafiken zu zeichnen
    @Override
    protected synchronized void paintComponent(Graphics g) {
        gameLayer.draw(g); // Zeichne die Grafiken der aktuellen GameLayer
    }

    // Methode zum Wechseln der GameLayer
    public synchronized void changeGameLayer(GameLabelLayer gameLayer) {
        this.gameLayer = gameLayer; // Setze die aktuelle GameLayer auf die übergebene GameLayer
    }
}
