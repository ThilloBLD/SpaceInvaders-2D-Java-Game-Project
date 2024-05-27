package spaceinvaders.handler;

import spaceinvaders.main.SpaceInvaders;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class InputHandler implements KeyListener, WindowListener {

    // Array, das den Status aller Tasten speichert
        public boolean[] keys = new boolean[68836 /* 68836 is the maximum value of a key code */];

    // Überprüft, ob eine bestimmte Taste gedrückt ist
        public boolean isKeyDown(int keyCode) {
            return keys[keyCode];
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // Behandlung des Tastendrucks
            int keyCode = e.getKeyCode();
            if (keyCode >= 0 && keyCode < keys.length) {
                keys[keyCode] = true; // Setzt den entsprechenden Eintrag im Array auf 'true', um anzuzeigen, dass die Taste gedrückt wurde
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // Behandlung des Loslassens der Taste
            int keyCode = e.getKeyCode();
            if (keyCode >= 0 && keyCode < keys.length) {
                keys[keyCode] = false; // Setzt den entsprechenden Eintrag im Array auf 'false', um anzuzeigen, dass die Taste losgelassen wurde
            }
        }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        // Schließt das Spiel, wenn das Fenster geschlossen wird
        SpaceInvaders.instance.running = false;
    }

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
