package spaceinvaders.texture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public final class ResourceManager {

    // Statische Instanz des ResourceManagers (Singleton-Muster)
    private static ResourceManager resourceManagerInstance;
    // HashMap zum Speichern der geladenen Texturen
    private HashMap<TextureType,Texture> map = new HashMap<>();

    // Privater Konstruktor, der die Texturen lädt
    private ResourceManager() {
        loadTextures();
    }

    // Statische Methode, um die Instanz des ResourceManagers zu erhalten
    public static ResourceManager getInstance() {
        if (resourceManagerInstance == null) {
            resourceManagerInstance = new ResourceManager();
        }
        return resourceManagerInstance;
    }

    // Gibt die Texture-Instanz für den angegebenen TextureType zurück
    public Texture getTexture(TextureType textureType) {
        return map.get(textureType);
    }

    // Lädt alle Texturen aus den Assets
    private void loadTextures() {
        for (TextureType textureType : TextureType.values()) {
            map.put(textureType, new Texture(getBufferedImageFromAssets(textureType)));
        }
    }

    // Lädt ein BufferedImage aus den Assets basierend auf dem TextureType
    public static BufferedImage getBufferedImageFromAssets(TextureType textureType) {
        try {
            return ImageIO.read(ResourceManager.class.getClassLoader().getResource("assets/" + textureType.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lädt ein ImageIcon aus den Assets basierend auf den Dateinamen
    public static ImageIcon getImageIconFromAssets(String fileName) {
        return new ImageIcon(ResourceManager.class.getClassLoader().getResource("assets/" + fileName));
    }

    // Lädt ein ImageIcon aus den Assets basierend auf die Hintergrund-ID
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