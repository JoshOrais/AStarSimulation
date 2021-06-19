package Testers;

import DataStructures.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SolverTester {
    public static void main (String [] args) {
        Scanner scan = new Scanner (System.in);
        GraphReader reader = new GraphReader(new File("TextFiles/test.txt"));
        Graph graph = reader.getGraph();

        System.out.print("Start: ");
        String start = scan.next();
        System.out.print("Destination: ");
        String dest = scan.next();

        AStarSolver solver = new AStarSolver(graph, start, dest);
        // solver.setHCost();
        // solver.start();

        while (!solver.done) {
            solver.step();
        }

        ArrayList<String> path = solver.getPath();
        float pathWeight = solver.getPathWeight();

        System.out.println("Path weight: " + pathWeight);
        System.out.print("Path: ");

        for(String str : path) {
            System.out.print(str + " ");
        }
    }
}
