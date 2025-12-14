package vista;

import physicsBall.PhysicsBallDTO;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Vista extends JFrame {

    private ControlPanel controlPanel;
    private Viewer viewer;
    private DataPanel datapanel;

    public Vista() {
        this.controlPanel = new ControlPanel();
        this.viewer = new Viewer();
        this.datapanel = new DataPanel();

        setupFrame();
        setupLayout();
        initialize();
    }

    //Jframe

    private void setupFrame() {
        setTitle("Balls");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    //setup Layout config
    private void setupLayout() {
        GridBagConstraints gbc = new GridBagConstraints();

        // ControlPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 0.3;
        gbc.fill = GridBagConstraints.BOTH;
        add(controlPanel, gbc);

        // DataPanel
        gbc.gridy = 1;
        gbc. weighty = 0.3;
        add(datapanel, gbc);

        // Jconfig
        JPanel Jconfig = new JPanel();
        gbc.gridy = 2;
        gbc.weighty = 0.4;
        add(Jconfig, gbc);

        // Viewer
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc. fill = GridBagConstraints. BOTH;
        add(viewer, gbc);
    }

    private void initialize() {
        updateNumPelotas(0);
        viewer.start();
        mostrarPelotas(new ArrayList<>());
    }

    /**
     * Muestra la ventana
     */
    public void display() {
        setVisible(true);
    }

    // Getters

    public int getViewerWidth() {
        return viewer.getWidth();
    }

    public int getViewerHeight() {
        return viewer.getHeight();
    }

    //update Methods

    public void updateNumPelotas(int numPelotas) {
        datapanel.setNumpelotas(numPelotas);
    }

    public void setPlayerModeActive(boolean active) {
        if (active) {
            controlPanel.disablebutton();
        } else {
            controlPanel.enablebutton();
        }
    }

    public void mostrarPelotas(List<PhysicsBallDTO> balls) {
        viewer.setBalls(balls);
    }

    public void setPlayer(PhysicsBallDTO player) {
        viewer.setPlayer(player);
    }

    //listeners

    public void addNewPelotaListener(ActionListener listener) {
        controlPanel. btnAddBall.addActionListener(listener);
    }

    public void addnewPauseListener(ActionListener listener) {
        controlPanel.btnpause. addActionListener(listener);
    }

    public void addNewPlayListener(ActionListener listener) {
        controlPanel.btnPlay.addActionListener(listener);
    }

    public void addNewRestartlistener(ActionListener listener) {
        controlPanel.btnRestart.addActionListener(listener);
    }

    public void generatePJ1(ActionListener listener) {
        controlPanel.btnGenrarPJ1.addActionListener(listener);
    }
}