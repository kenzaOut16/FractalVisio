package fractale.View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import  fractale.Model.*;

// Contient la frame principale et lui ajoute ses composants
public class GUI extends JFrame{
    public GUI(){
        setTitle("Explorateur de fractals");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(1100, 900);
        setLayout(new BorderLayout());

        ButtonPanel panelButton = null;
        FractalPanel panel = new FractalPanel(panelButton);
        panelButton = new ButtonPanel(panel);
        add(panel, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setBackground(Color.green);

    }

}
