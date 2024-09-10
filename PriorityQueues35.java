


import java.util.ArrayList;
import java.util.PriorityQueue;

public class PriorityQueues35 {

    static class Student implements Comparable<Student> {
        String name;
        int age;

        public Student(String n, int a) {
            name = n;
            age = a;
        }
 
        @Override
        public int compareTo(Student s2) {
            return this.age - s2.age;
        }
    }


    // This class is for nearbyCars
    static class Point implements Comparable<Point> {
        int x;
        int y;
        int distSqr;
        int idx;

        Point(int x, int y, int dis, int idx) {
            this.x = x;  // We are storing coordinates for same type other questions 
            this.y = y;  // If we need these then we can use them
            this.distSqr = dis;
            this.idx = idx;
        }

        @Override

        public int compareTo(Point p2) {
            return this.distSqr - p2.distSqr;
        }
    }

    public static void nearbyCars() {

        int[][] cars = { {3, 3}, {5, -1}, {-2, 4}};
        int k = 2;

        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int i = 0; i < cars.length; i++) {
            int distSqr = cars[i][0] * cars[i][0] + cars[i][1] * cars[i][1];
            pq.add(new Point(cars[i][0], cars[i][0], distSqr, i));
        }

        for (int i = 0; i < k; i++) {
            System.out.print("C" + pq.remove().idx + " ");
        }
    
    }

    // Print Minimum Cost of connecting N ropes
    public static void connectRopes() {
        int[] ropes = {2, 3, 3, 4, 6};
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : ropes) {
            pq.add(ropes[i]);
        }
        int minCost = 0;
        while (pq.size() > 1) {
            int curr = pq.remove() + pq.remove(); 
            minCost += curr;
            pq.add(curr);
        }
        System.out.println(minCost);
    }

    static class Row implements Comparable<Row>{
        int soldier;
        int idx;

        public Row(int soldier, int i) {
            this.soldier = soldier;
            idx = i;
        }

        @Override

        public int compareTo(Row r2) {
            if (this.soldier == r2.soldier) {
                return this.idx - r2.idx;
            } else {
                return this.soldier - r2.soldier;
            }
        }
    }
    // leetcode 1337
    public static void weakestSoldier() {
        int[][] army ={ {1, 0, 0, 0}, 
                        {1, 1, 1, 1}, 
                        {1, 0, 0, 0},
                        {1, 0, 0, 0} };
        int k = 2;
        PriorityQueue<Row> pq = new PriorityQueue<>();
        for (int i = 0; i < army.length; i++) {
            int soldier = 0;
            for (int j : army[i]) {
                soldier += j;
            }
            pq.add(new Row(soldier, i));
        }
        for (int i = 0; i < k; i++) {
            System.out.print("R" + pq.remove().idx + " ");
        }
    }

    static class Number implements Comparable<Number>{
        int data;
        int idx;
        public Number(int d, int i) {
            data = d;
            idx = i;
        }
        @Override
        public int compareTo(Number n2) {
            return n2.data - this.data;
        }
    }

    public static void slidingWindowMaximum() {
        int[] arr = { 1, 3, -1, -3, 5, 3, 6, 7};
        int n = arr.length;
        int k = 3;   // Window Size

        PriorityQueue<Number> pq = new PriorityQueue<>();
        int res[] = new int[n - k + 1];

        for (int i = 0; i < k; i++) {  //  First Windos  
            pq.add(new Number(arr[i], i));
        }

        res[0] = pq.peek().data;

        for (int i = k ; i < n; i++) {
            while (pq.size() > 0 && pq.peek().idx <= i - k) {
                pq.remove();
            }
            pq.add(new Number(arr[i], i));
            res[i - k + 1] = pq.peek().data;
        }

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    static class KthLargestInStream {
        PriorityQueue<Integer> pq;
        int k = 0;

        public KthLargestInStream(int k, ArrayList<Integer> list) {
            this.k = k;
            pq = new PriorityQueue<>(k);
            for (Integer n : list) {
                add(n);
            }
        }

        public int add(int n) {
            if (pq.size() < k) {
                pq.add(n);
            } else if (n > pq.peek()) {
                pq.poll();
                pq.add(n);
            }
            return pq.peek();
        }
    }
    //  K’th largest element in a stream
    public static void kthLargestInStream() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        KthLargestInStream k = new KthLargestInStream(3, list);
        for (int i = 0; i < 6; i++ ) {
            System.out.println(k.add(i * 3));
        }
    }

    public static void main(String[] args) {
        slidingWindowMaximum();   
    }
}
