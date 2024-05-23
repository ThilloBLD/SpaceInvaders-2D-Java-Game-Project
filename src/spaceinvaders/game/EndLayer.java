package spaceinvaders.game;

import spaceinvaders.main.SpaceInvaders;

import java.awt.*;

public class EndLayer implements GameLabelLayer {

    private final boolean gameWon;

    public EndLayer(boolean gameWon) {
        this.gameWon = gameWon;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        String textSpeicher = null;
        if (gameWon) {
            textSpeicher = "Du hast gewonnen! :D";
        } else {
            textSpeicher = "Du hast verloren! :(";
        }

        g.drawString(textSpeicher, (SpaceInvaders.instance.frame.label.getWidth() - g.getFontMetrics().stringWidth(textSpeicher)) / 2, SpaceInvaders.instance.frame.label.getHeight() / 2);
    }
}
