package Images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.VolatileImage;
import java.util.HashMap;
import java.util.Map;

public class SpriteManager {

    private VolatileImage backgroundimg;
    private VolatileImage playerIMG;
    private final Map<String, VolatileImage> cachedImages;

    public SpriteManager() {
        this.cachedImages = new HashMap<>();

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        // Cargar imágenes por defecto
        Image player = new ImageIcon(getClass().getResource("/Images/Assets/spaceship-1.png")).getImage();

        playerIMG = gc.createCompatibleVolatileImage(
                player.getWidth(null),
                player.getHeight(null),
                Transparency.TRANSLUCENT
        );
        Graphics2D gPlayer = playerIMG.createGraphics();
        gPlayer.setComposite(AlphaComposite.Src);
        gPlayer.drawImage(player, 0, 0, null);
        gPlayer.dispose();
    }

    /**
     * Carga una imagen desde un path y la almacena en caché
     */
    public VolatileImage loadImage(String imagePath) {
        if (cachedImages.containsKey(imagePath)) {
            return cachedImages.get(imagePath);
        }

        try {
            Image img = new ImageIcon(getClass().getResource(imagePath)).getImage();

            GraphicsConfiguration gc = GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();

            VolatileImage vImg = gc.createCompatibleVolatileImage(
                    img.getWidth(null),
                    img.getHeight(null),
                    Transparency.TRANSLUCENT
            );

            Graphics2D g2d = vImg.createGraphics();
            g2d.setComposite(AlphaComposite.Src);
            g2d.drawImage(img, 0, 0, null);
            g2d.dispose();

            cachedImages.put(imagePath, vImg);
            return vImg;

        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + imagePath);
            e.printStackTrace();
            return null;
        }
    }

    public void setBackground(String backgroundPath) {
        VolatileImage newBg = loadImage(backgroundPath);
        if (newBg != null) {
            this.backgroundimg = newBg;
        }
    }

    public VolatileImage getPlayerIMG() {
        return playerIMG;
    }

    public VolatileImage getBackgroundIMG() {
        return backgroundimg;
    }
}