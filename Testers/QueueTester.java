package Testers;

import DataStructures.*;
import java.util.Scanner;

public class QueueTester {
    private PriorityQueue queue;

    public QueueTester() {
        queue = new PriorityQueue();
        Scanner scan = new Scanner (System.in);
        int choice = 0;

        while(true) {
            System.out.println("\nQUEUE TESTER");
            System.out.println("\t1. Enqueue");
            System.out.println("\t2. Dequeue");
            System.out.println("\t3. Check");

            System.out.println("\n\t0. EXIT");

            System.out.print("\nEnter Choice: ");
            choice = scan.nextInt();

            if (choice == 1) {
                System.out.print("Enter Input: ");
                String name = scan.next();
                System.out.print("Priority: ");
                Float priority = scan.nextFloat();

                queue.enqueue(name, priority);
                display();
            }
            if (choice == 2) {
                String vertexName = queue.dequeue();
                System.out.println("Dequeued Vertex: " + vertexName);

                display();
            }
            if (choice == 3) {
                System.out.print("Enter vertex: ");
                String vertexName = scan.next();
                System.out.println("Exists: " + queue.exists(vertexName));
            }
            if (choice == 0) {
                break;
            }
        }
    }

    public void display() {
        queue.display();
    }

    public static void main (String [] args) {
        new QueueTester();
    }
}
