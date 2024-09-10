
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Heaps35 {

    static class Heap { // MinHeap

        ArrayList<Integer> arr = new ArrayList<>();

        public void add(int data) {
            // add at last Index
            arr.add(data);

            int x = arr.size() - 1; // x is child index
            int par = (x - 1) / 2; // parent index

            // reverse the inequality for maxheap
            while (arr.get(x) < arr.get(par)) { // O(log n)
                // Swapping
                int temp = arr.get(x);
                arr.set(x, arr.get(par));
                arr.set(par, temp);

                x = par;
                par = (x - 1) / 2;
            }

        }

        public int peek() {
            return arr.get(0);
        }

        // Heapify is a private fuction. It is used only in this class
        private void heapify(int i) {

            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int min = i; // we firsly assume that root is minimum

            // reverse the inequality for maxheap
            if (left < arr.size() && arr.get(left) < arr.get(min)) {
                min = left;
            }

            // reverse the inequality for maxheap
            if (right < arr.size() && arr.get(right) < arr.get(min)) {
                min = right;
            }

            if (min != i) { // check if our heap is already fix
                // swap
                int temp = arr.get(i);
                arr.set(i, arr.get(min));
                arr.set(min, temp);

                heapify(min);
            }
        }

        public int remove() {
            int data = arr.get(0); // Store data that will remove

            // Swap with last
            int temp = arr.get(0);
            arr.set(0, arr.get(arr.size() - 1));
            arr.set(arr.size() - 1, temp);

            // delete last
            arr.remove(arr.size() - 1);

            // heapify
            heapify(0);

            return data;
        }

        public boolean isEmpty() {
            return arr.size() == 0;
        }
    }

    public static void heapify1(int[] arr, int i, int size) { // Maxheap TC = O(log n)
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i; // we firsly assume that root is maximum

        if (left < size && arr[left] > arr[max]) {
            max = left;
        }

        if (right < size && arr[right] > arr[max]) {
            max = right;
        }

        if (max != i) { // check if our heap is already fix
            // Swap
            int temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;

            heapify1(arr, max, size);
        }
    }

    public static void heapSort1(int[] arr) { // O(n * log n)
        // Step 1 -> Build MaxHeap
        int n = arr.length;
        for (int i = n / 2; i >= 0; i--) {
            heapify1(arr, i, n);
        }

        // Step 2 -> Swap (First(largest) with the last)
        for (int i = n - 1; i > 0; i--) {
            // Swap
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify1(arr, 0, i);
        }

    }

    public static void heapify2(int[] arr, int i, int size) { // MinHeap
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int min = i;

        if (left < size && arr[left] < arr[min]) {
            min = left;
        }

        if (right < size && arr[right] < arr[min]) {
            min = right;
        }

        if (min != i) {
            // Swap
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;

            heapify2(arr, min, size);
        }
    }

    public static void heapSort2(int[] arr) {
        // Step 1 -> Build MinHeap
        int n = arr.length;
        for (int i = n / 2; i >= 0; i--) {
            heapify2(arr, i, n);
        }

        // Step 2 -> Swap (First(smallest) with the last)
        for (int i = n - 1; i > 0; i--) {
            // Swap
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify2(arr, 0, i);
        }
    }

    // Minimum time required to fill given N slots
    public static void minTime(int[] arr, int N, int k) { // O(N)

        Queue<Integer> q = new LinkedList<>();
        int time = 0;
        boolean[] vis = new boolean[N + 1];

        for (int i : arr) {
            q.add(i);
            vis[i] = true;
        }

        while (q.size() > 0) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                int curr = q.poll();
                if (curr - 1 >= 1 && !vis[curr - 1]) {
                    q.add(curr - 1);
                    vis[curr - 1] = true;
                }
                if (curr + 1 <= N && !vis[curr + 1]) {
                    q.add(curr + 1);
                    vis[curr + 1] = true;
                }
            }
            time++;
        }
        System.out.println(--time);
    }

    public static void minTime2(int[] arr, int N, int k) { // O(klogk) // sorting

        // Sort the array
        Arrays.sort(arr);
        int maxDist = 0, maxCorner = 0, time = 0;

        // Find maximum distance between adjacent slots
        for (int i = 1; i < k; i++) {
            maxDist = Math.max(maxDist, arr[i] - arr[i - 1]);
        }

        // Make half of maxDist becase these slots will be filled
        // by two sides(two adjacent slot)
        maxDist /= 2;

        // Check the distance from the first and last slot to the closest filled slot
        maxCorner = Math.max(arr[0] - 1, N - arr[k - 1]);

        time = Math.max(maxDist, maxCorner);

        System.out.println(time);
    }

    // Minimum Operations to Halve Array Sum
    public static int minOperations(int arr[]) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int count = 0;
        int sum = 0;

        for (int i : arr) {
            pq.add(-i);
            sum += i;
        }

        double temp = sum;
        while (temp > sum / 2) {
            int x = -pq.poll();
            temp -= Math.ceil(x * 1.0 / 2);
            count++;
        }

        return count;
    }

    static class Node {
        int data;
        Node next;

        public Node(int d) {
            data = d;
        }
    }

    static class CompareNode implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            if (n1.data < n2.data) {
                return -1;
            } else if (n1.data > n2.data) {
                return 1;
            } else {
                return 0;
            }   
        }
    }

    public static Node mergeLinkedList(Node[] arr) {

        PriorityQueue<Node> pq = new PriorityQueue<>(new CompareNode());

        for (Node n : arr) {
            while (n != null) {
                pq.add(n);
                n = n.next;
            }
        }

        Node temp = pq.poll();
        Node head = temp;

        while (!pq.isEmpty()) {
            temp.next = pq.poll();
            temp = temp.next;
        }

        return head;
    }

    public static void main(String[] args) {
        int N = 3;
        Node[] a = new Node[N];
        Node head1 = new Node(1);
        a[0] = head1;
        head1.next = new Node(3);
        head1.next.next = new Node(5);
        head1.next.next.next = new Node(7);

        Node head2 = new Node(2);
        a[1] = head2;
        head2.next = new Node(4);
        head2.next.next = new Node(6);
        head2.next.next.next = new Node(8);
        Node head3 = new Node(0);
        a[2] = head3;
        head3.next = new Node(9);
        head3.next.next = new Node(10);
        head3.next.next.next = new Node(11);
        Node res = mergeLinkedList(a);

        while (res != null) {
            System.out.print(res.data + " ");
            res = res.next;
        }
    }
}
