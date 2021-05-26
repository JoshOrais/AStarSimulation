package Panels;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;

public class AlgoPanel extends JPanel {

    private JTextArea textArea;
    private JScrollPane scrollPane;

    public AlgoPanel(Dimension dimension) {
        this.setSize(dimension);
        this.setBackground(Color.red);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        textArea = new JTextArea("Hello World");
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void updatePanel() {

    }

    public void clearPanel() {
        
    }
}