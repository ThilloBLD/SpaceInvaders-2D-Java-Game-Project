package spaceinvaders.texture;

public enum TextureType {

    player("spaceship.png"), // Spieler-Textur
    bullet("bullet.png"), // Projektil-Textur
    reversedBullet("reversedbullet.png"), // Umgekehrte Projektil-Textur
    enemy("enemy.png"), // Gegner-Textur
    penis("penis.png"), // Penis-Textur (interessante Wahl!)
    cum("cum.png"), // Ejakulation-Textur
    pille("pille.png"), // Pille-Textur
    vagina("vagina.png"); // Vagina-Textur

    // Dateiname der Textur
    private final String fileName;

    // Konstruktor für TextureType
    private TextureType(String fileName) {
        this.fileName = fileName;
    }

    // Getter für den Dateinamen
    public String getFileName() {
        return fileName;
    }
}
