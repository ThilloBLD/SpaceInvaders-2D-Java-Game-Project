package spaceinvaders.main;

import spaceinvaders.gui.GameFrame;


public final class SpaceInvaders {

    // Sagt an ob das Spiel läuft
    public boolean running = false;
    // Instanz des Spiels
    public static SpaceInvaders instance;
    public GameFrame frame;

    public SpaceInvaders() {
        // Setzen der Instanz
        instance = this;

         //aufrufen der Methoden
        initGame();
        gameLoop();
        stopGame();

    }

    private void initGame() {
        frame = new GameFrame(800, 600);
        // Hier kommt die Initialisierung hin
        running = true;
    }

    private void gameLoop() {
        while (running) {
            // Hier kommt die Spiellogik hin
            frame.update();
            frame.repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Testausgabe
            //System.out.print("Game Loop");
        }
    }

    private void stopGame() {
        System.exit(0);
    }

    public static void main(String[] args) {

        new SpaceInvaders();

    }

}
