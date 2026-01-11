package controller;

import model.*;
import physicsBall.PhysicsBallDTO;
import vista.BallRenderInfoDTO;
import vista.PlayerRenderInfoDTO;
import vista.Vista;
import model.Events;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final Model model;
    private final Vista vista;

    public Controller() {
        this.vista = new Vista(this);
        this.model = new Model(this);

        Listeners();
        vista.display();
    }

    private void Listeners() {
        vista.addNewPelotaListener(e -> HandleAddBall());

        vista.RestartListener(e -> {});

        vista.PauseListener(e -> {
            model.setState(State.Paused);
        });

        vista.startListener(e -> {
            model.setState(State.Started);
        });

        vista.RestartListener(e -> {
            model.setState(State.Restart);
            model.Restart();
        });

        vista.GeneratePJ(e -> {
            model.generatePlayer();
            System.out.println("FUnciona");
        });
    }

    private void HandleAddBall(){
        model.createRandomBall();
        updateNumPelotas();
    }

    public void updateWorkspaceSize(int width, int height) {
        model.setWorkspaceDimensions(width, height);
    }

    public List<BallRenderInfoDTO> getBallRenderables() {
        List<BallRenderInfoDTO> renderBalls = new ArrayList<>();
        List<PhysicsBallDTO> physicsBalls = model.getBalls();

        for (PhysicsBallDTO dto : physicsBalls) {
            renderBalls.add(new BallRenderInfoDTO(
                    (int) dto.x,
                    (int) dto.y,
                    dto.radius
            ));
        }

        return renderBalls;
    }

    public PlayerRenderInfoDTO getPlayerRenderInfo(){
        Player p = model.getPlayer();
        System.out.println("Controller: model.getPlayer() = " + p);
        if (p == null){
            return null;
        }

        PhysicsBallDTO dto = p.getPhysicsDTO();
        return new PlayerRenderInfoDTO(dto.x, dto.y, dto.radius);
    }

    public void eventManager(Events events){

        switch (events){
            case West_Reached -> System.out.println("West reached");
            case North_Reached -> System.out.println("North reached");
            case East_Reached -> System.out.println("East reached");
            case South_Reached -> System.out.println("South reached");
        }
    }


    private void updateNumPelotas() {
        int numPelotas = model.getBalls().size();
        vista.updatePelotas(numPelotas);
    }

    public void getMoveLeft(){
        model.moveLeft();
    }

    public void getMoveUP(){
        model.moveUP();
    }
    public void getMoveDown(){
        model.moveDown();
    }
    public void getMoveRight(){
        model.moveRight();
    }
}
