package Testers;

import java.util.Scanner;

import DataStructures.*;

public class GraphTester {
    private Scanner scan = new Scanner (System.in);
    private Graph graph = new Graph();

    public GraphTester(){
        int choice = 0;

        while(true){
            System.out.println("\nGRAPH TESTER");
            System.out.println("\t1. Insert Vertex");
            System.out.println("\t2. Remove Vertex");
            System.out.println("\t3. Insert Edge");
            System.out.println("\t4. Remove Edge");

            System.out.println("\nQUERIES");
            System.out.println("\t5. Display");
            System.out.println("\t6. Get Adjacent Vertices");
            System.out.println("\t7. Get Edge");
            System.out.println("\t8. Check if Adjacent");

            System.out.println("\n\t0. EXIT");

            System.out.print("\nEnter Choice: ");
            choice = scan.nextInt();

            if(choice == 1){
                System.out.print("Enter Vertex to Insert: ");
                String name = scan.next();
                System.out.print("Enter Weight of Vertex: ");
                float weight = scan.nextFloat();
                Vertex vertex = new Vertex(name, weight);
                graph.insertVertex(vertex);
            }
            if(choice == 2){
                System.out.print("Enter Vertex to Remove: ");
                graph.removeVertex(scan.next());
            }
            if(choice == 3){
                System.out.println("Enter 2 Vertices to connect: ");
                System.out.print("\tVertex 1: ");
                String vertexName1 = scan.next();
                System.out.print("\tVertex 2: ");
                String vertexName2 = scan.next();
                System.out.print("\tEnter Weight of Edge: ");
                float weight = scan.nextFloat();
                graph.insertEdge(vertexName1, vertexName2, weight);
            }
            if(choice == 4){
                System.out.println("Enter 2 Vertices to disconnect: ");
                System.out.print("\tVertex 1: ");
                String vertexName1 = scan.next();
                System.out.print("\tVertex 2: ");
                String vertexName2 = scan.next();
                graph.removeEdge(vertexName1, vertexName2);
            }
            if(choice == 5){
                Vertex[] vertexArray = graph.getVertexArray();
                float[][] matrix = graph.getMatrix();

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

            if(choice == 6) {
                System.out.print("Vertex: ");
                String vertexName = scan.next();
                String[] adjacentVertices = graph.getAdjacentVertices(vertexName);

                if (adjacentVertices == null) {
                    System.out.println("\nThe vertex " + vertexName + " is not connected to any other vertices!!!");
                }
                else {
                    System.out.println("\nADJACENT VERTICES TO VERTEX " + vertexName + ": ");
                    for(int i=0; i<adjacentVertices.length; i++){
                        System.out.print("[" + adjacentVertices[i]);
                    }
                    System.out.println();
                }
            }

            if(choice == 7) {
                System.out.print("Vertex 1: ");
                String vertexNameA = scan.next();
                System.out.print("Vertex 2: ");
                String vertexNameB = scan.next();
                float edgeWeight = graph.getEdgeWeight(vertexNameA, vertexNameB);
                System.out.println("The edge between " + vertexNameA + " and " + vertexNameB + " is " + edgeWeight);
            }

            if(choice == 8) {
                System.out.print("Vertex 1: ");
                String vertexNameA = scan.next();
                System.out.print("Vertex 2: ");
                String vertexNameB = scan.next();
                boolean adjacency = graph.isAdjacent(vertexNameA, vertexNameB);

                if (adjacency) {
                    System.out.println("The vertices " + vertexNameA + " and " + vertexNameB + " are adjacent");
                }
                else {
                    System.out.println("The vertices " + vertexNameA + " and " + vertexNameB + " are not adjacent");
                }
            }

            if(choice == 0){
                break;
            }
        }
    }

    public static void main (String [] args){
        new GraphTester();
    }
}
