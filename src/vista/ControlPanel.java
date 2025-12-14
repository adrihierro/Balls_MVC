package vista;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public JButton btnAddBall;
    public JButton btnpause;
    public JButton btnRestart;
    public JButton btnPlay;
    public JButton btnGenrarPJ1;

    public ControlPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        //Fila 0

        btnPlay = new JButton("Play");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(btnPlay,gbc);


        btnpause = new JButton("Pause");
        gbc.gridx = 1;
        add(btnpause,gbc);

        btnRestart = new JButton("Restart");
        gbc.gridx = 2;
        add(btnRestart,gbc);

        //Fila 1
        btnAddBall = new JButton("Spawn Asteroid");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(btnAddBall,gbc);

        btnGenrarPJ1 = new JButton("Generate ship");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        add(btnGenrarPJ1,gbc);

        //caja invisible para ajustar los botones en el norte
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.weighty = 1; // ocupa el resto del espacio vertical
        add(Box.createVerticalGlue(), gbc);
    }

    public void disablebutton(){
        btnGenrarPJ1.setEnabled(false);
    }

    public void enablebutton(){
        btnGenrarPJ1.setEnabled(true);
    }

}