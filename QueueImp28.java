
import java.util.Stack;

public class QueueImp28 {
    static class Queue { // using Array
        int[] arr;
        int front;
        int rear;
        int size;

        Queue(int n) {
            arr = new int[n];
            rear = -1;
            front = -1;
            size = n;
        }

        public boolean isEmpty() {
            return rear == -1 && front == -1;
        }

        public boolean isFull() {
            return (rear + 1) % size == front;
        }

        public void add(int data) {
            if (isFull()) {
                System.out.println("Queue is full");
                return;
            }
            if (front == -1) { // adding first element
                front = 0;
            }
            rear = (rear + 1) % size;
            arr[rear] = data;
        }

        public int remove() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return Integer.MIN_VALUE;
            }
            int data = arr[front];
            if (front == rear) { // removing last element
                front = rear = -1;
            } else {
                front = (front + 1) % size;
            }
            return data;
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return Integer.MIN_VALUE;
            }
            return arr[front];
        }
    }

    static class Queue2 { // using LinkedList
        static class Node {
            int data;
            Node next;

            Node(int data) {
                this.data = data;
                this.next = null;
            }
        }

        public Node head;
        public Node tail;

        public boolean isEmpty() {
            return head == null && tail == null;
        }

        public void add(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = tail = newNode;
                return;
            }
            tail.next = newNode;
            tail = newNode;
        }

        public int remove() {
            if (isEmpty()) {
                System.out.println("Queue is Empty.");
                return Integer.MIN_VALUE;
            }
            int result = head.data;
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }
            return result;
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty.");
                return Integer.MIN_VALUE;
            }
            return head.data;
        }
    }

    static class Queue3 {   // using two Stack
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        public boolean isEmpty() {
            return s1.isEmpty();
        }

        public void add(int data) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s1.push(data);
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        public int remove() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return Integer.MIN_VALUE;
            }
            return s1.pop();
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return Integer.MIN_VALUE;
            }
            return s1.peek();
        }
    }

    static class Queue4 {    // using two Stack
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        public boolean isEmpty() {
            return s1.isEmpty();
        }

        public void add(int data) {
            s1.push(data);
        }

        public int remove() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return Integer.MIN_VALUE;
            }
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            int result = s2.pop();
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
            return result;
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return Integer.MIN_VALUE;
            }
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            int result = s2.peek();
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
            return result;
        }
    }

    public static void main(String[] args) {

    }

}
