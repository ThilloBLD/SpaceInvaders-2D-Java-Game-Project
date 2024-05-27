package spaceinvaders.gui.elements;

import spaceinvaders.texture.Texture;

import java.awt.Graphics;

public class GameButton {

    // Eindeutige ID des Buttons
    private final int id;
    // Status, ob der Button ausgewählt ist
    private boolean selectButton;
    // Textur des ausgewählten Buttons
    private Texture selectedTexture;
    // Textur des nicht ausgewählten Buttons
    private Texture unselectedTexture;
    // Text, der auf dem Button angezeigt wird
    private String buttonText;

    // Konstruktor für den GameButton
    public GameButton(int id, boolean selectButton, Texture selectedTexture, Texture unselectedTexture, String buttonText) {
        this.id = id;
        this.selectButton = selectButton;
        this.selectedTexture = selectedTexture;
        this.unselectedTexture = unselectedTexture;
        this.buttonText = buttonText;
    }

    // Getter für die ID des Buttons
    public int getID() {
        return id;
    }

    // Setzt den Button als ausgewählt
    public void select() {
        selectButton = true;
    }

    // Setzt den Button als nicht ausgewählt
    public void unselect() {
        selectButton = false;
    }

    // Überprüft, ob der Button ausgewählt ist
    public boolean isButtonSelected() {
        return selectButton;
    }

    // Zeichnet den Button
    public void draw(Graphics g, int x, int y) {
        // Bestimmt, welche Textur gezeichnet werden soll
        Texture toDraw = null;
        if (isButtonSelected()) {
            toDraw = selectedTexture;
        } else {
            toDraw = unselectedTexture;
        }
        // Zeichnet die Textur des Buttons
        g.drawImage(toDraw.getBufferedImage(), x, y, null);
        // Zeichnet den Text auf dem Button zentriert
        g.drawString(buttonText, x + ((toDraw.getWidth() - g.getFontMetrics().stringWidth(buttonText)) / 2), y + (toDraw.getHeight() / 2));
    }
}
