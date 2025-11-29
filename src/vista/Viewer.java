package vista;

import Images.SpriteManager;
import physicsBall.PhysicsBallDTO;

import java.awt.*;
import java.util.List;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Viewer extends Canvas implements Runnable, KeyListener {
    private List<PhysicsBallDTO> balls;
    int frames = 0;
    private PhysicsBallDTO player;
    private Image pelotaJugadorImg;
    private Image backgroundImg;
    private SpriteManager spriteManager;

    public Viewer() {
        this.spriteManager = new SpriteManager();

        pelotaJugadorImg = spriteManager.getPlayerIMG();
        backgroundImg = spriteManager.getBackgroundIMG();

        setFocusable(true);
        addKeyListener(this);
    }

    public void setPlayer(PhysicsBallDTO player){
        this.player = player;
    }

    public void setBalls(List<PhysicsBallDTO> balls) {
        this.balls = balls;
    }

    public void start() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (player == null) return;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:  player.setVx(-5); break;
            case KeyEvent.VK_RIGHT: player.setVx(5);  break;
            case KeyEvent.VK_UP:    player.setVy(-5); break;
            case KeyEvent.VK_DOWN:  player.setVy(5);  break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (player == null) return;
        // Al soltar la tecla, parar movimiento
        player.setVx(0);
        player.setVy(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void run() {
        if (getBufferStrategy() == null) {
            try {
                createBufferStrategy(2);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        BufferStrategy bufferStrategy = getBufferStrategy();
        long fpsTimer = System.currentTimeMillis();
        frames = 0;

        while (true) {
            if (player != null) {
                player.setX(player.getX() + player.getVx());
                player.setY(player.getY() + player.getVy());

                if (player.getX() < 0) {
                    player.setX(0);
                }
                if (player.getY() < 0) {
                    player.setY(0);
                }
                if (player.getX() + player.getRadius()*2 > getWidth()) {
                    player.setX(getWidth() - player.getRadius()*2);
                }
                if (player.getY() + player.getRadius()*2 > getHeight()) {
                    player.setY(getHeight() - player.getRadius()*2);
                }
            }

            Graphics g = bufferStrategy.getDrawGraphics();
            Graphics2D g2d = (Graphics2D) g;

            g2d.clearRect(0, 0, getWidth(), getHeight());

            // Fondo
            g2d.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);

            // Pelotas
            if (balls != null) {
                for (PhysicsBallDTO ball : balls) {
                    g2d.setColor(Color.RED);
                    g2d.fillOval(ball.getX(), ball.getY(),
                            ball.getRadius() * 2, ball.getRadius() * 2);
                }
            }

            // Jugador
            if (player != null) {
                g2d.drawImage(pelotaJugadorImg,
                        player.getX(), player.getY(),
                        player.getRadius() * 2, player.getRadius() * 2,
                        this);
            }

            g2d.dispose();
            bufferStrategy.show();

            frames++;
            if (System.currentTimeMillis() - fpsTimer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                fpsTimer += 1000;
            }

            try { Thread.sleep(15); } catch (InterruptedException e) {}
        }
    }
}
