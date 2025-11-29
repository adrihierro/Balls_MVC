package model;

import physicsBall.PhysicEngineInterface;
import physicsBall.PhysicsBallDTO;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Model {
    private List<PhysicsBallDTO> balls;
    private Player player;
    private boolean paused;


    public Model() {
        this.balls = new CopyOnWriteArrayList<>();
    }

    public List<PhysicsBallDTO> getBalls() {
        return balls;
    }


    public void addBall(int x, int y, int vx, int vy, int radius,
                        int viewerWidth, int viewerHeight, PhysicEngineInterface motor) {
        Ball ball = new Ball(x, y, vx, vy, radius, viewerWidth, viewerHeight, this, motor);
        balls.add(ball.getPhysics());
    }

    public void pause() {
        this.paused = true;
    }

    public void play() {
        this.paused = false;
    }

    public boolean isPaused() {
        return paused;
    }

    //Crear el jugador controlable
    public Player generatePlayer(){
        int startx = 100;
        int starty = 100;
        int radius = 20;
        this.player = new Player(startx,starty,radius);
        return player;
    }
}

