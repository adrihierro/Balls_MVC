package model;

import controller.State;
import physicsBall.PhysicEngineInterface;
import physicsBall.PhysicsBallDTO;

public class Asteroid implements Runnable {
    private PhysicsBallDTO physicsDTO;
    private final PhysicEngineInterface motor;
    private final Model model;
    private Thread thread;
    private boolean alive = true;

    public Asteroid(int x, int y, int vx, int vy, int radius,
                    Model model, PhysicEngineInterface motor) {
        if (motor == null) {
            throw new IllegalArgumentException("Motor físico no puede ser null");
        }
        this.physicsDTO = new PhysicsBallDTO(x, y, vx, vy,0,0,radius); // aceleración 0,
        this.model = model;
        this.motor = motor;
        start();
    }

    public void start() {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(this);
            thread.start();
        }
    }

    // ---------- Acceso al DTO ----------
    public PhysicsBallDTO getPhysics() {
        return physicsDTO;
    }



    public void kill(){
        alive = false;
    }



    // ---------- Bucle de animación ----------
    @Override
    public void run() {
        while (alive) {

            if (model.getState() == State.Started) {
                int workspaceWidth = model.getWorkspaceWidth();
                int workspaceHeight = model.getWorkspaceHeight();

                physicsDTO = motor.newPosition(physicsDTO,workspaceWidth,workspaceHeight);
                model.eventDetector(physicsDTO);
            }

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
