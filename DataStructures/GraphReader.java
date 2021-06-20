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
                if (line.charAt(0) != '/') {
                    if (line.equals("EDGES")) {
                        flag = 1;
                    }
                    else {
                        if (flag == 0) {
                            String[] subString = line.split(":");
                            Vertex vertex = new Vertex(subString[0], (Float.valueOf(subString[1])));
                            graph.insertVertex(vertex);
                        }
                        else if (flag == 1) {
                            String[] subString1 = line.split(":");
                            String[] subString2 = subString1[0].split(",");
                            graph.insertEdge(subString2[0], subString2[1], (Float.valueOf(subString1[1])));
                        }
                    }  
                }  
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Graph getGraph() {
        return graph;
    }
}