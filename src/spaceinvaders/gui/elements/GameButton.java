package spaceinvaders.gui.elements;

import spaceinvaders.texture.Texture;

import java.awt.*;

public class GameButton {

    private final int id;
    private boolean selectButton;
    private Texture selectedTexture;
    private Texture unselectedTexture;
    private String buttonText;

    // Konstruktor für GameButton
    public GameButton(int id, boolean selectButton, Texture selectedTexture, Texture unselectedTexture, String buttonText) {
        this.id = id;
        this.selectButton = selectButton;
        this.selectedTexture = selectedTexture;
        this.unselectedTexture = unselectedTexture;
        this.buttonText = buttonText;
    }

    // Gibt die ID des Buttons zurück
    public int getID() {
        return id;
    }

    // Selektiert den Button
    public void select() {
        selectButton = true;
    }

    // Deselektiert den Button
    public void unselect() {
        selectButton = false;
    }

    // Überprüft, ob der Button selektiert ist
    public boolean isButtonSelected() {
        return selectButton;
    }

    // Zeichnet den Button
    public void draw(Graphics g, int x, int y) {
        Texture toDraw = selectButton ? selectedTexture : unselectedTexture; // Auswahl der zu zeichnenden Textur
        // Zeichnen der Textur und des Button-Textes
        g.drawImage(toDraw.getBufferedImage(), x, y, null);
        g.drawString(buttonText, x + ((toDraw.getWidth() - g.getFontMetrics().stringWidth(buttonText)) / 2),
                y + (toDraw.getHeight() / 2));
    }
}
