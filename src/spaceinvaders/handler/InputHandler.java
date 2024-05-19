package spaceinvaders.handler;

import spaceinvaders.main.SpaceInvaders;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class InputHandler implements KeyListener, WindowListener {

        public boolean[] keys = new boolean[68836 /* 68836 is the maximum value of a key code */];

        public boolean isKeyDown(int keyCode) {
            return keys[keyCode];
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode >= 0 && keyCode < keys.length) {
                keys[keyCode] = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode >= 0 && keyCode < keys.length) {
                keys[keyCode] = false;
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
