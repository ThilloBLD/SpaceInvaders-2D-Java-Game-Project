package spaceinvaders.game;

import spaceinvaders.gui.elements.GameButton;
import spaceinvaders.handler.InputHandler;
import spaceinvaders.main.SpaceInvaders;
import spaceinvaders.texture.Texture;
import spaceinvaders.texture.Theme;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ThemeLayer implements GameLabelLayer {

    private final InputHandler handler;
    private final int backgroundID;
    private ArrayList<GameButton> allButtons = new ArrayList<>();


    public ThemeLayer(InputHandler handler, int backgroundID) {
        
        this.handler = handler;
        this.backgroundID = backgroundID;
        this.init(); 
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
            startGame(Theme.values()[selectedButton.getID()]);
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

    private void startGame(Theme theme) {
        SpaceInvaders.instance.frame.label.changeGameLayer(new GameLayer(handler, backgroundID, theme));
        
    }

    private void init() {
        Texture unselectedButton = new Texture(100, 40);
        unselectedButton.setColor(Color.YELLOW);
        unselectedButton.setRandColor(Color.BLACK);
        Texture selectedButton = new Texture(100, 40);
        selectedButton.setColor(Color.CYAN);
        selectedButton.setRandColor(Color.BLACK);
        allButtons.add(new GameButton(Theme.standard.ordinal(), true, selectedButton, unselectedButton, "Standard"));
        allButtons.add(new GameButton(Theme.milfhunter.ordinal(),false, selectedButton, unselectedButton, "Milfhunter"));
        if (allButtons.isEmpty()) {
            startGame(Theme.standard);
            return;
        }
    }

}
