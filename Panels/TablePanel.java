package Panels;

import java.awt.Dimension;
import javax.swing.*; // specify all the classes to be imported
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.BorderLayout;

import DataStructures.*;

public class TablePanel extends JPanel {

    private String[][] content = {};
    private String[] header = {"Vertex", "Cost of G", "Cost of H", "Cost of F", "Parent"};
    private TableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;

    public TablePanel(Dimension dimension) {
        this.setSize(dimension);
        this.setBackground(Color.green);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        tableModel = new DefaultTableModel(content, header);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void setContent(Graph graph) {
        Vertex[] vertexArray = graph.getVertexArray();
        //find heuristic

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String[] rowData = new String[5];

        for (int i=0; i<vertexArray.length; i++) {
            rowData[0] = vertexArray[i].name;
            rowData[1] = "infinity";
            rowData[2] = "-";
            rowData[3] = "infinity";
            rowData[4] = "-";
            model.addRow(rowData);
        }
    }

    public void setHeuristic(float[] heuristic) {
        for (int i=0; i<table.getRowCount(); i++) {
            // System.out.println(i + " " + table.getRowCount());
            table.setValueAt(String.valueOf(heuristic[i]), i, 2);
        }
    }

    public void updatePanel() {
        table.setValueAt("hello", 2, 3);
    }

    public void clearPanel() {
        
    }
}