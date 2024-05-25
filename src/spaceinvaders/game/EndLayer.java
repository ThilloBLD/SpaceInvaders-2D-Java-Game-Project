package spaceinvaders.game;

import spaceinvaders.gui.elements.GameButton;
import spaceinvaders.handler.InputHandler;
import spaceinvaders.main.SpaceInvaders;
import spaceinvaders.texture.Texture;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EndLayer implements GameLabelLayer {

    private final InputHandler handler; // Eingabebehandler
    private ArrayList<GameButton> allButtons = new ArrayList<>(); // Liste aller Buttons
    private final boolean gameWon; // Gibt an, ob das Spiel gewonnen wurde

    // Konstruktor für das EndLayer-Objekt
    public EndLayer(boolean gameWon, InputHandler handler) {
        this.gameWon = gameWon;
        this.handler = handler;
        init(); // Initialisiert die Buttons
    }

    // Initialisiert die Buttons und deren Aussehen
    private void init() {
        Texture unselectedButton = new Texture(100, 40);
        unselectedButton.setColor(Color.YELLOW);
        unselectedButton.setRandColor(Color.BLACK);
        Texture selectedButton = new Texture(100, 40);
        selectedButton.setColor(Color.CYAN);
        selectedButton.setRandColor(Color.BLACK);
        allButtons.add(new GameButton(0, true, selectedButton, unselectedButton, "Replay")); // Button zum Neustart des Spiels
        allButtons.add(new GameButton(1, false, selectedButton, unselectedButton, "Exit")); // Button zum Beenden des Spiels
        if (allButtons.isEmpty()) {
            SpaceInvaders.instance.running = false; // Beendet das Spiel, wenn keine Buttons vorhanden sind
            return;
        }
    }

    // Aktualisiert den Zustand des EndLayers
    @Override
    public void update() {
        // Überprüft, ob die linke Pfeiltaste gedrückt wurde
        if (handler.isKeyDown(KeyEvent.VK_LEFT)) {
            int i = 0;
            for (GameButton gb : allButtons) {
                if (gb.isButtonSelected()) {
                    gb.unselect();
                    i = allButtons.indexOf(gb) - 1; // Verschiebt die Auswahl zum vorherigen Button
                    break;
                }
            }
            if (i < 0) {
                i = allButtons.size() - 1; // Springt zum letzten Button, wenn der erste erreicht wurde
            }
            allButtons.get(i).select();
            handler.keys[KeyEvent.VK_LEFT] = false;
        }

        // Überprüft, ob die rechte Pfeiltaste gedrückt wurde
        if (handler.isKeyDown(KeyEvent.VK_RIGHT)) {
            int i = 0;
            for (GameButton gb : allButtons) {
                if (gb.isButtonSelected()) {
                    gb.unselect();
                    i = allButtons.indexOf(gb) + 1; // Verschiebt die Auswahl zum nächsten Button
                    break;
                }
            }
            if (i >= allButtons.size()) {
                i = 0; // Springt zum ersten Button, wenn der letzte erreicht wurde
            }
            allButtons.get(i).select();
            handler.keys[KeyEvent.VK_RIGHT] = false;
        }

        // Überprüft, ob die Enter-Taste gedrückt wurde
        if (handler.isKeyDown(KeyEvent.VK_ENTER)) {
            GameButton selectedButton = null;
            for (GameButton gb : allButtons) {
                if (gb.isButtonSelected()) {
                    selectedButton = gb;
                    break;
                }
            }
            handler.keys[KeyEvent.VK_ENTER] = false;

            // Aktion basierend auf dem ausgewählten Button
            switch (selectedButton.getID()) {
                case 0: // Neustart des Spiels
                    SpaceInvaders.instance.frame.label.changeGameLayer(new StartLayer(handler));
                    break;
                case 1: // Beenden des Spiels
                    SpaceInvaders.instance.running = false;
                    break;
                default:
                    SpaceInvaders.instance.running = false;
                    break;
            }
        }
    }

    // Zeichnet das EndLayer und die Buttons
    @Override
    public void draw(Graphics g) {
        String textSpeicher;
        if (gameWon) {
            textSpeicher = "Du hast gewonnen! :D";
        } else {
            textSpeicher = "Du hast verloren! :(";
        }

        // Zeichnet den Gewinn-/Verlieren-Text in der Mitte des Bildschirms
        g.drawString(textSpeicher, (SpaceInvaders.instance.frame.label.getWidth() - g.getFontMetrics().stringWidth(textSpeicher)) / 2, SpaceInvaders.instance.frame.label.getHeight() / 2);

        int i = 0;
        int x = 280;
        int y = 180;

        // Zeichnet die Buttons
        for (GameButton gb : allButtons) {
            gb.draw(g, x, y);
            if (i >= 1) {
                i = 0;
                x = 280;
                y += 50; // Verschiebt die Buttons nach unten
            } else {
                i++;
                x += 110; // Verschiebt die Buttons nach rechts
            }
        }
    }
}
