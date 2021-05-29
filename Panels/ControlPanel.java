package Panels;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.Color;

public class ControlPanel extends JPanel {

    private JSlider slider;
    private JButton start;
    private JButton pause;
    private JButton reset;
    private JButton result;
    private JButton exit;

    public ControlPanel(Dimension dimension) {
        this.setSize(dimension);
        this.setBackground(Color.green);
        this.setLayout(null);
    }

}