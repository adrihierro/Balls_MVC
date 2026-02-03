package model;

import physicsBall.PhysicsBallDTO;
import physicsBall.PhysicEngineInterface;

public class Player implements Runnable {

    private PhysicsBallDTO physicsDTO;
    private final Model model;
    private final PhysicEngineInterface motor;
    private final int speed = 5;

    private Thread thread;
    private boolean running = false;

    public Player(Model model, PhysicEngineInterface motor, PhysicsBallDTO physicsDTO) {
        this.model = model;
        this.motor = motor;
        this.physicsDTO = physicsDTO;
    }


    //Convierte el estado interno de Player a un dto
    public PlayerDTO toDTO() {
        return new PlayerDTO(
                physicsDTO.x,
                physicsDTO.y,
                physicsDTO.radius
        );
    }

    public void moveUp() {
        physicsDTO = physicsDTO.withVelocity(physicsDTO.vx, -speed);
    }

    public void moveDown() {
        physicsDTO = physicsDTO.withVelocity(physicsDTO.vx, speed);
    }

    public void moveLeft() {
        physicsDTO = physicsDTO.withVelocity(-speed, physicsDTO.vy);
    }

    public void moveRight() {
        physicsDTO = physicsDTO.withVelocity(speed, physicsDTO.vy);
    }

    public void stopX() {
        physicsDTO = physicsDTO.withVelocity(0, physicsDTO.vy);
    }

    public void stopY() {
        physicsDTO = physicsDTO.withVelocity(physicsDTO.vx, 0);
    }

    public void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    // --- BUCLE DE FÍSICAS DEL JUGADOR ---
    @Override
    public void run() {
        while (running) {

            physicsDTO = motor.newPosition(
                    physicsDTO,
                    model.getWorkspaceWidth(),
                    model.getWorkspaceHeight()
            );

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
