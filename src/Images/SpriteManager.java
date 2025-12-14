package Images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.VolatileImage;

public class SpriteManager {

    private VolatileImage backgroundimg;
    private VolatileImage playerIMG;

    public SpriteManager() {
        // Configuración gráfica del sistema
        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        Image player = new ImageIcon(getClass().getResource("/Images/Assets/spaceship-1.png")).getImage();
        Image background = new ImageIcon(getClass().getResource("/Images/Assets/background-space.png")).getImage();

        // Crear VolatileImage con transparencia adecuada
        playerIMG = gc.createCompatibleVolatileImage(player.getWidth(null), player.getHeight(null), Transparency.TRANSLUCENT);
        Graphics2D gPlayer = playerIMG.createGraphics();
        gPlayer.setComposite(AlphaComposite.Src); // asegura que se copie el canal alfa
        gPlayer.drawImage(player, 0, 0, null);
        gPlayer.dispose();

        backgroundimg = gc.createCompatibleVolatileImage(background.getWidth(null), background.getHeight(null), Transparency.OPAQUE);
        Graphics2D gBg = backgroundimg.createGraphics();
        gBg.drawImage(background, 0, 0, null);
        gBg.dispose();
    }

    // Getters para acceder a las imágenes
    public VolatileImage getPlayerIMG() {
        return playerIMG;
    }

    public VolatileImage getBackgroundIMG() {
        return backgroundimg;
    }
}
