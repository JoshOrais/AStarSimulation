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
    // private ControlPanel controlPanel;
    private JButton fileInput, rndInput, start, pause, reset, result, exit;
    private JSlider slider;

    private File inputFile;
    private int sliderSpeed;
    private Graph graph;

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
        rndInput = new JButton("RANDOM INPUT");
        JLabel speed = new JLabel("ANIMATION SPEED:");
        slider = new JSlider();
        start = new JButton("START");
        pause = new JButton("PAUSE");
        reset = new JButton("RESET");
        result = new JButton("RESULT");
        exit = new JButton("EXIT");

        input.setBounds(1125,75,150,25);
        fileInput.setBounds(1140,110,170,30);
        rndInput.setBounds(1140,150,170,30);
        speed.setBounds(1125,300,150,25);
        slider.setBounds(1140,325,170,40);
        start.setBounds(1140, 450, 80, 50);
        pause.setBounds(1230, 450, 80, 50);
        reset.setBounds(1140, 510, 170,40);
        result.setBounds(1140,575, 170, 50);
        exit.setBounds(1140,675, 170, 30);
        
        frame.add(algoPanel);
        frame.add(tablePanel);
        frame.add(graphPanel);
        frame.add(input);
        frame.add(fileInput);
        frame.add(rndInput);
        frame.add(speed);
        frame.add(slider);
        frame.add(start);
        frame.add(pause);
        frame.add(reset);
        frame.add(result);
        frame.add(exit);

        frame.setVisible(true);

        componentEnabler(true, true, false, false, false, false, false);
        
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

                        componentEnabler(true, true, true, true, false, false, false);

                        System.out.println("Selected File is: " + inputFile.getName());

                        GraphReader graphReader = new GraphReader(inputFile);
                        graph = graphReader.getGraph();

                        tablePanel.setContent(graph);
                        graphPanel.setContent(graph);

                        System.out.println("Contents set for Table and Graph");
                    } 
                }

                if (e.getSource() == rndInput){
                    System.out.println("Generate Random");

                    componentEnabler(true, true, true, true, false, false, false);

                    RandomGraphGenerator randomGraphGenerator = new RandomGraphGenerator();
                    graph = randomGraphGenerator.generate();

                    System.out.println("Random Graph Generated!");

                    tablePanel.setContent(graph);
                    graphPanel.setContent(graph);

                    System.out.println("Contents set for Table and Graph");
                }
                
                if (e.getSource() == start){
                    System.out.println("Start Pressed");
                    
                    componentEnabler(true, true, true, true, true, true, false);
                }

                if (e.getSource() == pause){
                    System.out.println("Pause Pressed");
                }

                if (e.getSource() == reset){
                    System.out.println("Reset Pressed");
                }

                if (e.getSource() == result){
                    System.out.println("Result Pressed");
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
                sliderSpeed = slider.getValue();

                System.out.println(sliderSpeed);
            }
        };

        fileInput.addActionListener(actionListener);
        rndInput.addActionListener(actionListener);
        start.addActionListener(actionListener);
        pause.addActionListener(actionListener);
        reset.addActionListener(actionListener);
        result.addActionListener(actionListener);
        exit.addActionListener(actionListener);

        slider.addChangeListener(changeListener);
    }

    private void componentEnabler(  boolean fileInputStatus, boolean rndInputStatus, boolean sliderStatus, boolean startStatus, 
                                    boolean pauseStatus, boolean resetStatus, boolean resultStatus) {
        fileInput.setEnabled(fileInputStatus);
        rndInput.setEnabled(rndInputStatus);
        slider.setEnabled(sliderStatus);
        start.setEnabled(startStatus);
        pause.setEnabled(pauseStatus);
        reset.setEnabled(resetStatus);
        result.setEnabled(resultStatus);
    }

    public static void main (String [] args) {
        new MainFrame();
    }
}