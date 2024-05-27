package spaceinvaders.texture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public final class ResourceManager {

    private static ResourceManager resourceManagerInstance;
    private HashMap<TextureType,Texture> map = new HashMap<>();

    // Privater Konstruktor, um die Instanziierung von außerhalb zu verhindern
    private ResourceManager() {
        loadTextures();
    }

    // Statische Methode, um eine Instanz der Klasse zu erhalten
    public static ResourceManager getInstance() {
        if (resourceManagerInstance == null) {
            resourceManagerInstance = new ResourceManager();
        }
        return resourceManagerInstance;
    }

    // Gibt eine Texture anhand des Texture-Typs zurück
    public Texture getTexture(TextureType textureType) {
        return map.get(textureType);
    }

    // Lädt alle Texturen
    private void loadTextures() {
        for (TextureType textureType : TextureType.values()) {
            map.put(textureType, new Texture(getBufferedImageFromAssets(textureType)));
        }
    }

    // Lädt ein BufferedImage aus den Assets anhand des Dateinamens
    public static BufferedImage getBufferedImageFromAssets(TextureType textureType) {
        try {
            return ImageIO.read(ResourceManager.class.getClassLoader().getResource("assets/" + textureType.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lädt ein ImageIcon aus den Assets anhand des Dateinamens
    public static ImageIcon getImageIconFromAssets(String fileName) {
        return new ImageIcon(ResourceManager.class.getClassLoader().getResource("assets/" + fileName));
    }

    // Lädt ein ImageIcon anhand einer Hintergrund-ID
    public static ImageIcon getImageIconByBackgroundID(int backgroundID) {
        switch (backgroundID) {
            case 0:
                return getImageIconFromAssets("fractalspace.gif");
            case 1:
                return getImageIconFromAssets("fractal.gif");
            case 2:
                return getImageIconFromAssets("calm.gif");
            case 3:
                return getImageIconFromAssets("spaceonaut.gif");
            case 4:
                return getImageIconFromAssets("space.gif");
            default:
                return getImageIconFromAssets("calm.gif");
        }
    }
}
