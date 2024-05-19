package spaceinvaders.gui;

import javax.swing.JLabel;
import java.awt.*;
import spaceinvaders.game.Game;
import spaceinvaders.handler.InputHandler;

public class GameLabel extends JLabel{
    private Game game;

    public GameLabel(InputHandler inputHandler) {

        init(inputHandler);
    }

    public void update() {
        game.update();
    }

    private void init(InputHandler inputHandler) {

        game = new Game(inputHandler);

    }

    @Override
    protected void paintComponent(Graphics g) {
        game.draw(g);
    }
}
