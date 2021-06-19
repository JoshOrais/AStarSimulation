import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.io.File;

import Panels.*;
import DataStructures.*;

public class MainFrame{

    private JFrame frame;
    private AlgoPanel algoPanel;
    private TablePanel tablePanel;
    private GraphPanel graphPanel;
    private JButton fileInput, startButton, exit;
    private JSlider slider;
    private JLabel startLabel, destinationLabel;
    private JTextField startInput, destinationInput;
    private File inputFile;
    private int animationSpeed = 50;
    private Graph graph;
    private AStarSolver solver;

    public MainFrame() {
        System.out.println("START");

        Dimension frameDimension = new Dimension(1350, 750);
        Dimension leftPanelDimension = new Dimension(350, 375);
        Dimension centerPanelDimension = new Dimension(750, 750);

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setLayout(null);
        frame.setBounds(200, 200, frameDimension.width, frameDimension.height);

        algoPanel = new AlgoPanel(leftPanelDimension);
        tablePanel = new TablePanel(leftPanelDimension);
        graphPanel = new GraphPanel(centerPanelDimension);

        algoPanel.setLocation(0,0);
        tablePanel.setLocation(0,376);
        graphPanel.setLocation(351,0);

        JLabel input = new JLabel("SELECT INPUT:");
        fileInput = new JButton("SELECT FILE");
        JLabel speed = new JLabel("ANIMATION SPEED:");
        slider = new JSlider();
        startButton = new JButton("START");
        startLabel = new JLabel("Starting Vertex");
        destinationLabel = new JLabel("Destination Vertex");
        startInput = new JTextField();
        destinationInput = new JTextField();
        exit = new JButton("EXIT");

        input.setBounds(1125,75,150,25);
        fileInput.setBounds(1140,110,170,30);
        speed.setBounds(1125,300,150,25);
        slider.setBounds(1140,325,170,40);
        startLabel.setBounds(1140,375,170,25);
        startInput.setBounds(1140,400,170,25);
        destinationLabel.setBounds(1140,435,170,25);
        destinationInput.setBounds(1140,460,170,25);
        startButton.setBounds(1140, 510, 170,50);
        exit.setBounds(1140,675, 170, 30);
        
        frame.add(algoPanel);
        frame.add(tablePanel);
        frame.add(graphPanel);
        frame.add(input);
        frame.add(fileInput);
        frame.add(speed);
        frame.add(slider);
        frame.add(startLabel);
        frame.add(startInput);
        frame.add(destinationLabel);
        frame.add(destinationInput);
        frame.add(startButton);
        frame.add(exit);

        frame.setVisible(true);

        System.out.println("Instantiation Done");

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                if (e.getSource() == fileInput){
                    System.out.println("Choose File");

                    JFileChooser fileChooser = new JFileChooser();
                    int response = fileChooser.showOpenDialog(null);

                    if (response == JFileChooser.APPROVE_OPTION) {
                        inputFile = fileChooser.getSelectedFile();

                        System.out.println("Selected File is: " + inputFile.getName());

                        GraphReader graphReader = new GraphReader(inputFile);
                        graph = graphReader.getGraph();

                        tablePanel.setContent(graph);
                        graphPanel.setContent(graph);

                        System.out.println("Contents set for Table and Graph");
                    } 
                }
                
                if (e.getSource() == startButton){
                    System.out.println("Start Pressed");

                    String startVertex = startInput.getText();
                    String destinationVertex = destinationInput.getText();

                    graphPanel.setStartVertex(startVertex);
                    graphPanel.setDestVertex(destinationVertex);

                    tablePanel.setHeuristic(graphPanel.getEuclidean());

                    //instantiate solver with graph input
                    //set heuristics for solver
                    //instantiate thread class
                    //start thread

                    solver = new AStarSolver(graph, startVertex, destinationVertex);
                    solver.setHCost(graphPanel.getEuclidean());
                }

                if (e.getSource() == exit){
                    System.out.println("Exiting");
                    System.exit(0);
                }
            }
        };

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e){                
                animationSpeed = animationSpeed * slider.getValue();

                System.out.println(slider.getValue());
            }
        };

        fileInput.addActionListener(actionListener);
        startButton.addActionListener(actionListener);
        exit.addActionListener(actionListener);
        slider.addChangeListener(changeListener);
    }

    public void step() {

    }

    public class AnimatorThread implements Runnable {
        public AnimatorThread() {

        }
        
        @Override
        public void run() {
            //while solver is not done
                //frame.step()
                //frame.repaint()
                //thread.sleep(animationSpeed)
        }
    }

    public static void main (String [] args) {
        new MainFrame();
    }
}