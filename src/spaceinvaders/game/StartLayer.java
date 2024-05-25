package spaceinvaders.game;

import spaceinvaders.gui.elements.GameButton;
import spaceinvaders.handler.InputHandler;
import spaceinvaders.main.SpaceInvaders;
import spaceinvaders.texture.Texture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class StartLayer implements GameLabelLayer {

    private final InputHandler handler;
    private ArrayList<GameButton> allButtons = new ArrayList<>();

    public StartLayer(InputHandler handler) {
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
        allButtons.add(new GameButton(0, true, selectedButton, unselectedButton, "FractalSpace"));
        allButtons.add(new GameButton(1,false, selectedButton, unselectedButton, "Fractal"));
        allButtons.add(new GameButton(2,false, selectedButton, unselectedButton, "Calm"));
        allButtons.add(new GameButton(3,false, selectedButton, unselectedButton, "Spaceonaut"));
        allButtons.add(new GameButton(4,false, selectedButton, unselectedButton, "Space"));
        if (allButtons.isEmpty()) {
            startGame(-1);
            return;
        }
    }

    private void startGame(int backgroundID) {
        SpaceInvaders.instance.frame.label.changeGameLayer(new ThemeLayer(handler, backgroundID));
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
            startGame(selectedButton.getID());
        }
    }

    @Override
    public void draw(Graphics g) {
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
