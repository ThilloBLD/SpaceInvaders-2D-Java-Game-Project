package spaceinvaders.game;

import java.awt.*;

/**
 * Dieses Interface definiert die Methoden, die alle Schichten (Layer) eines Spiels implementieren müssen.
 * Jede Schicht muss sich aktualisieren und gezeichnet werden können.
 */
public interface GameLabelLayer {

    /**
     * Diese Methode wird verwendet, um den Zustand der Schicht zu aktualisieren.
     * Sie wird in jeder Spielschleife aufgerufen, um die Logik der Schicht zu aktualisieren.
     */
    public void update();

    /**
     * Diese Methode wird verwendet, um die Schicht zu zeichnen.
     * Sie wird in jeder Spielschleife aufgerufen, um die grafische Darstellung der Schicht zu aktualisieren.
     *
     * @param g Das Graphics-Objekt, das zum Zeichnen verwendet wird.
     */
    public void draw(Graphics g);
}
