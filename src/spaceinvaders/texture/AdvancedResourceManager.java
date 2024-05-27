package spaceinvaders.texture;

public final class AdvancedResourceManager {

    // Methode, um eine Textur basierend auf dem Typ und dem Theme zu erhalten
    public static Texture getTextureForType(AdvancedTextureType type, Theme theme) {
        switch (type) {
            case player:
                // Spieler-Textur
                switch (theme) {
                    case standard:
                        // Standard-Thema verwendet Spieler-Textur
                        return ResourceManager.getInstance().getTexture(TextureType.player);
                    case milfhunter:
                        // Milfhunter-Thema verwendet Penis-Textur
                        return ResourceManager.getInstance().getTexture(TextureType.penis);
                }
                break;
            case enemy:
                // Gegner-Textur
                switch (theme) {
                    case standard:
                        // Standard-Thema verwendet Gegner-Textur
                        return ResourceManager.getInstance().getTexture(TextureType.enemy);
                    case milfhunter:
                        // Milfhunter-Thema verwendet Vagina-Textur
                        return ResourceManager.getInstance().getTexture(TextureType.vagina);
                }
                break;
            case bullet:
                // Kugel-Textur
                switch (theme) {
                    case standard:
                        // Standard-Thema verwendet Kugel-Textur
                        return ResourceManager.getInstance().getTexture(TextureType.bullet);
                    case milfhunter:
                        // Milfhunter-Thema verwendet Sperma-Textur
                        return ResourceManager.getInstance().getTexture(TextureType.cum);
                }
                break;
            case reversedbullet:
                // Umgekehrte Kugel-Textur
                switch (theme) {
                    case standard:
                        // Standard-Thema verwendet umgekehrte Kugel-Textur
                        return ResourceManager.getInstance().getTexture(TextureType.reversedBullet);
                    case milfhunter:
                        // Milfhunter-Thema verwendet Pille-Textur
                        return ResourceManager.getInstance().getTexture(TextureType.pille);
                }
                break;
        }
        return null;
    }
}
