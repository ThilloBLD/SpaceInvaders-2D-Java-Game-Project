package spaceinvaders.texture;

public enum TextureType {

    player("spaceship.png"),
    bullet("bullet.png"),
    reversedBullet("reversedbullet.png"),
    enemy("enemy.png"),
    penis("penis.png"),
    cum("cum.png"),
    vagina("vagina.png");


    private final String fileName;

    private TextureType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
