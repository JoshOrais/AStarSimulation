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
    private Vertex currentVertex;


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

    // TEST METHOD
    public void setHCost() {
        for (int i=0; i<size; i++) {
            graph.setHCost(vertexNames[i], 0);
        }
    }

    public void step() {
        //values for stepStatus
        //1 = dequeue from openList
        //2 = get neighbors
        //3 = evaluate each neighbor
        //4 = set dequeued as visited

        if (stepStatus == 1) {
            dequeued = openList.dequeue();
            highlighted = new String[] {dequeued};
            currentVertex = null;
            stepStatus = 2;

            //test
            System.out.println("Dequeued: " + dequeued);
            //
        }

        else if (stepStatus == 2) {
            neighbors = graph.getAdjacentVertices(dequeued);
            highlighted = neighbors;
            neighborsCounter = 0;
            currentVertex = null;
            stepStatus = 3;

            //test
            System.out.print("Neighbors: ");
            for(String str : neighbors) {
                System.out.print(str + " ");
            }
            System.out.println();
            //
        }

        else if (stepStatus == 3) {
            if (neighborsCounter < neighbors.length) {
                int i = neighborsCounter;

                //test
                System.out.println("Neighbor " + i + ": " + neighbors[i]);

                float parentGCost = graph.getGCost(dequeued);
                float edgeWeight = graph.getEdgeWeight(dequeued, neighbors[i]);
                float vertexWeight = graph.getVertexWeight(neighbors[i]);
                float updatedGCost = parentGCost + edgeWeight + vertexWeight;

                if (neighbors[i].equals(end)) {
                    graph.setGCost(neighbors[i], updatedGCost);
                    graph.setParent(neighbors[i], dequeued);
                    done = true;

                    //test
                    System.out.println("SOLVER DONE!!!");
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
                currentVertex = graph.getVertex(neighbors[i]);
                stepStatus = 3;
            }
            
            else {
                highlighted = null;
                currentVertex = null;
                stepStatus = 4;
            }
        }

        if (stepStatus == 4) {
            graph.setVisited(dequeued, true);
            highlighted = null;
            currentVertex = null;
            stepStatus = 1;
        }
    }

    public String getDequeued() {
        return dequeued;
    }
    public String[] getNeighbors() {
        return neighbors;
    }

    public String[] getCurrentPath() {
        ArrayList<String> path = new ArrayList<String>();
        Vertex vertex = graph.getVertex(dequeued);

        if (vertex.parent == null) {
            path.add(vertex.name);
        }
        else {
            if (done) {
                path.add(end);
            }

            path.add(dequeued);
            while (!vertex.parent.name.equals(start)) {
                path.add(vertex.parent.name);
                vertex = vertex.parent;
            }
            path.add(vertex.parent.name);
        }

        ArrayList<String> reverse = new ArrayList<String>();

        for (int i=path.size()-1; i>=0; i--) {
            reverse.add(path.get(i));
        }

        String[] pathArr = reverse.toArray(new String[reverse.size()]);

        return pathArr;
    }

    public float getPathWeight() {
        return graph.getGCost(end);
    }

    public String[] getHighlighted() {
        return highlighted;
    }

    public Vertex getCurrentVertex() {
        return currentVertex;
    }

    public String[] getQueue() {
        return openList.getVertices();
    }

    public int getStep() {
        return stepStatus;
    }

    public float getGCost(String name) {
        return graph.getGCost(name);
    }
    public float getHCost(String name) {
        return graph.getHCost(name);
    }
    public float getFCost(String name) {
        return graph.getFCost(name);
    }

}
