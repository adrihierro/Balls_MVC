package controller;

import Generators.LifeGenerator.LifeGenerator;
import Generators.worldgenerator.StaticObject;
import Generators.worldgenerator.WorldConfigDTO;
import Generators.worldgenerator.WorldGenerator;
import controller.mappers.BallRenderMapper;
import controller.mappers.PlayerRenderMapper;
import model.*;
import physicsBall.PhysicsBallDTO;
import vista.*;
import model.Events;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final Model model;
    private final Vista vista;
    private final WorldGenerator worldGenerator;
    private final LifeGenerator lifeGenerator;

    public Controller(WorldGenerator worldGenerator) {
        this.vista = new Vista(this);
        this.model = new Model(this);
        this.worldGenerator = worldGenerator;

        Listeners();
        initRandomWorld();
        vista.display();

        this.lifeGenerator = new LifeGenerator(this, 5000); // 2 segundos entre cada bola
        this.lifeGenerator.start();
    }

    private void Listeners() {
        vista.addNewPelotaListener(e -> HandleAddBall());

        vista.PauseListener(e -> {
            model.setState(State.Paused);
        });

        vista.startListener(e -> {
            model.setState(State.Started);
        });

        vista.RestartListener(e -> {
            model.Restart();
            updateNumPelotas();
        });

        vista.GeneratePJ(e -> {
            model.generatePlayer();
        });
    }


    public void generateRandomBall(){
        model.createRandomBall();
    }


    public void initRandomWorld() {

        WorldConfigDTO world = worldGenerator.generateWorld(3, vista.getHeight(), vista.getWidth());

        vista.applyWorld(convertToWorldDTO(world));
    }

    private WorldRenderableDTO convertToWorldDTO(WorldConfigDTO worldConfig) {
        if (worldConfig == null) {
            return null;
        }

        List<StaticObjectDTO> dtoObjects = convertStaticObjectsToDTO(
                worldConfig.getStaticObjects());


        return new WorldRenderableDTO(worldConfig.getBackgroundPath(), dtoObjects);
    }

    private List<StaticObjectDTO> convertStaticObjectsToDTO(List<StaticObject> staticObjects) {
        if (staticObjects == null) {
            return new ArrayList<>();
        }

        List<StaticObjectDTO> dtoList = new ArrayList<>();

        for (StaticObject obj : staticObjects) {
            dtoList.add(convertStaticObjectToDTO(obj));
        }

        return dtoList;
    }

    private StaticObjectDTO convertStaticObjectToDTO(StaticObject staticObject) {
        if (staticObject == null) {
            return null;
        }

        return new StaticObjectDTO(
                staticObject.getPath(),
                staticObject.getX(),
                staticObject.getY(),
                staticObject.getSize());
    }

    private void HandleAddBall() {
        model.createRandomBall();
        updateNumPelotas();
    }

    public void updateWorkspaceSize(int width, int height) {
        model.setWorkspaceDimensions(width, height);
    }

    public List<BallRenderInfoDTO> getBallRenderables() {
        List<PhysicsBallDTO> physicsBalls = model.getBalls();

       return BallRenderMapper.toRenderableAsteroids(physicsBalls);
    }

    public PlayerRenderInfoDTO getPlayerRenderInfo() {
        PlayerDTO playerDTO = model.getPlayerData();
        return PlayerRenderMapper.toRenderDTO(playerDTO);
    }


    public void eventManager(Events events, int x, int y) {
        vista.CollisionDetector(events, x, y);
    }

    private void updateNumPelotas() {
        int numPelotas = model.getBalls().size();
        vista.updatePelotas(numPelotas);
    }

    // Player movements

    public void getMoveLeft() {
        model.moveLeft();
    }

    public void getMoveUP() {
        model.moveUP();
    }

    public void getMoveDown() {
        model.moveDown();
    }

    public void getMoveRight() {
        model.moveRight();
    }

    public void getStopX() {
        model.StopX();
    }

    public void getStopY() {
        model.StopY();
    }
}
