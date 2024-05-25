package spaceinvaders.texture;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Texture {

    // Das BufferedImage-Objekt, das die Pixeldaten enthält
    private final BufferedImage img;
    // Ein Array von Standardpixeln, das als Backup dient
    private int[] defaultPixels;

    // Konstruktor, der ein BufferedImage-Objekt als Argument nimmt
    public Texture(BufferedImage img) {
        this.img = img;
    }

    // Konstruktor, der ein neues BufferedImage-Objekt mit der angegebenen Breite und Höhe erstellt
    public Texture(int width, int height) {
        this(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    // Konstruktor, der ein neues BufferedImage-Objekt mit der angegebenen Breite, Höhe und Bildtyp erstellt
    public Texture(int width, int height, int imageType) {
        this(new BufferedImage(width, height, imageType));
    }

    // Gibt ein Array von Pixeldaten zurück
    public int[] getPixels() {
        return ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
    }

    // Setzt die Pixeldaten des BufferedImage-Objekts auf ein neues Array von Pixelwerten
    public void setPixel(int[] pixel) {
        int[] pixi = getPixels();
        if (pixel.length == pixi.length) {
            for (int i = 0; i < pixi.length; i++) {
                pixi[i] = pixel[i];
            }
        }
    }

    // Gibt das BufferedImage-Objekt zurück
    public BufferedImage getBufferedImage() {
        return this.img;
    }

    // Setzt den RGB-Wert eines bestimmten Pixels
    public void setRGB(int rgb, int pixel) {
        int[] pixi = getPixels();
        if (pixel >= 0 && pixel < pixi.length) {
            pixi[pixel] = rgb;
        } else {
            throw new ArrayIndexOutOfBoundsException(pixel + " > " + pixi.length);
        }
    }

    // Setzt den RGB-Wert eines bestimmten Pixels basierend auf einer Color-Instanz
    public void setRGB(Color color, int pixel) {
        setRGB(color.getRGB(), pixel);
    }

    // Setzt den RGB-Wert eines Pixels basierend auf den Koordinaten x und y
    public void setRGB(int x, int y, int rgb) {
        if (!isLocationInBound(x, y)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        setRGB(rgb, (y * img.getHeight()) + x);
    }

    // Setzt den RGB-Wert eines Pixels basierend auf den Koordinaten x und y sowie einer Color-Instanz
    public void setRGB(int x, int y, Color color) {
        setRGB(x, y, color.getRGB());
    }

    // Gibt den RGB-Wert eines bestimmten Pixels zurück
    public int getRGB(int pixel) {
        int[] pixi = getPixels();
        if (pixel >= 0 && pixel < pixi.length) {
            return pixi[pixel];
        } else {
            throw new ArrayIndexOutOfBoundsException(pixel + " > " + pixi.length);
        }
    }

    // Gibt den RGB-Wert eines Pixels basierend auf den Koordinaten x und y zurück
    public int getRGB(int x, int y) {
        if (isLocationInBound(x, y)) {
            int[] pixi = getPixels();
            return pixi[(y * img.getHeight()) + x];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    // Setzt die Farbe aller Pixel auf eine bestimmte Color-Instanz
    public void setColor(Color color) {
        int[] pixi = getPixels();
        for (int i = 0; i < pixi.length; i++) {
            pixi[i] = color.getRGB();
        }
    }

    // Setzt die Farbe aller Pixel auf einen bestimmten RGB-Wert
    public void setColor(int rgb) {
        int[] pixi = getPixels();
        for (int i = 0; i < pixi.length; i++) {
            pixi[i] = rgb;
        }
    }

    // Ersetzt einen bestimmten RGB-Wert durch einen anderen RGB-Wert
    public void replaceColor(int rgb, int replaceRGB) {
        int[] pixi = getPixels();
        for (int i = 0; i < pixi.length; i++) {
            if (pixi[i] == rgb) {
                pixi[i] = replaceRGB;
            }
        }
    }

    // Ersetzt eine bestimmte Farbe durch eine andere Farbe
    public void replaceColor(Color replace, Color replaceColor) {
        replaceColor(replace.getRGB(), replaceColor.getRGB());
    }

    // Kopiert die aktuellen Pixeldaten in das defaultPixels-Array
    public void copyDefault() {
        this.defaultPixels = getPixels().clone();
    }

    // Lädt die Standardpixeldaten aus dem defaultPixels-Array
    public void loadDefault() {
        if (isDefaultExist()) {
            setPixel(defaultPixels);
        }
    }

    // Überprüft, ob das defaultPixels-Array initialisiert wurde
    public boolean isDefaultExist() {
        return defaultPixels != null;
    }

    // Gibt das defaultPixels-Array zurück
    public int[] getDefaultPixel() {
        return defaultPixels;
    }

    // Zeichnet ein Bild basierend auf einem Array von RGB-Werten
    public void drawImage(int... rgb) {
        int[] pixi = getPixels();
        if (rgb.length > pixi.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < rgb.length; i++) {
            pixi[i] = rgb[i];
        }
    }

    // Zeichnet ein Bild basierend auf einem Array von Color-Objekten
    public void drawImage(Color... colors) {
        int[] pixi = getPixels();
        if (colors.length > pixi.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < colors.length; i++) {
            pixi[i] = colors[i].getRGB();
        }
    }

    // Überprüft, ob die Koordinaten x und y innerhalb des BufferedImage-Objekts liegen
    public boolean isLocationInBound(int x, int y) {
        if (x < 0 || x > img.getWidth() || y < 0 || y > img.getHeight()) {
            return false;
        }
        return true;
    }

    // Gibt die Breite des BufferedImage-Objekts zurück
    public int getWidth() {
        return img.getWidth();
    }

    // Gibt die Höhe des BufferedImage-Objekts zurück
    public int getHeight() {
        return img.getHeight();
    }

    // Setzt die Randfarbe des BufferedImage-Objekts basierend auf einer Color-Instanz
    public void setRandColor(Color color) {
        setRandColor(color.getRGB());
    }

    // Setzt die Randfarbe des BufferedImage-Objekts basierend auf einen RGB-Wert
    public void setRandColor(int color) {
        for (int i = 0; i < getWidth() * getHeight(); i++) {
            // Berechnet den Zeilenindex des aktuellen Pixels
            int a = i / getWidth();
            // Berechnet den Spaltenindex des aktuellen Pixels
            int b = i % getWidth();
            // Wenn wir in der ersten Zeile sind, setze die Farbe
            if (a < 1) {
                setRGB(color, i);
            } else {
                // Wenn wir in der letzten Zeile sind, setze die Farbe
                if ((getWidth() * getHeight()) - i <= getWidth()) {
                    setRGB(color, i);
                } else {
                    // Wenn wir in der ersten oder letzten Spalte sind, setze die Farbe
                    if (b == 0 || b == getWidth() - 1) {
                        setRGB(color, i);
                    }
                }
            }
        }
    }
}