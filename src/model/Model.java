package model;

import physicsBall.BasicPhysicsEngine;
import physicsBall.PhysicEngineInterface;
import physicsBall.PhysicsBallDTO;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Model {
    private List<PhysicsBallDTO> balls;
    private Player player;
    private boolean paused;
    private int workspaceWidth;
    private int workspaceHeight;

    public Model() {
        this.balls = new CopyOnWriteArrayList<>();
    }


    public void setWorkspaceDimensions(int width, int height) {
        this.workspaceWidth = width;
        this.workspaceHeight = height;
    }

    public int getWorkspaceWidth() {
        return workspaceWidth;
    }

    public int getWorkspaceHeight() {
        return workspaceHeight;
    }

    public List<PhysicsBallDTO> getBalls() {
        return balls;
    }


    public void createRandomBall() {
        int x = (int) (Math.random() * workspaceWidth);
        int y = (int) (Math.random() * workspaceHeight);
        int vx = (int) (Math.random() * 5 + 1);  // velocidad entre 1 y 5
        int vy = (int) (Math.random() * 5 + 1);
        int radius = (int) (Math.random() * 16) + 5;  // radio entre 5 y 20

        PhysicEngineInterface motor = new BasicPhysicsEngine();

        addBall(x, y, vx, vy, radius, motor);
    }


    // ---------- Crear bola normal ----------
    private void addBall(int x, int y, int vx, int vy, int radius,
                         PhysicEngineInterface motor) {
        Ball ball = new Ball(x, y, vx, vy, radius, this, motor);
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

    public void clearBalls() {
        balls.clear();
    }

    public int getBallCount() {
        return balls.size();
    }
}
