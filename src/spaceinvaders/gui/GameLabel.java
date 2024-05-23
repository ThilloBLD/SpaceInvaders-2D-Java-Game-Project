package spaceinvaders.gui;

import javax.swing.JLabel;
import java.awt.Graphics;
import spaceinvaders.game.GameLabelLayer;
import spaceinvaders.game.StartLayer;
import spaceinvaders.handler.InputHandler;

public class GameLabel extends JLabel {

    private GameLabelLayer gameLayer;

    public GameLabel(InputHandler inputHandler) {
        init(inputHandler);
    }

    public void update() {
        gameLayer.update();
    }

    private void init(InputHandler inputHandler) {
        gameLayer = new StartLayer(inputHandler);
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        gameLayer.draw(g);
    }

    public synchronized void changeGameLayer(GameLabelLayer gameLayer) {
        this.gameLayer = gameLayer;
    }
}
