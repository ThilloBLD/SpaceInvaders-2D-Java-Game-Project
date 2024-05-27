package spaceinvaders.texture;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Texture {

    // Instanzvariable für das Bild
    private final BufferedImage img;
    // Array zum Speichern der Standardpixel
    private int[] defaultPixels;

    // Konstruktor, der ein vorhandenes BufferedImage verwendet
    public Texture(BufferedImage img) {
        this.img = img;
    }

    // Konstruktor, um ein neues BufferedImage mit bestimmter Breite und Höhe zu erstellen
    public Texture(int width, int height) {
        this(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    // Konstruktor, um ein neues BufferedImage mit bestimmter Breite, Höhe und Bildtyp zu erstellen
    public Texture(int width, int height, int imageType) {
        this(new BufferedImage(width, height, imageType));
    }

    // Methode, um die Pixel des Bildes abzurufen
    public int[] getPixels() {
        return ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
    }

    // Methode, um einen bestimmten Pixel zu setzen
    public void setPixel(int[] pixel) {
        int[] pixi = getPixels();
        if(pixel.length == pixi.length) {
            for(int i = 0; i < pixi.length; i++) {
                pixi[i] = pixel[i];
            }
        }
    }

    // Methode, um das BufferedImage abzurufen
    public BufferedImage getBufferedImage() {
        return this.img;
    }

    // Methode, um einen Pixel an einer bestimmten Position zu setzen (über RGB)
    public void setRGB(int rgb, int pixel) {
        int[] pixi = getPixels();
        if(pixel >= 0 && pixel < pixi.length) {
            pixi[pixel] = rgb;
        } else {
            throw new ArrayIndexOutOfBoundsException(pixel + " > " + pixi.length);
        }
    }

    // Methode, um einen Pixel an einer bestimmten Position zu setzen (über Color)
    public void setRGB(Color color, int pixel) {
        setRGB(color.getRGB(), pixel);
    }

    // Methode, um einen Pixel an einer bestimmten Position zu setzen (über Koordinaten und RGB)
    public void setRGB(int x, int y, int rgb) {
        if(!isLocationInBound(x, y)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        setRGB(rgb, (y * img.getHeight()) + x);
    }

    // Methode, um einen Pixel an einer bestimmten Position zu setzen (über Koordinaten und Color)
    public void setRGB(int x, int y, Color color) {
        setRGB(x, y, color.getRGB());
    }

    // Methode, um den Farbwert eines bestimmten Pixels abzurufen
    public int getRGB(int pixel) {
        int[] pixi = getPixels();
        if(pixel >= 0 && pixel < pixi.length) {
            return pixi[pixel];
        } else {
            throw new ArrayIndexOutOfBoundsException(pixel + " > " + pixi.length);
        }
    }

    // Methode, um den Farbwert an einer bestimmten Position abzurufen
    public int getRGB(int x, int y) {
        if(isLocationInBound(x, y)) {
            int[] pixi = getPixels();
            return pixi[(y * img.getHeight()) + x];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    // Methode, um allen Pixeln eine bestimmte Farbe zuzuweisen
    public void setColor(Color color) {
        int[] pixi = getPixels();
        for(int i = 0; i < pixi.length; i++) {
            pixi[i] = color.getRGB();
        }
    }

    // Methode, um allen Pixeln einen bestimmten Farbwert zuzuweisen
    public void setColor(int rgb) {
        int[] pixi = getPixels();
        for(int i = 0; i < pixi.length; i++) {
            pixi[i] = rgb;
        }
    }

    // Methode, um alle Pixel eines bestimmten Farbwerts durch einen anderen zu ersetzen
    public void replaceColor(int rgb, int replaceRGB) {
        int[] pixi = getPixels();
        for(int i = 0; i < pixi.length; i++) {
            if(pixi[i] == rgb) {
                pixi[i] = replaceRGB;
            }
        }
    }

    // Überladung der Methode replaceColor, die zwei Farben übernimmt
    public void replaceColor(Color replace, Color replaceColor) {
        replaceColor(replace.getRGB(), replaceColor.getRGB());
    }

    // Methode zum Speichern der aktuellen Pixel als Standard
    public void copyDefault() {
        this.defaultPixels = getPixels().clone();
    }

    // Methode zum Laden der gespeicherten Standardpixel
    public void loadDefault() {
        if(isDefaultExist()) {
            setPixel(defaultPixels);
        }
    }

    // Methode zum Überprüfen, ob Standardpixel existieren
    public boolean isDefaultExist() {
        return defaultPixels != null;
    }

    // Methode zum Abrufen der Standardpixel
    public int[] getDefaultPixel() {
        return defaultPixels;
    }

    // Methode zum Zeichnen eines Bildes mit Pixeln
    public void drawImage(int... rgb) {
        int[] pixi = getPixels();
        if(rgb.length > pixi.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for(int i = 0; i < rgb.length; i++) {
            pixi[i] = rgb[i];
        }
    }

    // Überladung der Methode drawImage, die Farben übernimmt
    public void drawImage(Color... colors) {
        int[] pixi = getPixels();
        if(colors.length > pixi.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for(int i = 0; i < colors.length; i++) {
            pixi[i] = colors[i].getRGB();
        }
    }

    // Methode zur Überprüfung, ob die Koordinaten innerhalb der Bildgrenzen liegen
    public boolean isLocationInBound(int x, int y) {
        if(x < 0 || x > img.getWidth() || y < 0 || y > img.getHeight()) {
            return false;
        }
        return true;
    }

    // Methode zum Abrufen der Bildbreite
    public int getWidth() {
        return img.getWidth();
    }

    // Methode zum Abrufen der Bildhöhe
    public int getHeight() {
        return img.getHeight();
    }

    // Methode zum Setzen der Randfarbe
    public void setRandColor(Color color) {
        setRandColor(color.getRGB());
    }

    // Überladung der Methode setRandColor, die einen Farbwert als Ganzzahl übernimmt
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