package spaceinvaders.game;

import java.awt.*;

public interface GameLabelLayer {

    // Methode zur Aktualisierung des Zustands der Spielebene
    public void update();

    // Methode zum Zeichnen der Spielebene
    public void draw(Graphics g);
}
