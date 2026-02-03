package model;

import controller.Controller;
import controller.State;
import physicsBall.BasicPhysicsEngine;
import physicsBall.PhysicEngineInterface;
import physicsBall.PhysicsBallDTO;

import java.util.ArrayList;
import java.util.List;


public class Model {
    private List<Asteroid> balls = new ArrayList<>();
    private int workspaceWidth;
    private int workspaceHeight;
    private final Controller controller;
    PhysicEngineInterface motor = new BasicPhysicsEngine();
    private State state;
    private Player player;


    public Model(Controller controller) {
        this.controller = controller;
        setState(State.Started);
    }

    public void setWorkspaceDimensions(int width, int height) {
        this.workspaceWidth = width;
        this.workspaceHeight = height;
    }

    public void generatePlayer(){
        int startX = workspaceWidth / 2;
        int startY = workspaceHeight / 2;
        int radius = 10;

        PhysicsBallDTO dto = new PhysicsBallDTO( startX, startY, 0, 0,0,0,radius);

        this.player = new Player(this, motor, dto);
        this.player.start();
    }

    /**
     * Obtiene datos del jugador como DTO
     */
    public PlayerDTO getPlayerData() {
        if (player == null) {
            return null;
        }

        return player.toDTO();
    }

    public int getWorkspaceWidth() {
        return workspaceWidth;
    }

    public int getWorkspaceHeight() {
        return workspaceHeight;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }

    public List<PhysicsBallDTO> getBalls() {
        List<PhysicsBallDTO> result = new ArrayList<>();

        for (Asteroid b : balls){
            result.add(b.getPhysics());
        }

        return result;
    }

    public void createRandomBall() {
        int x = (int) (Math.random() * workspaceWidth);
        int y = (int) (Math.random() * workspaceHeight);;
        int vx = (int) (Math.random() * 3 + 1);  // velocidad entre 1 y 5
        int vy = (int) (Math.random() * 5 + 1);
        int radius = (int) (Math.random() * 16) + 5;  // radio entre 5 y 20

        addBall(x, y, vx, vy, radius, motor);
    }

    // ---------- Crear bola normal ----------
    private void addBall(int x, int y, int vx, int vy, int radius,
                         PhysicEngineInterface motor) {
        Asteroid ball = new Asteroid(x, y, vx, vy, radius, this, motor);
        balls.add(ball);
    }

    public void Restart(){
        for (Asteroid b : balls){
            b.kill();
        }
        balls.clear();
    }

    public void eventDetector(PhysicsBallDTO physicsBallDTO){

        int diameter = physicsBallDTO.radius * 2;

        if (physicsBallDTO.x <= 0){
           controller.eventManager(Events.West_Reached, physicsBallDTO.x, physicsBallDTO.y);
        } else if (physicsBallDTO.x + diameter >= workspaceWidth) {
            controller.eventManager(Events.East_Reached, physicsBallDTO.x, physicsBallDTO.y);
        }

        if (physicsBallDTO.y <= 0) {
            controller.eventManager(Events.North_Reached,physicsBallDTO.x,physicsBallDTO.y);
        }else if(physicsBallDTO.y + diameter >= workspaceHeight){
            controller.eventManager(Events.South_Reached, physicsBallDTO.x, physicsBallDTO.y);
        }
    }

    public void moveLeft(){
        player.moveLeft();
    }

    public void moveUP(){
        player.moveUp();
    }

    public void moveRight(){
        player.moveRight();
    }

    public void moveDown(){
        player.moveDown();
    }

    public Player getPlayer() {
        return player;
    }

    public void StopX() {
        player.stopX();
    }

    public void StopY(){
        player.stopY();
    }
}
