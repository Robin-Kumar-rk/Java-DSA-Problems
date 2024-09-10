

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Queue28 {

    public static Queue<Integer> createQueue(int n) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            q.add(i);
        }
        return q;
    }

    static class Stack1 {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        public boolean isEmpty() {
            return q1.isEmpty() && q2.isEmpty();
        }

        public void push(int data) {
            if (!q2.isEmpty()) {
                q1.add(data);
            } else {
                q1.add(data);
            }
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return Integer.MIN_VALUE;
            }

            int top = -1;

            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    if (q1.isEmpty()) {
                        break;
                    }
                    q2.add(top);
                }
            } else {
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    if (q2.isEmpty()) {
                        break;
                    }
                    q1.add(top);
                }
            }
            return top;
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return Integer.MIN_VALUE;
            }

            int top = -1;

            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    q2.add(top);
                }
            } else {
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    q1.add(top);
                }
            }
            return top;
        }
    }

    static class Stack2 {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        public boolean isEmpty() {
            return q1.isEmpty() && q2.isEmpty();
        }

        public void push(int data) {
            if (q1.isEmpty()) {
                q1.add(data);
                while (!q2.isEmpty()) {
                    q1.add(q2.remove());
                }
            } else {
                q2.add(data);
                while (!q1.isEmpty()) {
                    q2.add(q1.remove());
                }
            }
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return Integer.MIN_VALUE;
            }
            if (!q1.isEmpty()) {
                return q1.remove();
            } else {
                return q2.remove();
            }
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Stack is Empty");
                return Integer.MIN_VALUE;
            }
            if (!q1.isEmpty()) {
                return q1.peek();
            } else {
                return q2.peek();
            }
        }
    }

    public static void firstNonRepeatingCharacter(String st) {
        Queue<Character> q = new LinkedList<>();
        char[] frequency = new char[26];
        for (int i = 0; i < st.length(); i++) {
            char ch = st.charAt(i);
            q.add(ch);
            frequency[ch - 'a']++;
            while (!q.isEmpty() && frequency[q.peek() - 'a'] > 1) {
                q.remove();
            }
            if (q.isEmpty()) {
                System.out.print(-1 + " ");
            } else {
                System.out.print(q.peek() + " ");
            }
        }
    }

    public static void interleave2HalvesOfQueue(Queue<Integer> q) {
        int size = q.size(); // important to store
        Queue<Integer> q1 = new LinkedList<>();
        for (int i = 0; i < size / 2; i++) { // here we can't use q.size because it will change
            q1.add(q.remove());
        }
        while (!q1.isEmpty()) {
            q.add(q1.remove());
            q.add(q.remove());
        }
    }

    public static void queueReversal(Queue<Integer> q) {
        Stack<Integer> s = new Stack<>();
        while (!q.isEmpty()) {
            s.push(q.remove());
        }
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
    }

    public static void generateBinary(int n) {
        Queue<String> q = new ArrayDeque<>();
        q.add("1");
        while (n-- > 0) {
            String s1 = q.remove();
            System.out.print(s1 + " ");
            // if x is a binary number then x0 = 2 * x & x1 = 2 * x + 1
            q.add(s1 + "0");
            q.add(s1 + "1");
        }
    }

    // connect n ropes with minimum cost
    public static int minCost(int n, int[] length) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(length[i]);
        }
        int cost = 0;
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            cost += first + second;
            pq.add(first + second);
        }
        return cost;
    }

    static class Job {
        char id;
        int profit;
        int deadline;

        Job(char id, int d, int p) {
            this.id = id;
            profit = p;
            deadline = d;
        }
    }

    public static void printJobScheduling(ArrayList<Job> arr) {

        int n = arr.size();
        Collections.sort(arr, (a, b) -> {return a.deadline - b.deadline;});
        // we can sort this arr in reverse order to iterate from starting 
        // basically we want to start from max deadline

        ArrayList<Job> result = new ArrayList<>();
        PriorityQueue<Job> maxHeap = new PriorityQueue<>((a, b) -> {return b.profit - a.profit;});

        for (int i = n - 1; i >= 0; i--) {

            int slot;
            // calculate slots between two deadlines
            if (i == 0) {
                slot = arr.get(i).deadline;
            }  else {
                slot = arr.get(i).deadline - arr.get(i - 1).deadline;
            }

            // include the profit of job(as priority),deadline and job_id in maxHeap
            maxHeap.add(arr.get(i));
            while (slot > 0 && maxHeap.size() > 0) {
                // get the job with max_profit and
                // include the job in the result array
                result.add(maxHeap.remove());
                // reduce the slots
                slot--;
            }
        }

        // jobs included might be shuffled sort the result array by their deadlines
        Collections.sort(result, (a, b) -> a.deadline - b.deadline);

        for (Job job : result) {
            System.out.print(job.id + " ");    
        }
    }

    public static void jobSchedulingProblem() {

        ArrayList<Job> arr = new ArrayList<>();
        arr.add(new Job('a', 4, 100));
		arr.add(new Job('b', 4, 75));
		arr.add(new Job('c', 3, 60));
		arr.add(new Job('d', 3, 120));
		arr.add(new Job('f', 1, 20));
		arr.add(new Job('g', 1, 140));
		arr.add(new Job('h', 1, 70));

        printJobScheduling(arr);
    }

    public static void reverseFirstKelements() {

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= 10; i++) {
            q.add(i * 10);
        }
        int k = 4;

        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < k; i++) {
            s.push(q.remove());
        }
        for (int i = 0; i < k; i++) {
            q.add(s.pop());
        }
        for (int i = 0; i < q.size() - k; i++) {
            q.add(q.remove());
        }

        System.out.println(q);
    }

    // Print Maximum of all subarrays of an array. Size of subarrays is k.
    public static void printMax() {  
        int arr[] = { 12, 1, 78, 90, 57, 89, 56 };
        int n = arr.length;
        int k = 3;
        
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()])
                dq.removeLast();
            dq.addLast(i);
        }

        for (int i = k; i < n; i++) {
            System.out.print(arr[dq.peek()] + " ");
            while (!dq.isEmpty() && dq.peek() <= i - k)
                dq.removeFirst();
            while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()])
                dq.removeLast();
            dq.addLast(i);
        }
        

        System.out.println(arr[dq.peek()]);
    }



    public static void main(String[] args) {
       
    }
}
