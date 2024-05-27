package spaceinvaders.main;

import spaceinvaders.gui.GameFrame;

public final class SpaceInvaders {

    // Flag, das angibt, ob das Spiel läuft
    public boolean running = false;
    // Statische Instanz des Spiels
    public static SpaceInvaders instance;
    // Das Hauptfenster des Spiels
    public GameFrame frame;

    // Konstruktor des Spiels
    public SpaceInvaders() {
        // Setzen der Instanz
        instance = this;

        // Aufrufen der Methoden zur Initialisierung, Hauptschleife und Stoppen des Spiels
        initGame();
        gameLoop();
        stopGame();
    }

    // Initialisiert das Spiel
    private void initGame() {
        // Erstellen eines neuen Spielrahmens mit einer bestimmten Größe
        frame = new GameFrame(800, 550);
        // Setzen des Running-Flags auf true
        running = true;
    }

    // Die Hauptspielschleife
    private void gameLoop() {
        while (running) {
            // Aktualisieren des Spielframes
            frame.update();
            // Neuzeichnen des Spielframes
            frame.repaint();
            // Ein kurzes Warten, um die CPU nicht zu stark zu belasten
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Testausgabe (kann entfernt werden)
            // System.out.print("Game Loop");
        }
    }

    // Stoppt das Spiel
    private void stopGame() {
        // Beendet das Programm
        System.exit(0);
    }

    // Der Einstiegspunkt des Programms
    public static void main(String[] args) {
        // Starten einer neuen Instanz des Spiels
        new SpaceInvaders();
    }
}
