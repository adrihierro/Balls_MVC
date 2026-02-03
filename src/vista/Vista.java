package vista;

import controller.Controller;
import model.Events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Vista extends JFrame implements KeyListener {

    private ControlPanel controlPanel;
    private DataPanel datapanel;
    private Controller controller;
    private Viewer viewer;

    public Vista(Controller controller) {
        this.controller = controller;
        this.controlPanel = new ControlPanel();
        this.datapanel = new DataPanel();
        this.viewer = new Viewer(this);

        setupFrame();
        setupLayout();
    }

    //Jframe

    private void setupFrame() {
        setTitle("Balls");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setExtendedState(JFrame. MAXIMIZED_BOTH);
    }

    //setup Layout config
    private void setupLayout() {
        GridBagConstraints gbc = new GridBagConstraints();

        // ControlPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.08;
        gbc. weighty = 0.3;
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
        gbc. gridheight = 3;
        gbc.weightx = 0.92;  // Aumentado de 0.7 a 0.85
        gbc.weighty = 1.0;
        gbc. fill = GridBagConstraints. BOTH;
        add(viewer, gbc);
    }

    public void renderBalls(){
        List<BallRenderInfoDTO> balls = controller.getBallRenderables();
        viewer.updateRenderData(balls);
    }

    /**
     * Muestra la ventana
     */
    public void display() {
        setVisible(true);

        viewer.setSize(viewer.getParent().getSize());
        viewer.setPreferredSize(viewer.getParent().getSize());
        viewer.revalidate();


        viewer.setFocusable(true);
        viewer.requestFocusInWindow();
        viewer.addKeyListener(this);

        viewer.start();
    }

    public void updatePelotas(int numpelotas){
        datapanel.setNumpelotas(numpelotas);
    }

    public void addNewPelotaListener(ActionListener listener) {
        controlPanel.btnAddBall.addActionListener(listener);
    }

    public void RestartListener(ActionListener listener){
        controlPanel.btnRestart.addActionListener(listener);
    }

    public void PauseListener(ActionListener listener){
        controlPanel.btnpause.addActionListener(listener);
    }

    public void GeneratePJ(ActionListener listener){
        controlPanel.btnGenrarPJ1.addActionListener(listener);
    }

    public void startListener(ActionListener listener){
        controlPanel.btnPlay.addActionListener(listener);
    }

    public void updateWorkspaceSize(int width, int height) {
        controller.updateWorkspaceSize(width, height);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> controller.getMoveUP();
            case KeyEvent.VK_DOWN -> controller.getMoveDown();
            case KeyEvent.VK_LEFT -> controller.getMoveLeft();
            case KeyEvent.VK_RIGHT -> controller.getMoveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP -> controller.getStopY();
            case KeyEvent.VK_DOWN -> controller.getStopY();
            case KeyEvent.VK_LEFT -> controller.getStopX();
            case KeyEvent.VK_RIGHT -> controller.getStopX();
        }
    }

    public PlayerRenderInfoDTO getPlayerRenderInfo(){
        return controller.getPlayerRenderInfo();
    }

    public void CollisionDetector(Events events,int x,int y){
        System.out.println("Vista:Colision: --> " + events);

        viewer.onCollisionEvent(events,x,y);
    }

    public void applyWorld(WorldRenderableDTO worldDTO){
        viewer.setWorld(worldDTO);
    }
}