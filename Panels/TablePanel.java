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
    private Graph graph;
    private int size;

    public TablePanel(Dimension dimension) {
        this.setSize(dimension);
        this.setBackground(Color.black);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        tableModel = new DefaultTableModel(content, header);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void setContent(Graph g) {
        graph = g;
        size = g.getVertexNum();
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String[] rowData = new String[5];

        String[] vertexNames = graph.getVertexNames();
        for (int i=0; i<size; i++) {
            rowData[0] = vertexNames[i];
            rowData[1] = "-";
            rowData[2] = "-";
            rowData[3] = "-";
            rowData[4] = "-";
            model.addRow(rowData);
        }

        setDefaultValues();
    }

    public void setDefaultValues() {
        for (int i=0; i<size; i++) {
            table.setValueAt("infinity", i, 1);
            table.setValueAt("infinity", i, 3);
        }
    }

    public void setHeuristic(float[] heuristic) {
        for (int i=0; i<size; i++) {
            table.setValueAt(String.valueOf(heuristic[i]), i, 2);
        }
    }
    public void setGCost(String name, float g) {
        int pos = graph.findPos(name);
        table.setValueAt(String.valueOf(g), pos, 1);
    }
    public void setFCost (String name, float g, float h) {
        int pos = graph.findPos(name);
        float fValue = g + h;
        table.setValueAt(String.valueOf(fValue), pos, 3);
    }
    public void setParent (String name, String parent) {
        int pos = graph.findPos(name);
        table.setValueAt(parent, pos, 4);
    }

    public void updateRow (Vertex vertex) {
        if (vertex != null) {
            int pos = graph.findPos(vertex.name);
            float g = vertex.gCost;
            float f = vertex.fCost;
            String parentName;
            if (vertex.parent == null) {
                parentName = null;
            }
            else {
                parentName = vertex.parent.name;
            }

            table.setValueAt(String.valueOf(g), pos, 1);
            table.setValueAt(String.valueOf(f), pos, 3);
            table.setValueAt(String.valueOf(parentName), pos, 4);
        } 
    }
}