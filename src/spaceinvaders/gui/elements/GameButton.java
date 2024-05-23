package spaceinvaders.gui.elements;

import spaceinvaders.texture.Texture;

import java.awt.Graphics;

public class GameButton {

    private final int id;
    private boolean selectButton;
    private Texture selectedTexture;
    private Texture unselectedTexture;
    private String buttonText;

    public GameButton(int id, boolean selectButton, Texture selectedTexture, Texture unselectedTexture, String buttonText) {
        this.id = id;
        this.selectButton = selectButton;
        this.selectedTexture = selectedTexture;
        this.unselectedTexture = unselectedTexture;
        this.buttonText = buttonText;
    }

    public int getID() {
        return id;
    }

    public void select() {
        selectButton = true;
    }

    public void unselect() {
        selectButton = false;
    }

    public boolean isButtonSelected() {
        return selectButton;
    }

    public void draw(Graphics g, int x, int y) {
        Texture toDraw = null;
        if (isButtonSelected()) {
            toDraw = selectedTexture;
        } else {
            toDraw = unselectedTexture;
        }
        g.drawImage(toDraw.getBufferedImage(), x, y, null);
        g.drawString(buttonText, x + ((toDraw.getWidth() - g.getFontMetrics().stringWidth(buttonText)) / 2), y + (toDraw.getHeight() / 2));
    }
}