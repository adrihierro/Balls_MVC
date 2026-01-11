package model;

import physicsBall.PhysicsBallDTO;
import physicsBall.PhysicEngineInterface;

public class Player {

    private PhysicsBallDTO physicsDTO;
    private final Model model;
    private final PhysicEngineInterface motor;
    private final int speed = 5;

    public Player(Model model, PhysicEngineInterface motor, PhysicsBallDTO physicsDTO) {
        this.model = model;
        this.motor = motor;
        this.physicsDTO = physicsDTO;
    }

    public PhysicsBallDTO getPhysicsDTO() {
        return physicsDTO;
    }

    public void moveUp() {
        physicsDTO = physicsDTO.withPosition(physicsDTO.x, physicsDTO.y - speed);
    }

    public void moveDown() { physicsDTO = physicsDTO.withPosition(physicsDTO.x, physicsDTO.y + speed); }


    public void moveLeft(){
        physicsDTO = physicsDTO.withPosition(physicsDTO.x - speed, physicsDTO.y);
    }

    public void moveRight(){
        physicsDTO = physicsDTO.withPosition(physicsDTO.x + speed, physicsDTO.y);
    }
}
