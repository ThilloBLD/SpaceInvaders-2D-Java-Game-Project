package spaceinvaders.main;

import spaceinvaders.gui.GameFrame;

public final class SpaceInvaders {

    // Gibt an, ob das Spiel läuft
    public boolean running = false;

    // Instanz des Spiels
    public static SpaceInvaders instance;

    // Das Hauptfenster des Spiels
    public GameFrame frame;

    // Der Konstruktor für das SpaceInvaders-Objekt
    public SpaceInvaders() {
        // Setzen der Instanz
        instance = this;

        // Initialisierung des Spiels
        initGame();

        // Hauptspielschleife
        gameLoop();

        // Beenden des Spiels
        stopGame();
    }

    // Initialisierung des Spiels
    private void initGame() {
        // Erstellen des Hauptfensters
        frame = new GameFrame(800, 550);

        // Setzen des Spielstatus auf "läuft"
        running = true;
    }

    // Die Hauptspielschleife
    private void gameLoop() {
        // Solange das Spiel läuft
        while (running) {
            // Aktualisiere das Hauptfenster
            frame.update();
            // Neuzeichnen des Hauptfensters
            frame.repaint();
            try {
                // Kurze Pause, um die CPU-Last zu reduzieren
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Beenden des Spiels
    private void stopGame() {
        // Beenden des Programms
        System.exit(0);
    }

    // Die Hauptmethode, die das Spiel startet
    public static void main(String[] args) {
        // Erstellen einer neuen Instanz von SpaceInvaders
        new SpaceInvaders();
    }

}
