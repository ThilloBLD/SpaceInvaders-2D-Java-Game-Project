package spaceinvaders.texture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public final class ResourceManager {
    
    private static ResourceManager resourceManagerInstance;
    private HashMap<TextureType,Texture> map = new HashMap<>();

    private ResourceManager() {
        loadTextures();
    }

    public static ResourceManager getInstance() {
        if (resourceManagerInstance == null) {
            resourceManagerInstance = new ResourceManager();
        }
        return resourceManagerInstance;
    }

    public Texture getTexture(TextureType textureType) {
        return map.get(textureType);
    }

    private void loadTextures() {
        for (TextureType textureType : TextureType.values()) {
            map.put(textureType, new Texture(getBufferedImageFromAssets(textureType)));
        }
    }

    public static BufferedImage getBufferedImageFromAssets(TextureType textureType) {
        try {
            return ImageIO.read(ResourceManager.class.getClassLoader().getResource("assets/" + textureType.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ImageIcon getImageIconFromAssets(String fileName) {
        return new ImageIcon(ResourceManager.class.getClassLoader().getResource("assets/" + fileName));
    }

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