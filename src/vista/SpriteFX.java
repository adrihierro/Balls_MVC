package vista;

import javax.swing.*;
import java.awt.*;

public class SpriteFX implements Runnable {

    private final Image spriteSheet;
    private final int rows;
    private final int cols;
    private final int frameWidth;
    private final int frameHeight;
    private final int frameDelay; // ms entre frames

    private int currentFrame = 0;
    private boolean running = false;


    public SpriteFX(String resourcePath, int rows, int cols, int frameDelay) {
        ImageIcon icon = new ImageIcon(getClass().getResource(resourcePath));
        this.spriteSheet = icon.getImage();
        this.rows = rows;
        this.cols = cols;
        this.frameDelay = frameDelay;

        this.frameWidth = spriteSheet.getWidth(null) / cols;
        this.frameHeight = spriteSheet.getHeight(null) / rows;
    }

    public void start() {
        if (!running) {
            running = true;
            Thread animThread = new Thread(this);
            animThread.start();
        }
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        int totalFrames = rows * cols;

        while (running) {

            //cuando llegue al ultimo frame de la animacion esta se para

            if (currentFrame < totalFrames - 1) {
                currentFrame++;
            } else {
                running = false;
                break;
            }

            try {
                Thread.sleep(frameDelay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
                break;
            }
        }
    }

    public void draw(Graphics2D g2d, int x, int y, int width, int height) {
        int frameX = (currentFrame % cols) * frameWidth;
        int frameY = (currentFrame / cols) * frameHeight;

        g2d.drawImage(spriteSheet,
                x, y, x + width, y + height,
                frameX, frameY, frameX + frameWidth, frameY + frameHeight,
                null);
    }

    public boolean isRunning() {
        return running;
    }
}
