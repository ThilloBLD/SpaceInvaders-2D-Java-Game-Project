package spaceinvaders.texture;

public enum TextureType {

    // Textur für das Spielerschiff
    player("spaceship.png"),
    // Textur für die Standard-Kugel
    bullet("bullet.png"),
    // Textur für die umgekehrte Kugel
    reversedBullet("reversedbullet.png"),
    // Textur für den Gegner
    enemy("enemy.png"),

    // Die folgenden Texturen sind möglicherweise unangemessen benannt
    penis("penis.png"),
    cum("cum.png"),
    pille("pille.png"),
    vagina("vagina.png");

    // Das Feld, das den Dateinamen der Textur speichert
    private final String fileName;

    // Konstruktor, der den Dateinamen der Textur setzt
    private TextureType(String fileName) {
        this.fileName = fileName;
    }

    // Getter-Methode für den Dateinamen der Textur
    public String getFileName() {
        return fileName;
    }
}