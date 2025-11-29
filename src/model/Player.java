package model;

import physicsBall.PhysicsBallDTO;

public class Player {
    private PhysicsBallDTO physics;


    public Player(int x, int y, int radius) {
        this.physics = new PhysicsBallDTO(x, y, 0, 0, 0, 0, radius, 1.0);
    }

    public PhysicsBallDTO getPhysics() {
        return physics;
    }

}
