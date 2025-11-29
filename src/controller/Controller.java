package controller;

import model.*;
import physicsBall.BasicPhysicsEngine;
import physicsBall.PhysicEngineInterface;
import vista.*;

import java.awt.*;

public class Controller {
    private final Model model;
    private final Vista vista;

    public Controller() {
        this.vista = new Vista();
        this.model = new Model();

        initView();
        connectListeners();
    }

    //Inicialización de la vista
    private void initView() {
        vista.setVisible(true);
        updateNumPelotas();
        vista.StartAnimation();
        vista.mostrarPelotas(model.getBalls());
    }

    private void connectListeners() {
        vista.addNewPelotaListener(e -> {
            addBall();
            updateNumPelotas();
        });

        vista.addnewPauseListener(e -> model.pause());

        vista.addNewPlayListener(e -> {
            model.play();
        });

        vista.addNewRestartlistener(e -> {
            model.getBalls().clear();
            updateNumPelotas();
            vista.setPlayerModeActive(false);
        });

        vista.generatePJ1(e -> {
            Player player = model.generatePlayer();
            vista.setPlayer(player.getPhysics());
        });
    }

    public void addBall() {
        int viewerWidth = getViewerWidth();
        int viewerHeight = getViewerHeight();

        int x = (int) (Math.random() * viewerWidth);
        int y = (int) (Math.random() * viewerHeight);
        int vx = (int) (Math.random() * 5 + 1);  //velocidad entre 1 y 5
        int vy = (int) (Math.random() * 5 + 1);
        int radius = (int) (Math.random() * 16) + 5;

        PhysicEngineInterface motor = new BasicPhysicsEngine();
        model.addBall(x, y, vx, vy, radius, viewerWidth, viewerHeight, motor);

        vista.mostrarPelotas(model.getBalls());
        updateNumPelotas();
    }

    private void updateNumPelotas() {
        int numPelotas = model.getBalls().size();
        vista.updateNumPelotas(numPelotas);
    }


    public int getViewerWidth() {
        return vista.getViewerWidth();
    }

    public int getViewerHeight() {
        return vista.getViewerHeight();
    }
}
