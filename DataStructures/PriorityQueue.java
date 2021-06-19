package DataStructures;

public class PriorityQueue {
    public Node head = null;
    public int size = 0;

    public void enqueue(String vertexName, float priority) {
        Node newNode = new Node(vertexName, priority);
        boolean pushable = true;

        if (exists(vertexName)) {
            pushable = checkExisting(vertexName, priority);
        }
        
        if (head == null) {
            head = newNode;
            size++;
        }

        else {
            if (pushable == true) {
                if (newNode.priority < head.priority) {
                    newNode.next = head;
                    head = newNode;
                }
                else {
                    Node current = head;
                    while (current.next != null && current.next.priority <= newNode.priority) {
                        current = current.next;
                    }
                    newNode.next = current.next;
                    current.next = newNode;
                }
                size++;
            }    
        }
    }

    public String dequeue() {
        if (isEmpty()) {
            return null;
        }
        else {
            Node dequeued = head;
            head = head.next;
            size--;

            return dequeued.vertexName;
        }  
    }

    public boolean checkExisting(String vertexName, float priority) {
        Node current = head;

        if (current.vertexName.equals(vertexName)) {
            if (current.priority > priority) {
                head = current.next;
                size--;
                return true;
            }
            else {
                return false;
            }
        }
        else {
            while(!current.next.vertexName.equals(vertexName)){
                current = current.next;
            }

            if (current.next.priority > priority) {
                current.next = current.next.next;
                size--;
                return true;
            }
            else {
                return false;
            }
        }  
    }

    public String peek() {
        return head.vertexName;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) { return true; }
        else { return false; }
    }

    public boolean exists(String vertexName) {
        boolean status = false;
        Node current = head;

        while (current != null) {
            if (current.vertexName.equals(vertexName)) {
                status = true;
                break;
            }
            current = current.next;
        }

        return status;
    }

    public String[] getVertices() {
        String[] vertices = new String[size];

        Node current = head;
        
        for (int i=0; i<size; i++) {
            vertices[i] = current.vertexName;
            current = current.next;
        }

        return vertices;
    }

    public void display() {
        Node current = head;

        System.out.println("\nSize: " + size);
        System.out.print("Queue: ");

        while (current != null) {
            System.out.print(current.vertexName+ ":" + current.priority + " ");
            current = current.next;
        }
        System.out.println();
    }

    public class Node {
        String vertexName;
        Node next;
        float priority;

        public Node(String v, float p) {
            vertexName = v;
            priority = p;
        }
    }
}
