package spaceinvaders.handler;

import spaceinvaders.main.SpaceInvaders;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class InputHandler implements KeyListener, WindowListener {

    // Ein Array, um den Status der Tasten zu verfolgen
    public boolean[] keys = new boolean[68836]; // 68836 ist der maximale Wert eines Tastencodes

    // Überprüft, ob eine bestimmte Taste gedrückt ist
    public boolean isKeyDown(int keyCode) {
        return keys[keyCode];
    }

    // Reaktion auf das Drücken einer Taste
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = true; // Setze den Status der Taste auf gedrückt
        }
    }

    // Reaktion auf das Loslassen einer Taste
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = false; // Setze den Status der Taste auf nicht gedrückt
        }
    }

    // Reaktion auf das Tippen einer Taste (wird nicht verwendet)
    @Override
    public void keyTyped(KeyEvent e) {
    }

    // Implementierung der Methoden des WindowListener-Interfaces (nicht alle werden verwendet)
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    // Aktion beim Schließen des Fensters: Beende das Spiel
    @Override
    public void windowClosed(WindowEvent e) {
        SpaceInvaders.instance.running = false; // Setze die Laufvariable von SpaceInvaders auf false
    }

    // Implementierungen der restlichen Methoden des WindowListener-Interfaces (nicht verwendet)
    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
