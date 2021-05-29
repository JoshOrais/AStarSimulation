package Panels;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;

import DataStructures.*;

public class TablePanel extends JPanel {

    private String[][] content = {};
    private String[] header = {"Vertex", "G(n)", "H(n)", "F(n)", "Prev"};
    private JTable table;
    private JScrollPane scrollPane;

    public TablePanel(Dimension dimension) {
        this.setSize(dimension);
        this.setBackground(Color.green);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        table = new JTable(content, header);
        scrollPane = new JScrollPane(table);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void setContent(Graph graph) {

    }

    public void updatePanel() {

    }

    public void clearPanel() {
        
    }
}