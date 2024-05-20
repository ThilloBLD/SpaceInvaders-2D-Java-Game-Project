package spaceinvaders.texture;

import javax.imageio.ImageIO;
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
}