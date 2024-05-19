package spaceinvaders.texture;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Texture {

    private final BufferedImage img;

    public Texture(BufferedImage img) {
        this.img = img;
    }

    public Texture(int width, int height) {

        this(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));

    }

    public BufferedImage getBufferedImage() {
        return img;
    }

    public int getWidth() {
        return img.getWidth();
    }

    public int getHeight() {
        return img.getHeight();
    }
    public int[] getPixels() {
        // Permanently returns the pixels of the image
        return ((DataBufferInt)this.img.getRaster().getDataBuffer()).getData();
    }
    public void setColor(Color c) {
        // Set the color of the image
        int[] pixels = getPixels();
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = c.getRGB();
        }
    }
}
