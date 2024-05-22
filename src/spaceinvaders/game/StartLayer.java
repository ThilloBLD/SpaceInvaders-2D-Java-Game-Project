package spaceinvaders.game;

import spaceinvaders.handler.InputHandler;
import spaceinvaders.main.SpaceInvaders;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class StartLayer implements GameLabelLayer {

    private final InputHandler handler;

    public StartLayer(InputHandler handler) {
        this.handler = handler;
    }

    private void startGame() {
        SpaceInvaders.instance.frame.label.changeGameLayer(new GameLayer(handler));
    }

    @Override
    public void update() {
       if (handler.isKeyDown(KeyEvent.VK_ENTER)) {
           startGame();
       }
    }

    @Override
    public void draw(Graphics g) {

    }
}
