package DataStructures;

public class Vertex {
    public String name;
    public float weight;

    public float gCost;
    public float hCost;
    public float fCost;
    public Vertex parent;

    public boolean visited;

    public Vertex(String vertexName, float vertexWeight) {
        name = vertexName;
        weight = vertexWeight;
    }
    
    public Vertex(String vertexName) {
        name = vertexName;
        weight = 0;
    }
}
