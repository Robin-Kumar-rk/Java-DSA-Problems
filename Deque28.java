
import java.util.Deque;
import java.util.LinkedList;

public class Deque28 {

    static class Stack {
        Deque<Integer> dq = new LinkedList<>();
        public boolean isEmpty() {
            return dq.isEmpty();
        }
        public void push(int data) {
            dq.addLast(data);
        }
        public int pop() {
            return dq.removeLast();
        }
        public int peek() {
            return dq.getLast();
        }
    }

    static class Queue {
        Deque<Integer> dq = new LinkedList<>();
        public boolean isEmpty() {
            return dq.isEmpty();
        }
        public void add(int data) {
            dq.addLast(data);
        }
        public int remove() {
            return dq.removeFirst();
        }
        public int peek() {
            return dq.getFirst();
        }
    }
    public static void main(String[] args) {
        //Deque<Integer> dq = new LinkedList<>();
        
    }
}
