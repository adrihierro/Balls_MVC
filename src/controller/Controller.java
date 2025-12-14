package controller;

import model.*;
import vista.*;

import java.awt.*;

public class Controller {
    private final Model model;
    private final Vista vista;

    public Controller() {
        this.vista = new Vista();
        this.model = new Model();

        connectListeners();
        syncWorkspaceDimensions();
        vista.display();
    }

    private void connectListeners() {
        vista.addNewPelotaListener(e -> {
            syncWorkspaceDimensions();
            HandleAddBall();
        });

        vista.addnewPauseListener(e -> model.pause());

        vista.addNewPlayListener(e -> {
            handlePlay();
        });

        vista.addNewRestartlistener(e -> {
            HandleRestart();
        });

        vista.generatePJ1(e -> {
            Player player = model.generatePlayer();
            vista.setPlayer(player.getPhysics());
        });
    }

    private void HandleAddBall(){
        model.createRandomBall();
        refreshView();
    }

    private void HandleRestart(){
        model.clearBalls();
        vista.setPlayerModeActive(false);
        refreshView();
    }

    private void handlePlay(){
        model.play();
    }

    private void syncWorkspaceDimensions() {
        int width = vista.getViewerWidth();
        int height = vista.getViewerHeight();
        model.setWorkspaceDimensions(width, height);
    }

    private void refreshView() {
        vista.mostrarPelotas(model.getBalls());
        updateNumPelotas();
    }

    private void updateNumPelotas() {
        int numPelotas = model.getBalls().size();
        vista.updateNumPelotas(numPelotas);
    }
}
