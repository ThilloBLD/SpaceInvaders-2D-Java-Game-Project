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

    // Instanzvariable für den Input-Handler
    private final InputHandler handler;
    // Liste aller GameButtons
    private ArrayList<GameButton> allButtons = new ArrayList<>();

    // Konstruktor, der den Input-Handler übernimmt
    public StartLayer(InputHandler handler) {
        this.handler = handler;
        init(); // Initialisiert die Schaltflächen
    }

    // Methode zur Initialisierung der Schaltflächen
    private void init() {
        Texture unselectedButton = new Texture(100, 40); // Erstellt Textur für unselektierte Schaltflächen
        unselectedButton.setColor(Color.YELLOW);
        unselectedButton.setRandColor(Color.BLACK);
        Texture selectedButton = new Texture(100, 40); // Erstellt Textur für selektierte Schaltflächen
        selectedButton.setColor(Color.CYAN);
        selectedButton.setRandColor(Color.BLACK);
        // Fügt verschiedene Schaltflächen zur Liste hinzu
        allButtons.add(new GameButton(0, true, selectedButton, unselectedButton, "FractalSpace"));
        allButtons.add(new GameButton(1, false, selectedButton, unselectedButton, "Fractal"));
        allButtons.add(new GameButton(2, false, selectedButton, unselectedButton, "Calm"));
        allButtons.add(new GameButton(3, false, selectedButton, unselectedButton, "Spaceonaut"));
        allButtons.add(new GameButton(4, false, selectedButton, unselectedButton, "Space"));
        if (allButtons.isEmpty()) {
            startGame(-1); // Startet das Spiel mit einer ungültigen Hintergrund-ID, wenn keine Schaltflächen existieren
            return;
        }
    }

    // Methode zum Starten des Spiels mit einer bestimmten Hintergrund-ID
    private void startGame(int backgroundID) {
        SpaceInvaders.instance.frame.label.changeGameLayer(new ThemeLayer(handler, backgroundID));
    }

    @Override
    public void update() {
        // Überprüft, ob die linke Pfeiltaste gedrückt wurde
        if (handler.isKeyDown(KeyEvent.VK_LEFT)) {
            int i = 0;
            for (GameButton gb : allButtons) {
                if (gb.isButtonSelected()) {
                    gb.unselect(); // Deselektiert den aktuell ausgewählten Button
                    i = allButtons.indexOf(gb) - 1;
                    break;
                }
            }
            if (i < 0) {
                i = allButtons.size() - 1;
            }
            allButtons.get(i).select(); // Selektiert den vorherigen Button
            handler.keys[KeyEvent.VK_LEFT] = false;
        }
        // Überprüft, ob die rechte Pfeiltaste gedrückt wurde
        if (handler.isKeyDown(KeyEvent.VK_RIGHT)) {
            int i = 0;
            for (GameButton gb : allButtons) {
                if (gb.isButtonSelected()) {
                    gb.unselect(); // Deselektiert den aktuell ausgewählten Button
                    i = allButtons.indexOf(gb) + 1;
                    break;
                }
            }
            if (i >= allButtons.size()) {
                i = 0;
            }
            allButtons.get(i).select(); // Selektiert den nächsten Button
            handler.keys[KeyEvent.VK_RIGHT] = false;
        }
        // Überprüft, ob die Eingabetaste gedrückt wurde
        if (handler.isKeyDown(KeyEvent.VK_ENTER)) {
            GameButton selectedButton = null;
            for (GameButton gb : allButtons) {
                if (gb.isButtonSelected()) {
                    selectedButton = gb;
                    break;
                }
            }
            handler.keys[KeyEvent.VK_ENTER] = false;
            startGame(selectedButton.getID()); // Startet das Spiel mit der ID des ausgewählten Buttons
        }
    }

    @Override
    public void draw(Graphics g) {
        int i = 0;
        int x = 280;
        int y = 180;
        for (GameButton gb : allButtons) {
            gb.draw(g, x, y); // Zeichnet den Button
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
