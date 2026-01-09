package model;

import controller.State;
import physicsBall.PhysicEngineInterface;
import physicsBall.PhysicsBallDTO;

public class Ball implements Runnable {
    private PhysicsBallDTO physicsDTO;
    private final PhysicEngineInterface motor;
    private final Model model;
    private Thread thread;


    public Ball(int x, int y, int vx, int vy, int radius,
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

    // ---------- Bucle de animación ----------
    @Override
    public void run() {
        while (true) {

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
