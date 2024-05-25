package spaceinvaders.texture;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Texture {

    private final BufferedImage img;
    private int[] defaultPixels;

    public Texture(BufferedImage img) {
        this.img = img;
    }

    public Texture(int width, int height) {
        this(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public Texture(int width, int height, int imageType) {
        this(new BufferedImage(width, height, imageType));
    }

    public int[] getPixels() {
        return ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
    }

    public void setPixel(int[] pixel) {
        int[] pixi = getPixels();
        if(pixel.length == pixi.length) {
            for(int i = 0; i < pixi.length; i++) {
                pixi[i] = pixel[i];
            }
        }
    }

    public BufferedImage getBufferedImage() {
        return this.img;
    }

    public void setRGB(int rgb, int pixel) {
        int[] pixi = getPixels();
        if(pixel >= 0 && pixel < pixi.length) {
            pixi[pixel] = rgb;
        } else {
            throw new ArrayIndexOutOfBoundsException(pixel + " > " + pixi.length);
        }
    }

    public void setRGB(Color color, int pixel) {
        setRGB(color.getRGB(), pixel);
    }

    public void setRGB(int x, int y, int rgb) {
        if(!isLocationInBound(x, y)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        setRGB(rgb, (y * img.getHeight()) + x);
    }

    public void setRGB(int x, int y, Color color) {
        setRGB(x, y, color.getRGB());
    }

    public int getRGB(int pixel) {
        int[] pixi = getPixels();
        if(pixel >= 0 && pixel < pixi.length) {
            return pixi[pixel];
        } else {
            throw new ArrayIndexOutOfBoundsException(pixel + " > " + pixi.length);
        }
    }

    public int getRGB(int x, int y) {
        if(isLocationInBound(x, y)) {
            int[] pixi = getPixels();
            return pixi[(y * img.getHeight()) + x];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void setColor(Color color) {
        int[] pixi = getPixels();
        for(int i = 0; i < pixi.length; i++) {
            pixi[i] = color.getRGB();
        }
    }

    public void setColor(int rgb) {
        int[] pixi = getPixels();
        for(int i = 0; i < pixi.length; i++) {
            pixi[i] = rgb;
        }
    }

    public void replaceColor(int rgb, int replaceRGB) {
        int[] pixi = getPixels();
        for(int i = 0; i < pixi.length; i++) {
            if(pixi[i] == rgb) {
                pixi[i] = replaceRGB;
            }
        }
    }

    public void replaceColor(Color replace, Color replaceColor) {
        replaceColor(replace.getRGB(), replaceColor.getRGB());
    }

    public void copyDefault() {
        this.defaultPixels = getPixels().clone();
    }

    public void loadDefault() {
        if(isDefaultExist()) {
            setPixel(defaultPixels);
        }
    }

    public boolean isDefaultExist() {
        return defaultPixels != null;
    }

    public int[] getDefaultPixel() {
        return defaultPixels;
    }

    public void drawImage(int... rgb) {
        int[] pixi = getPixels();
        if(rgb.length > pixi.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for(int i = 0; i < rgb.length; i++) {
            pixi[i] = rgb[i];
        }
    }

    public void drawImage(Color... colors) {
        int[] pixi = getPixels();
        if(colors.length > pixi.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for(int i = 0; i < colors.length; i++) {
            pixi[i] = colors[i].getRGB();
        }
    }

    public boolean isLocationInBound(int x, int y) {
        if(x < 0 || x > img.getWidth() || y < 0 || y > img.getHeight()) {
            return false;
        }
        return true;
    }

    public int getWidth() {
        return img.getWidth();
    }

    public int getHeight() {
        return img.getHeight();
    }

    public void setRandColor(Color color) {
        setRandColor(color.getRGB());
    }

    public void setRandColor(int color) {
        for(int i = 0; i < getWidth() * getHeight(); i++) {
            int a = i / getWidth();
            int b = i % getWidth();
            if(a < 1) {
                setRGB(color, i);
            } else {
                if((getWidth() * getHeight()) - i <= getWidth()) {
                    setRGB(color, i);
                } else {
                    if(b == 0 || b == getWidth() - 1) {
                        setRGB(color, i);
                    }
                }
            }
        }
    }

}