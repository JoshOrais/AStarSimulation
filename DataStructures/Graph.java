package DataStructures;

public class Graph {

    private Vertex[] vertexArray = new Vertex[0];
    private double[][] matrix = new double[0][0];
    private int vertexNum = 0;
    private int edgeNum = 0;

    public Graph() {
    }

    public void insertVertex(Vertex vertex) {
        int length = vertexArray.length;

        if (exists(vertex.name)) {
            System.out.println("Vertex " + vertex.name + " already exists!");
        }
        else {
            Vertex[] tempArray = new Vertex[length+1];
            double[][] tempMatrix = new double[length+1][length+1];

            for(int i=0; i<length; i++){
                tempArray[i] = vertexArray[i];
                for(int j=0; j<length; j++){
                    tempMatrix[i][j] = matrix[i][j];
                }
            }

            tempArray[length] = vertex;
            vertexArray = tempArray;
            
            for(int i=0; i<=length; i++){
                tempMatrix[length][i] = 0;
                tempMatrix[i][length] = 0;
            }

            matrix = tempMatrix;
            vertexNum++;
        }
    }

    public void removeVertex(String vertexName) {
        if(!exists(vertexName)){
            System.out.println("The vertex " + vertexName + " does not exist!!!");
        }
        else{
            int length = vertexArray.length;
            int pos = -1;

            for(int i=0; i<length; i++){
                if(vertexArray[i].name.equals(vertexName)){
                    pos = i;
                }
            }

            Vertex[] tempArray = new Vertex[length-1];
            double[][] tempMatrix = new double[length-1][length-1];

            for(int i=0; i<length; i++){
                if(i<pos){
                    tempArray[i] = vertexArray[i];

                    for(int j=0; j<length; j++){
                        if(j<pos){
                            tempMatrix[i][j] = matrix[i][j];
                        }
                        if(j>pos){
                            tempMatrix[i][j-1] = matrix[i][j];
                        }
                    }
                }
                if(i>pos){
                    tempArray[i-1] = vertexArray[i];

                    for(int j=0; j<length; j++){
                        if(j<pos){
                            tempMatrix[i-1][j] = matrix[i][j];
                        }
                        if(j>pos){
                            tempMatrix[i-1][j-1] = matrix[i][j];
                        }
                    }
                }
            }

            vertexArray = tempArray;
            matrix = tempMatrix;
            vertexNum--;
        }
    }

    public void insertEdge(String vertexNameA, String vertexNameB, double weight) {
        if(!exists(vertexNameA)){
            System.out.println("Vertex " + vertexNameA + " does not exist!!!");
        }
        if(!exists(vertexNameB)){
            System.out.println("Vertex " + vertexNameB + " does not exist!!!");
        }
        else{
            int posA = -1;
            int posB = -1;

            for(int i=0; i<vertexArray.length; i++){
                if(vertexArray[i].name.equals(vertexNameA)){
                    posA = i;
                }
                if(vertexArray[i].name.equals(vertexNameB)){
                    posB = i;
                }
            }

            if(matrix[posA][posB] > 0){
                System.out.println("The vertices are already connected!!!");
            }
            else{
                matrix[posA][posB] = weight;
                matrix[posB][posA] = weight;

                edgeNum++;
            }
        }
    }

    public void removeEdge(String vertexNameA, String vertexNameB) {
        if(!exists(vertexNameA)){
            System.out.println("Vertex " + vertexNameA + " does not exist!!!");
        }
        if(!exists(vertexNameB)){
            System.out.println("Vertex " + vertexNameB + " does not exist!!!");
        }
        else{
            int posA = -1;
            int posB = -1;

            for(int i=0; i<vertexArray.length; i++){
                if(vertexArray[i].name.equals(vertexNameA)){
                    posA = i;
                }
                if(vertexArray[i].name.equals(vertexNameB)){
                    posB = i;
                }
            }

            if(matrix[posA][posB] == 0){
                System.out.println("There is no Edge to remove!!!");
            }
            else{
                matrix[posA][posB] = 0;
                matrix[posB][posA] = 0;

                edgeNum--;
            }
        }
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }

    public Vertex[] getAdjacentVertices(String vertexName) {
        Vertex[] neighborArray = new Vertex[0];

        if(!exists(vertexName)){
            System.out.println("Vertex " + vertexName + " does not exist!!!");
        }
        else{
            int pos = -1;

            for(int i=0; i<vertexArray.length; i++){
                if(vertexArray[i].name.equals(vertexName)){
                    pos = i;
                }
            }

            for(int i=0; i<vertexArray.length; i++){
                if(matrix[pos][i] > 0){
                    Vertex[] tempArray = new Vertex[neighborArray.length + 1];
                    for(int j=0; j<neighborArray.length; j++){
                        tempArray[j] = neighborArray[j];
                    }
                    
                    tempArray[neighborArray.length] = vertexArray[i];
                    neighborArray = tempArray;
                }
            }
        }

        if (neighborArray.length == 0) {
            return null;
        }
        else {
            return neighborArray;
        }   
    }

    public Vertex[] getVertexArray() {
        return vertexArray;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public boolean exists(String vertexName) {
        boolean status = false;

        for(int i=0; i<vertexArray.length; i++){
            if(vertexArray[i].name.equals(vertexName)){
                status = true;
            }
        }
        return status;
    }

    public void dislay() {
        System.out.println("\nVERTICES: ");
        for(int i=0; i<vertexArray.length; i++){
            System.out.print("[" + vertexArray[i].name + ":" + vertexArray[i].weight + "] ");
        }
        
        System.out.println("\n\nMATRIX: ");
        System.out.print("Vertex");
        for(int i=0; i<vertexArray.length; i++){
            System.out.print("\t" + vertexArray[i].name);
        }
        System.out.println();
        for(int i=0; i<vertexArray.length; i++){
            System.out.print(vertexArray[i].name);
            for(int j=0; j<vertexArray.length; j++){
                System.out.print("\t" + matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("");
    }
}