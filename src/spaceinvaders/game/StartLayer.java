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

    // Konstruktor für die StartLayer
    public StartLayer(InputHandler handler) {
        this.handler = handler;
        init(); // Initialisierung der StartLayer
    }

    // Initialisierung der StartLayer
    private void init() {
        // Erstellung der Textur für unselektierte und selektierte Buttons
        Texture unselectedButton = new Texture(100, 40);
        unselectedButton.setColor(Color.YELLOW);
        unselectedButton.setRandColor(Color.BLACK);
        Texture selectedButton = new Texture(100, 40);
        selectedButton.setColor(Color.CYAN);
        selectedButton.setRandColor(Color.BLACK);
        // Erstellung der GameButtons und Hinzufügen zur Liste
        allButtons.add(new GameButton(0, true, selectedButton, unselectedButton, "FractalSpace"));
        allButtons.add(new GameButton(1,false, selectedButton, unselectedButton, "Fractal"));
        allButtons.add(new GameButton(2,false, selectedButton, unselectedButton, "Calm"));
        allButtons.add(new GameButton(3,false, selectedButton, unselectedButton, "Spaceonaut"));
        allButtons.add(new GameButton(4,false, selectedButton, unselectedButton, "Space"));
        // Überprüfung, ob die Liste leer ist
        if (allButtons.isEmpty()) {
            startGame(-1); // Startet das Spiel mit Standardhintergrund
            return;
        }
    }

    // Startet das Spiel mit dem ausgewählten Hintergrund
    private void startGame(int backgroundID) {
        SpaceInvaders.instance.frame.label.changeGameLayer(new ThemeLayer(handler, backgroundID));
    }

    // Aktualisierung der StartLayer
    @Override
    public void update() {
        // Überprüfung, ob die linke Taste gedrückt wurde
        if (handler.isKeyDown(KeyEvent.VK_LEFT)) {
            int i = 0;
            // Durchlaufen der GameButtons
            for (GameButton gb : allButtons) {
                if (gb.isButtonSelected()) {
                    gb.unselect(); // Button deselektieren
                    i = allButtons.indexOf(gb) - 1;
                    break;
                }
            }
            // Zurücksetzen auf das Ende der Liste, falls nötig
            if (i < 0) {
                i = allButtons.size() - 1;
            }
            allButtons.get(i).select(); // Button auswählen
            handler.keys[KeyEvent.VK_LEFT] = false;
        }
        // Überprüfung, ob die rechte Taste gedrückt wurde
        if (handler.isKeyDown(KeyEvent.VK_RIGHT)) {
            int i = 0;
            // Durchlaufen der GameButtons
            for (GameButton gb : allButtons) {
                if (gb.isButtonSelected()) {
                    gb.unselect(); // Button deselektieren
                    i = allButtons.indexOf(gb) + 1;
                    break;
                }
            }
            // Zurücksetzen auf den Anfang der Liste, falls nötig
            if (i >= allButtons.size()) {
                i = 0;
            }
            allButtons.get(i).select(); // Button auswählen
            handler.keys[KeyEvent.VK_RIGHT] = false;
        }
        // Überprüfung, ob die Enter-Taste gedrückt wurde
        if (handler.isKeyDown(KeyEvent.VK_ENTER)) {
            GameButton selectedButton = null;
            // Durchlaufen der GameButtons
            for (GameButton gb : allButtons) {
                if (gb.isButtonSelected()) {
                    selectedButton = gb;
                    break;
                }
            }
            handler.keys[KeyEvent.VK_ENTER] = false;
            startGame(selectedButton.getID()); // Starten des Spiels mit dem ausgewählten Hintergrund
        }
    }

    // Zeichnen der StartLayer
    @Override
    public void draw(Graphics g) {
        int i = 0;
        int x = 280;
        int y = 180;
        // Durchlaufen der GameButtons und Zeichnen
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
