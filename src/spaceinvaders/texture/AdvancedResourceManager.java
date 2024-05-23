package spaceinvaders.texture;

public final class AdvancedResourceManager {

    public static Texture getTextureForType(AdvancedTextureType type, Theme theme) {
        switch (type) {
            case player:
                switch (theme) {
                    case standard:
                        return ResourceManager.getInstance().getTexture(TextureType.player);
                    case milfhunter:
                        return ResourceManager.getInstance().getTexture(TextureType.penis);
                }
                break;
            case enemy:
                switch (theme) {
                    case standard:
                        return ResourceManager.getInstance().getTexture(TextureType.enemy);
                    case milfhunter:
                        return ResourceManager.getInstance().getTexture(TextureType.vagina);
                }
                break;
            case bullet:
                switch (theme) {
                    case standard:
                        return ResourceManager.getInstance().getTexture(TextureType.bullet);
                    case milfhunter:
                        return ResourceManager.getInstance().getTexture(TextureType.cum);
                }
                break;
            case reversedbullet:
                switch (theme) {
                    case standard:
                        return ResourceManager.getInstance().getTexture(TextureType.reversedBullet);
                    case milfhunter:
                        return ResourceManager.getInstance().getTexture(TextureType.cum);
                }
                break;
        }
        return null;
    }
}
