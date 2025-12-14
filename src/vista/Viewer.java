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
    private int frames = 0;
    private PhysicsBallDTO player;
    private Image pelotaJugadorImg;
    private Image backgroundImg;
    private SpriteManager spriteManager;
    private boolean running = false;

    public Viewer() {
        this.spriteManager = new SpriteManager();

        pelotaJugadorImg = spriteManager.getPlayerIMG();
        backgroundImg = spriteManager.getBackgroundIMG();

        setFocusable(true);
        addKeyListener(this);
    }

    public void setPlayer(PhysicsBallDTO player) {
        this.player = player;
    }

    public void setBalls(List<PhysicsBallDTO> balls) {
        this.balls = balls;
    }

    public void start() {
        if (! running) {
            running = true;
            Thread t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (player == null) return;
        switch (e.getKeyCode()) {
            case KeyEvent. VK_LEFT:  player. setVx(-5); break;
            case KeyEvent.VK_RIGHT: player.setVx(5);  break;
            case KeyEvent.VK_UP:    player.setVy(-5); break;
            case KeyEvent.VK_DOWN:  player.setVy(5);  break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (player == null) return;
        player.setVx(0);
        player.setVy(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void run() {
        while (! isDisplayable()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                running = false;
                return;
            }
        }

        createBufferStrategy(2);
        BufferStrategy bufferStrategy = getBufferStrategy();

        if (bufferStrategy == null) {
            System. err.println("ERROR: No se pudo crear BufferStrategy");
            running = false;
            return;
        }

        long fpsTimer = System.currentTimeMillis();
        frames = 0;

        while (running) {
            // Actualizar jugador
            if (player != null) {
                player.setX(player.getX() + player.getVx());
                player.setY(player.getY() + player.getVy());

                // Límites
                if (player.getX() < 0) {
                    player.setX(0);
                }
                if (player.getY() < 0) {
                    player.setY(0);
                }
                if (player.getX() + player.getRadius() * 2 > getWidth()) {
                    player.setX(getWidth() - player.getRadius() * 2);
                }
                if (player.getY() + player.getRadius() * 2 > getHeight()) {
                    player.setY(getHeight() - player.getRadius() * 2);
                }
            }

            try {
                Graphics g = bufferStrategy.getDrawGraphics();
                Graphics2D g2d = (Graphics2D) g;

                // Limpiar pantalla
                g2d.clearRect(0, 0, getWidth(), getHeight());

                // Dibujar fondo
                if (backgroundImg != null) {
                    g2d.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }

                // Dibujar pelotas
                if (balls != null) {
                    synchronized (balls) {
                        for (PhysicsBallDTO ball : balls) {
                            g2d.setColor(Color.RED);
                            g2d.fillOval(ball. getX(), ball.getY(),
                                    ball.getRadius() * 2, ball.getRadius() * 2);
                        }
                    }
                }

                // Dibujar jugador
                if (player != null) {
                    if (pelotaJugadorImg != null) {
                        g2d.drawImage(pelotaJugadorImg,
                                player.getX(), player.getY(),
                                player. getRadius() * 2, player.getRadius() * 2,
                                this);
                    } else {
                        g2d.setColor(Color. BLUE);
                        g2d. fillOval(player.getX(), player.getY(),
                                player.getRadius() * 2, player.getRadius() * 2);
                    }
                }

                g2d. dispose();
                bufferStrategy. show();

            } catch (IllegalStateException e) {
                System.err.println("Error en renderizado: " + e. getMessage());
                break;  // Salir del loop si hay error
            }

            // FPS counter
            frames++;
            if (System.currentTimeMillis() - fpsTimer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                fpsTimer += 1000;
            }

            // Sleep
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                running = false;
                break;
            }
        }

        System.out.println("Viewer thread terminado");
    }
}