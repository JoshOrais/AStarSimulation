package DataStructures;

import java.util.ArrayList;

public class AStarSolver {
    private Graph graph;
    private int size;
    private String[] vertexNames;
    private String start, end;
    private PriorityQueue openList = new PriorityQueue();
    public int stepStatus = 1;
    public boolean done = false;

    private String dequeued;
    private String[] neighbors;
    private int neighborsCounter;
    private String[] highlighted;


    public AStarSolver(Graph g, String s, String e) {
        graph = g;
        size = g.getVertexNum();
        vertexNames = g.getVertexNames();
        start = s;
        end = e;

        for (int i=0; i<size; i++) {
            if (vertexNames[i].equals(start)) {
                graph.setGCost(vertexNames[i], 0);
            }
            else {
                graph.setGCost(vertexNames[i], Float.POSITIVE_INFINITY);
            }
        }

        openList.enqueue(start, graph.getFCost(start));
    }

    public void setHCost(float[] heuristics) {
        for (int i=0; i<size; i++) {
            graph.setHCost(vertexNames[i], heuristics[i]);
        }
    }

    //TEST METHOD
    // public void setHCost() {
    //     for (int i=0; i<size; i++) {
    //         graph.setHCost(vertexNames[i], 0);
    //     }
    // }

    // public void start() {

    //     while (done == false) {

    //         String topVertex = openList.dequeue();
    //         String[] neighbors = graph.getAdjacentVertices(topVertex);

    //         for (int i=0; i<neighbors.length; i++) {

    //             float parentGCost = graph.getGCost(topVertex);
    //             float edgeWeight = graph.getEdgeWeight(topVertex, neighbors[i]);
    //             float vertexWeight = graph.getVertexWeight(neighbors[i]);
    //             float updatedGCost = parentGCost + edgeWeight + vertexWeight;

    //             if (neighbors[i].equals(end)) {
    //                 graph.setGCost(neighbors[i], updatedGCost);
    //                 graph.setParent(neighbors[i], topVertex);

    //                 done = true;
    //                 break;
    //             }

    //             else {
    //                 if (!graph.visited(neighbors[i])) {
    //                     graph.setGCost(neighbors[i], updatedGCost);
    //                     graph.setParent(neighbors[i], topVertex);
                        
    //                     openList.enqueue(neighbors[i], graph.getFCost(neighbors[i]));
    //                 }
    //                 else {
    //                     float existingF = graph.getFCost(neighbors[i]);
    //                     float newF = graph.getHCost(neighbors[i]) + updatedGCost;
    //                     if (newF < existingF) {
    //                         graph.setGCost(neighbors[i], updatedGCost);
    //                         graph.setParent(neighbors[i], topVertex);
                            
    //                         openList.enqueue(neighbors[i], graph.getFCost(neighbors[i]));
    //                     }
    //                 } 
    //             }
    //         }

    //         graph.setVisited(topVertex, true);
    //     }
    // }

    public void step() {
        //values for stepStatus
        //1 = dequeue from openList
        //2 = get neighbors
        //3 = evaluate each neighbor
        //4 = set dequeued as visited

        if (stepStatus == 1) {
            dequeued = openList.dequeue();
            highlighted = new String[] {dequeued};
            stepStatus = 2;
        }

        if (stepStatus == 2) {
            neighbors = graph.getAdjacentVertices(dequeued);
            highlighted = neighbors;
            neighborsCounter = 0;
            stepStatus = 3;
        }

        if (stepStatus == 3) {
            if (neighborsCounter < neighbors.length) {
                int i = neighborsCounter;

                float parentGCost = graph.getGCost(dequeued);
                float edgeWeight = graph.getEdgeWeight(dequeued, neighbors[i]);
                float vertexWeight = graph.getVertexWeight(neighbors[i]);
                float updatedGCost = parentGCost + edgeWeight + vertexWeight;

                if (neighbors[i].equals(end)) {
                    graph.setGCost(neighbors[i], updatedGCost);
                    graph.setParent(neighbors[i], dequeued);
                    done = true;
                }

                else {
                    if (!graph.visited(neighbors[i])) {
                        graph.setGCost(neighbors[i], updatedGCost);
                        graph.setParent(neighbors[i], dequeued);
                        
                        openList.enqueue(neighbors[i], graph.getFCost(neighbors[i]));
                    }
                    else {
                        float existingF = graph.getFCost(neighbors[i]);
                        float newF = graph.getHCost(neighbors[i]) + updatedGCost;
                        if (newF < existingF) {
                            graph.setGCost(neighbors[i], updatedGCost);
                            graph.setParent(neighbors[i], dequeued);
                            
                            openList.enqueue(neighbors[i], graph.getFCost(neighbors[i]));
                        }
                    } 
                }

                neighborsCounter++;
                highlighted = new String[]{neighbors[i]};
                stepStatus = 3;
            }
            
            else {
                highlighted = null;
                stepStatus = 4;
            }
        }

        if (stepStatus == 4) {
            graph.setVisited(dequeued, true);
            highlighted = null;
            stepStatus = 1;
        }
    }

    public ArrayList<String> getPath() {
        ArrayList<String> path = new ArrayList<String>();
        Vertex vertex = graph.getVertex(end);

        path.add(end);
        while (!vertex.parent.name.equals(start)) {
            path.add(vertex.parent.name);
            vertex = vertex.parent;
        }
        path.add(vertex.parent.name);

        ArrayList<String> reverse = new ArrayList<String>();

        for (int i=path.size()-1; i>=0; i--) {
            reverse.add(path.get(i));
        }

        return reverse;
    }

    public float getPathWeight() {
        return graph.getGCost(end);
    }

    public String[] getHighlighted() {
        return highlighted;
    }
}
