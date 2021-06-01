package Testers;

import java.io.File;

import DataStructures.*;

public class ReaderTester {
    public static void main (String [] args) {
        System.out.println("Graph Reader Tester");
        File file = new File("TextFiles/sample.txt");
        new GraphReader(file);
    }
}
