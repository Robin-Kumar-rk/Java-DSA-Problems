
import java.util.*;

public class Hashing36 {

    public static void majorityElement(int[] nums) { // O(N)
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i : nums) {
            hm.put(i, hm.getOrDefault(i, 0) + 1);
        }
        for (Integer k : hm.keySet()) {
            if (hm.get(k) > nums.length / 3) {
                System.out.print(k + " ");
            }
        }
        
    }

    // we want majority element's frequency is > n / 2.Then this code is best
    public int majorityElement2(int[] num) {

        int major = num[0], count = 1;
        for (int i = 1; i < num.length; i++) {
            if (count == 0) {
                count++;
                major = num[i];
            } else if (major == num[i]) {
                count++;
            } else {
                count--;
            }
        }
        return major;
    }

    // Anagrams : race, care & knee, keen
    public static boolean validAnagram(String s, String t) {
        if (s.length() != t.length()) { // if lenghts are not equal, they can not anagrams
            return false;
        }
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            Character ch = t.charAt(i);
            if (hm.containsKey(ch)) {
                if (hm.get(ch) == 1) {
                    hm.remove(ch);
                } else {
                    hm.put(ch, hm.get(ch) - 1);
                }
            } else {
                return false;
            }
        }
        return hm.isEmpty();
    }

    public static int distinctElements(int[] nums) { // O(n)
        HashSet<Integer> hs = new HashSet<>();
        for (Integer i : nums) {
            hs.add(i);
        }
        return hs.size();
    }

    public static void unionAndIntersection(int[] arr1, int[] arr2) {
        HashSet<Integer> hs = new HashSet<>();

        for (Integer i : arr1) {
            hs.add(i);
        }

        for (Integer i : arr2) {
            hs.add(i);
        }

        for (Integer i : hs) { // printing union
            System.out.print(i + " ");
        }
        System.out.println("Union = " + hs.size());

        hs.clear();
        int count = 0;
        for (Integer i : arr1) {
            hs.add(i);
        }

        for (Integer i : arr2) { // Findin & printing Intersection
            if (hs.contains(i)) {
                count++;
                hs.remove(i);
                System.out.print(i + " ");
            } else {
                hs.add(i);
            }
        }
        System.out.println("Intersection = " + count);
    }

    public static void findItinerary() { // O(n)

        HashMap<String, String> tickets = new HashMap<>();
        tickets.put("Chennai", "Bengluru");
        tickets.put("Mumbai", "Delhi");
        tickets.put("Goa", "Chennai");
        tickets.put("Delhi", "Goa");

        // HashMap<String, String> revTickets = new HashMap<>(); // reverse tickets
        // for (String key : tickets.keySet()) {
        // revTickets.put(tickets.get(key), key);
        // }

        String start = null;

        for (String key : tickets.keySet()) {
            if (!tickets.containsValue(key)) {
                start = key;
            }
        }

        System.out.print(start);

        for (int i = 0; i < tickets.size(); i++) {
            System.out.print(" -> " + tickets.get(start));
            start = tickets.get(start);
        }
    }

    // Largest SubArray length with sum 0
    public static int largrstSubArray(int[] arr) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int sum = 0, length = 0;
        for (int j = 0; j < arr.length; j++) {
            sum += arr[j];
            if (hm.containsKey(sum)) {
                length = Math.max(j - hm.get(sum), length);
            } else {
                hm.put(sum, j);
            }
        }
        return length;
    }

    // Count Subarray Sum equal to K
    public static int countSubarray(int[] arr, int k) {

        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0, 1); // important for that case whole subarray sum = k
        int sum = 0, ans = 0;

        for (int j = 0; j < arr.length; j++) {
            sum += arr[j];
            if (hm.containsKey(sum - k)) {
                ans += hm.get(sum - k);
            }
            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }

    static class Node {
        int data, hd;
        Node left, right;
        public Node(int data, int hd) {
            this.data = data;
            this.hd = hd;
        }
    }

    static int idx = -1;
    public static Node buildBT(int arr[], int hd) {
        idx++;
        if (arr[idx] == -1) {
            return null;
        }
        Node root = new Node(arr[idx], hd);
        root.left = buildBT(arr, hd - 1);
        root.right = buildBT(arr, hd + 1);
        return root;
    }

    public static void bottomView(Node root, int curr, HashMap<Integer, int[]> map) {
        if (root == null) {
            return;
        }
        if (!map.containsKey(root.hd)) {
            map.put(root.hd, new int[] {root.data, curr});
        } else if (root.left == null && root.right == null) {
            int[] p = map.get(root.hd);
            if (p[1] <= curr) {
                p[1] = curr;
                p[0] = root.data;
                map.put(root.hd, p);
            }
        }
        bottomView(root.left, curr + 1, map);
        bottomView(root.right, curr - 1, map);
    }

    public static void printBottomView() {
        int arr[] = { 20, 8, 5, -1, -1, 3, -1, -1, 22, 4, 10, -1,
                      -1, 14, -1, -1, 25, -1, -1 };
        Node root = buildBT(arr, 0);
        HashMap<Integer, int[]> map = new HashMap<>();
        bottomView(root, 0, map);
        for (int[] val : map.values()) {
            System.out.print(val[0] + " ");
        }
    }

    // return indices of those element whose sum is equal to target
    public static void twoSum(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int x = arr[i]; int diff = k - x;
            if (map.containsKey(diff) && diff != x) {
                System.out.print("(" + map.get(diff) + ", " + i + ")");
                return;
            } else {
                map.put(x, i);
            }
        }
    }

    public static String sortByFrequency(String str) {
        int n = str.length();
        if (n == 0) {
            return null;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
            (a, b) -> a.getValue() == b.getValue() ? a.getKey() - b.getKey() :
            b.getValue() - a.getValue());

        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            pq.add(e);
        }
         
        StringBuilder sb = new StringBuilder("");

        while (!pq.isEmpty()) {
            char ch = pq.poll().getKey();
            int val = map.get(ch);
            while (val != 0) {
                sb.append(ch);
                val--;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
       
        
    }
}
