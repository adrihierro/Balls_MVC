package vista;

import Images.SpriteManager;
import physicsBall.PhysicsBallDTO;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Viewer extends Canvas implements Runnable, KeyListener {

    public Vista vista;
    private List<BallRenderInfoDTO> balls = new ArrayList<>();
    private Thread loopThread;
    private boolean running = false;
    private SpriteManager spriteManager;
    private Image backgroundImg;

    //Constructor

    public Viewer(Vista vista){
        this.vista = vista;
        this.spriteManager = new SpriteManager();

        setMinimumSize(new Dimension(0, 0));
        setPreferredSize(new Dimension(100, 100));

        backgroundImg = spriteManager.getBackgroundIMG();

    }

    public void start() {
        if (running) return;
        running = true;
        loopThread = new Thread(this);
        loopThread.start();
    }

    public void updateRenderData(List<BallRenderInfoDTO> balls){
        this.balls = balls;
    }

    //PJ Controller
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //Render Balls
    @Override
    public void run() {
        this.createBufferStrategy(2);

        while (running){

            int w = getWidth();
            int h = getHeight();
            vista.updateWorkspaceSize(w,h);


            vista.renderBalls();

            BufferStrategy bufferStrategy = getBufferStrategy();
            Graphics g = bufferStrategy.getDrawGraphics();
            Graphics2D g2d = (Graphics2D) g;


            g.clearRect(0,0,getWidth(),getHeight());


            if (backgroundImg != null) {
                g2d.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
            } else {
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }


            for (BallRenderInfoDTO b : balls) {
                g.setColor(Color.BLUE);
                g.fillOval(b.x, b.y, b.radius * 2, b.radius * 2);
            }

            PlayerRenderInfoDTO p = vista.getPlayerRenderInfo();

            if (p != null) {
                g2d.drawImage(
                        spriteManager.getPlayerIMG(),
                        p.x,
                        p.y,
                        p.radius * 2,
                        p.radius * 2,
                        this );
            }


            g.dispose();
            bufferStrategy.show();
        }
    }
}