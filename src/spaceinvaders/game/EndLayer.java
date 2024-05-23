package spaceinvaders.game;

import spaceinvaders.gui.elements.GameButton;
import spaceinvaders.handler.InputHandler;
import spaceinvaders.main.SpaceInvaders;

import spaceinvaders.handler.InputHandler;
import spaceinvaders.texture.Texture;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EndLayer implements GameLabelLayer {

    private final InputHandler handler;
    private ArrayList<GameButton> allButtons = new ArrayList<>();
    private final boolean gameWon;

    public EndLayer(boolean gameWon, InputHandler handler) {
        this.gameWon = gameWon;
        this.handler = handler;
        init();
    }

    private void init() {
        Texture unselectedButton = new Texture(100, 40);
        unselectedButton.setColor(Color.YELLOW);
        unselectedButton.setRandColor(Color.BLACK);
        Texture selectedButton = new Texture(100, 40);
        selectedButton.setColor(Color.CYAN);
        selectedButton.setRandColor(Color.BLACK);
        allButtons.add(new GameButton(0, true, selectedButton, unselectedButton, "Replay"));
        allButtons.add(new GameButton(1,false, selectedButton, unselectedButton, "Exit"));
        if (allButtons.isEmpty()) {
            SpaceInvaders.instance.running = false;
            return;
        }
    }

    @Override
    public void update() {
        if (handler.isKeyDown(KeyEvent.VK_LEFT)) {
            int i = 0;
            for (GameButton gb : allButtons) {
                if (gb.isButtonSelected()) {
                    gb.unselect();
                    i = allButtons.indexOf(gb) - 1;
                    break;
                }
            }
            if (i < 0) {
                i = allButtons.size() - 1;
            }
            allButtons.get(i).select();
            handler.keys[KeyEvent.VK_LEFT] = false;
        }
        if (handler.isKeyDown(KeyEvent.VK_RIGHT)) {
            int i = 0;
            for (GameButton gb : allButtons) {
                if (gb.isButtonSelected()) {
                    gb.unselect();
                    i = allButtons.indexOf(gb) + 1;
                    break;
                }
            }
            if (i >= allButtons.size()) {
                i = 0;
            }
            allButtons.get(i).select();
            handler.keys[KeyEvent.VK_RIGHT] = false;
        }
        if (handler.isKeyDown(KeyEvent.VK_ENTER)) {
            GameButton selectedButton = null;
            for (GameButton gb : allButtons) {
                if (gb.isButtonSelected()) {
                    selectedButton = gb;
                    break;
                }
            }
            handler.keys[KeyEvent.VK_ENTER] = false;
            switch (selectedButton.getID()) {
                case 0:
                    SpaceInvaders.instance.frame.label.changeGameLayer(new StartLayer(handler));
                    break;
                case 1:
                    SpaceInvaders.instance.running = false;
                    break;
                default:
                    SpaceInvaders.instance.running = false;
                    break;
            }
        }
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
        int i = 0;
        int x = 280;
        int y = 180;
        for (GameButton gb : allButtons) {
            gb.draw(g, x, y);
            if (i >= 1) {
                i = 0;
                x = 280;
                y += 50;
            } else {
                i++;
                x += 110;
            }
        }
    }
}
