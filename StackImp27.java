

import java.util.ArrayList;

public class StackImp27 {

    // Implementation of Stack using ArrayList
    static class StackAL {
        ArrayList<Integer> list = new ArrayList<>();

        public boolean isEmpty() {
            return list.size() == 0;
        }

        public void push(int data) {
            list.add(data);
        }

        public int pop() {
            if (isEmpty()) {
                return -1;
            }
            return list.remove(list.size() - 1);
        }

        public int peek() {
            if (isEmpty()) {
                return -1;
            }
            return list.get(list.size() - 1);
        }
    }

    // Implementation of Stack using LinkedList
    static class StackLL {
        public static class Node {
            int data;
            Node next;

            public Node(int data) {
                this.data = data;
                next = null;
            }
        }

        public Node head;

        public boolean isEmpty() {
            return head == null;
        }

        public void push(int data) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        }

        public int peek() {
            if (isEmpty())
                return -1;
            return head.data;
        }

        public int pop() {
            if (isEmpty())
                return -1;
            int top = head.data;
            head = head.next;
            return top;
        }
    }

    public static void main(String[] args) {
        StackAL sAl = new StackAL();
        sAl.push(1);
        sAl.push(2);
        sAl.push(3);
        sAl.push(4);
        sAl.push(5);
        while (!sAl.isEmpty()) {
            System.out.print(sAl.peek() + " ");
            sAl.pop();
        }
        System.out.println();
        StackLL sLL = new StackLL();
        sLL.push(1);
        sLL.push(2);
        sLL.push(3);
        sLL.push(4);
        sLL.push(5);
        while (!sLL.isEmpty()) {
            System.out.print(sLL.peek() + " ");
            sLL.pop();
        }
    }
}