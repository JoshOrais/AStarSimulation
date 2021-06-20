package Panels;

import java.awt.Dimension;

import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;

public class AlgoPanel extends JPanel {

    private JTextArea algoProgress, queue, path;
    private JScrollPane algoScrollPane, queueScrollPane, pathScrollPane;

    public AlgoPanel(Dimension dimension) {
        this.setSize(dimension);
        this.setBackground(Color.black);
        this.setLayout(null);

        algoProgress = new JTextArea("PROGRESS:\n");
        queue = new JTextArea("QUEUE: ");
        path = new JTextArea("PATH: ");
        algoProgress.setEditable(false);
        queue.setEditable(false);
        path.setEditable(false);
        
        algoScrollPane = new JScrollPane(algoProgress, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        queueScrollPane = new JScrollPane(queue, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pathScrollPane = new JScrollPane(path, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        algoScrollPane.setBounds(5, 5, 340, 260);
        queueScrollPane.setBounds(5, 265, 340, 50);
        pathScrollPane.setBounds(5, 315, 340, 50);

        this.add(algoScrollPane);
        this.add(queueScrollPane);
        this.add(pathScrollPane);
    }

    public void setDequeued(String vertexName) {
        algoProgress.setText("PROGRESS:" + "\n     Dequeued Vertex: [" + vertexName + "]");
    }
    public void setNeighbors(String[] neighbors) {
        String content = algoProgress.getText();
        content = content + ("\n     Neighbors: ");
        for (int i=0; i<neighbors.length; i++) {
            content += ("[" + neighbors[i] + "] ");
        }
        algoProgress.setText(content);
    }
    public void reset() {
        algoProgress.setText("PROGRESS:");
    }

    public void updateQueue(String[] vertices) {
        String content = "QUEUE: ";
        for (int i=0; i<vertices.length; i++) {
            content += ("[" + vertices[i] + "] ");
        }
        queue.setText(content);
    }
    public void updatePath(String[] vertices) {
        String content = "PATH: ";
        for (int i=0; i<vertices.length-1; i++) {
            content += ("[" + vertices[i] + "] -> ");
        }
        content += ("[" + vertices[vertices.length-1] + "]");
        path.setText(content);
    }

    public void setDone(float cost) {
        String algoContent = algoProgress.getText();
        algoContent += ("\nALGORITHM DONE");
        algoProgress.setText(algoContent);

        String pathContent = path.getText();
        pathContent += ("\nTOTAL COST:   " + cost);
        path.setText(pathContent);
    }

    public void setDefault() {
        algoProgress.setText("PROGRESS:");
        queue.setText("PROGRESS:");
        path.setText("PROGRESS:");
    }
}