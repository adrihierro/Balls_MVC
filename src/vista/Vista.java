package vista;

import physicsBall.PhysicsBallDTO;

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

        setTitle("AnimacioPilota");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); // Centrar la ventana


        GridBagConstraints gbc = new GridBagConstraints();

        // ------------------- ControlPanel -------------------
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;   // ocupa el 30% horizontal
        gbc.weighty = 0.3;   // ocupa el 30% vertical
        gbc.fill = GridBagConstraints.BOTH;
        add(controlPanel, gbc);

        // ------------------- DataPanel -------------------
        gbc.gridy = 1;
        gbc.weighty = 0.3;   // ocupa 30% vertical
        add(datapanel, gbc);

        // ------------------- Jconfig -------------------
        JPanel Jconfig = new JPanel();
        gbc.gridy = 2;
        gbc.weighty = 0.4;   // ocupa 40% vertical
        add(Jconfig, gbc);

        // ------------------- Viewer -------------------
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;  // ocupa el alto de los 3 paneles de la izquierda
        gbc.weightx = 0.7;   // ocupa el 70% horizontal
        gbc.weighty = 1.0;   // ocupa todo vertical
        gbc.fill = GridBagConstraints.BOTH;
        add(viewer, gbc);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public Viewer getViewer() {
        return viewer;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public int getViewerWidth() {
        return viewer.getWidth();
    }

    public int getViewerHeight() {
        return viewer.getHeight();
    }


    public void updateNumPelotas(int numPelotas) {
        datapanel.setNumpelotas(numPelotas);
    }

    public void setPlayerModeActive(boolean active) {
        if (active) {
            controlPanel.disablebutton();  // o lo que corresponda
        } else {
            controlPanel.enablebutton();
        }
    }

    public void StartAnimation() {
        viewer.start();
    }

    public void mostrarPelotas(List<PhysicsBallDTO> balls) {
        viewer.setBalls(balls);
    }




    //botones

    public void addNewPelotaListener(ActionListener listener) {
        controlPanel.btnAddBall.addActionListener(listener);
    }

    public void addnewPauseListener(ActionListener listener){
        controlPanel.btnpause.addActionListener(listener);
    }

    public void addNewPlayListener(ActionListener listener){
        controlPanel.btnPlay.addActionListener(listener);
    }

    public void addNewRestartlistener(ActionListener listener){
        controlPanel.btnRestart.addActionListener(listener);
    }

    public void generatePJ1(ActionListener listener){
        controlPanel.btnGenrarPJ1.addActionListener(listener);
    }

    public void setPlayer(PhysicsBallDTO player) {
        viewer.setPlayer(player);
    }
}
