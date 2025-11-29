package model;

import physicsBall.PhysicEngineInterface;
import physicsBall.PhysicsBallDTO;

public class Ball implements Runnable {
    private final PhysicsBallDTO physics;
    private final PhysicEngineInterface motor;
    private final int viewerWidth;
    private final int viewerHeight;
    private final Model model;
    private State state;
    private Thread thread;

    public enum State {
        Play,
        Stop,
        Restart
    }

    public Ball(int x, int y, int vx, int vy, int radius,
                int viewerWidth, int viewerHeight,
                Model model, PhysicEngineInterface motor) {
        if (motor == null) {
            throw new IllegalArgumentException("Motor físico no puede ser null");
        }

        this.physics = new PhysicsBallDTO(x, y, vx, vy, 0, 0, radius, 1.0); // aceleración 0, masa 1.0
        this.viewerWidth = viewerWidth;
        this.viewerHeight = viewerHeight;
        this.model = model;
        this.motor = motor;
        this.state = State.Play;
        start();
    }

    public void start() {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void play() { this.state = State.Play; }
    public void stop() { this.state = State.Stop; }
    public void restart() { this.state = State.Restart; }

    // ---------- Acceso al DTO ----------
    public PhysicsBallDTO getPhysics() {
        return physics;
    }

    // ---------- Bucle de animación ----------
    @Override
    public void run() {
        while (state != State.Stop) {
            if (state == State.Play && !model.isPaused()) {

                motor.actualizar(physics, viewerWidth, viewerHeight);

            } else if (state == State.Restart) {
                physics.setX(0);
                physics.setY(0);
                state = State.Play;
            }

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
