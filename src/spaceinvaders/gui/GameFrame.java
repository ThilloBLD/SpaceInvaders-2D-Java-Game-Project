package spaceinvaders.gui;

import spaceinvaders.handler.InputHandler;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private int width;
    private int height;
    private GameLabel label;
    public InputHandler inputHandler;

    public GameFrame(int width, int height) {
        this.width = width;
        this.height = height;
        init();
        initFrame();
    }
    private void init() {
        // Initialisierung des InputHandlers
        inputHandler = new InputHandler();
        label = new GameLabel(inputHandler);

    }
    private void initFrame() {
        //Setzen der Eigenschaften des Frames
        setName("Space Invaders");
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        addKeyListener(inputHandler);
        add(label);
        setVisible(true);
    }

    public void update() {
        // Weitergabe der Aktualisierung an das Label
        label.update();
    }
    public int getLabelWidth() {
        return label.getWidth();
    }
    public int getLabelHeight() {
        return label.getHeight();
    }
}
