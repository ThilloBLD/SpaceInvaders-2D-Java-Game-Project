package spaceinvaders.texture;

public final class AdvancedResourceManager {

    public static Texture getTextureForType(AdvancedTextureType type, Theme theme) {
        switch (type) {
            case player:
                return getPlayerTexture(theme);
            case enemy:
                return getEnemyTexture(theme);
            case bullet:
                return getBulletTexture(theme);
            case reversedbullet:
                return getReversedBulletTexture(theme);
            default:
                return getDefaultTexture();
        }
    }

    private static Texture getPlayerTexture(Theme theme) {
        switch (theme) {
            case standard:
                return ResourceManager.getInstance().getTexture(TextureType.player);
            case milfhunter:
                return ResourceManager.getInstance().getTexture(TextureType.penis);
            default:
                return getDefaultTexture();
        }
    }

    private static Texture getEnemyTexture(Theme theme) {
        switch (theme) {
            case standard:
                return ResourceManager.getInstance().getTexture(TextureType.enemy);
            case milfhunter:
                return ResourceManager.getInstance().getTexture(TextureType.vagina);
            default:
                return getDefaultTexture();
        }
    }

    private static Texture getBulletTexture(Theme theme) {
        switch (theme) {
            case standard:
                return ResourceManager.getInstance().getTexture(TextureType.bullet);
            case milfhunter:
                return ResourceManager.getInstance().getTexture(TextureType.cum);
            default:
                return getDefaultTexture();
        }
    }

    private static Texture getReversedBulletTexture(Theme theme) {
        switch (theme) {
            case standard:
                return ResourceManager.getInstance().getTexture(TextureType.reversedBullet);
            case milfhunter:
                return ResourceManager.getInstance().getTexture(TextureType.pille);
            default:
                return getDefaultTexture();
        }
    }

    private static Texture getDefaultTexture() {
        // Return a default texture or handle the null case as appropriate
        return null;
    }
}
