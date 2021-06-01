package DataStructures;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class GraphReader {
    
    private Graph graph;

    public GraphReader(File file){
        graph = new Graph();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);

            String line;
            int flag = 0;

            while ((line = br.readLine()) != null) {
                if (line.equals("EDGES")) {
                    flag = 1;
                }
                else {
                    if (flag == 0) {
                        String[] subString = line.split(":");
                        Vertex vertex = new Vertex(subString[0], (Double.valueOf(subString[1])));
                        graph.insertVertex(vertex);

                        System.out.println("VERTEX: " + subString[0] + " WEIGHT: " + subString[1]);
                    }
                    else if (flag == 1) {
                        String[] subString1 = line.split(":");
                        String[] subString2 = subString1[0].split(",");
                        graph.insertEdge(subString2[0], subString2[1], (Double.valueOf(subString1[1])));

                        System.out.println("VERTEX 1: " + subString2[0] + " VERTEX 2: " + subString2[1] + " WEIGHT: " + subString1[1]);
                    }
                }  
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        graph.dislay();
    }

    public Graph getGraph() {
        return graph;
    }
}