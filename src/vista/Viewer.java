package vista;

import Images.SpriteManager;
import model.Events;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferStrategy;

public class Viewer extends Canvas implements Runnable {

    public Vista vista;
    private List<BallRenderInfoDTO> balls = new ArrayList<>();
    private List<StaticObjectDTO> staticObjects = new ArrayList<>();
    private Thread loopThread;
    private boolean running = false;
    private SpriteManager spriteManager;
    private Image backgroundImg;
    private SpriteFX spriteAnimator;

    private int collisionX;
    private int collisionY;
    private boolean collisionActive = false;

    public Viewer(Vista vista) {
        this.vista = vista;
        this.spriteManager = new SpriteManager();

        setMinimumSize(new Dimension(0, 0));
        setPreferredSize(new Dimension(100, 100));
    }

    public void start() {
        if (running) return;
        running = true;
        loopThread = new Thread(this);
        loopThread.start();
    }

    public void updateRenderData(List<BallRenderInfoDTO> balls) {
        this.balls = balls;
    }

    public void onCollisionEvent(Events event, int x, int y) {
        System.out.println("Viewer: colisión -> " + event);

        // Guardamos la posición exacta del choque
        collisionX = x;
        collisionY = y;
        collisionActive = true;

        // Creamos y arrancamos la animación
        spriteAnimator = new SpriteFX("/Images/Assets/exppp.png", 4, 4, 110);
        spriteAnimator.start();
    }


    public void setWorld(WorldRenderableDTO worldconfig){
        spriteManager.setBackground(worldconfig.getPath());
        backgroundImg = spriteManager.getBackgroundIMG();

        this.staticObjects = worldconfig.getStaticObject();
    }

    private void renderStaticObjects(Graphics2D g2d) {
        // Verificar que hay objetos para renderizar
        if (staticObjects == null || staticObjects.isEmpty()) {
            return;
        }

        // Iterar sobre cada objeto estático
        for (StaticObjectDTO obj : staticObjects) {
            // Cargar la imagen del objeto
            Image img = spriteManager.loadImage(obj.getImagePath());

            if (img != null) {
                // Dibujar la imagen en su posición y tamaño
                g2d.drawImage(
                        img,              // Imagen a dibujar
                        obj.getX(),       // Posición X
                        obj.getY(),       // Posición Y
                        obj.getSize(),    // Ancho
                        obj.getSize(),    // Alto
                        this              // ImageObserver
                );
            } else {
                // Fallback: Si la imagen no carga, dibujar un rectángulo
                g2d.setColor(Color.DARK_GRAY);
                g2d.fillRect(obj.getX(), obj.getY(), obj.getSize(), obj.getSize());

                // Dibujar borde para indicar que es un placeholder
                g2d.setColor(Color.WHITE);
                g2d.drawRect(obj.getX(), obj.getY(), obj.getSize(), obj.getSize());

                // Debug: mostrar ruta de imagen que falló (opcional)
                g2d.setColor(Color.RED);
                g2d.setFont(new Font("Arial", Font.PLAIN, 10));
                g2d.drawString("?", obj.getX() + obj.getSize()/2, obj.getY() + obj.getSize()/2);
            }
        }
    }

    @Override
    public void run() {
        this.createBufferStrategy(2);

        while (running) {

            int w = getWidth();
            int h = getHeight();
            vista.updateWorkspaceSize(w, h);

            vista.renderBalls();

            BufferStrategy bufferStrategy = getBufferStrategy();
            Graphics g = bufferStrategy.getDrawGraphics();
            Graphics2D g2d = (Graphics2D) g;

            g.clearRect(0, 0, getWidth(), getHeight());

            if (backgroundImg != null) {
                g2d.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
            } else {
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }


            renderStaticObjects(g2d);


            //Draw asteroids

            for (BallRenderInfoDTO b : balls) {
                g.setColor(Color.BLUE);
                g.fillOval(b.x, b.y, b.radius * 2, b.radius * 2);
            }


            //generate animations when collide with the max of the world

            if (collisionActive && spriteAnimator != null) {
                spriteAnimator.draw(g2d, collisionX - 20, collisionY - 20, 60, 60);

                // Si terminó, limpiamos
                if (!spriteAnimator.isRunning()) {
                    spriteAnimator = null;
                    collisionActive = false;
                }
            }

            // draw player
            PlayerRenderInfoDTO p = vista.getPlayerRenderInfo();
            if (p != null) {
                g2d.drawImage(
                        spriteManager.getPlayerIMG(),
                        p.x,
                        p.y,
                        p.radius * 2,
                        p.radius * 2,
                        this
                );
            }

            g.dispose();
            bufferStrategy.show();
        }
    }
}