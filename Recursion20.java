
public class Recursion20 {
    public static void printNumber(int n) {
        if (n < 0) {
            return;
        }
        printNumber(n - 1);
        System.out.println(n);
    }

    public static int calFac(int n) {
        if (n == 0) {
            return 1;
        }
        return calFac(n - 1) * n;
    }

    public static int calSum(int n) {
        if (n == 0) {
            return 0;
        }
        return n + calSum(n - 1);
    }

    public static int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static boolean isArrSorted(int arr[], int i) {
        if (i == arr.length - 1) {
            return true;
        }
        if (arr[i] > arr[i + 1]) {
            return false;
        }
        return isArrSorted(arr, i + 1);
    }

    public static int firstOccurence(int[] arr, int i, int key) {
        if (i == arr.length) {
            return -1;
        }
        if (arr[i] == key) {
            return i;
        }
        return firstOccurence(arr, i + 1, key);
    }

    public static int lastOccurence(int[] arr, int i, int key) { // i starts with n-1
        if (i < 0) {
            return -1;
        }
        if (arr[i] == key) {
            return i;
        }
        return lastOccurence(arr, i - 1, key);
    }

    public static int lastOccurence2(int arr[], int i, int key) { // i starts with 0
        if (i == arr.length) {
            return -1;
        }
        int found = lastOccurence2(arr, i + 1, key);
        if (arr[i] == key && found == -1) {
            return i;
        }
        return found;
    }

    public static int calApowN(int a, int n) { // A to the power n
        if (n == 0) {
            return 1;
        }
        int halfPow = calApowN(a, n / 2);
        int halfPowSquare = halfPow * halfPow;
        if ((n % 2) == 0) { // n = even
            return halfPowSquare;
        } else { // n = odd
            return a * halfPowSquare;
        }
    }

    public static int tilingProblem(int n) {
        if (n == 0 || n == 1) return 1; // if floor == 0 then we have one way
        return tilingProblem(n - 1) + tilingProblem(n - 2);
    }

    // this fuction also prints ways
    public static int tilingProblem(int n, String st) { // n = floor size
        if (n == 0) {
            System.out.println(st);
            return 1;
        }
        if (n == 1) {
            System.out.println(st + '|');
            return 1;
        }
        return tilingProblem(n - 1, st + '|') + tilingProblem(n - 2, st + '=');
    }
    
    static boolean map[] = new boolean[26];

    public static void removeDuplicates(String st, int i, StringBuilder sb) {
        if (i == st.length()) { // base case
            System.out.println(sb);
            return;
        }
        char ch = st.charAt(i);
        if (!map[ch - 'a']) { // main work
            sb.append(ch);
            map[ch - 'a'] = true;
        }
        removeDuplicates(st, i + 1, sb); // recursive call
    }

    public static int friendsPair(int n) {
        if (n == 2 || n == 1) {
            return n;
        }
        return friendsPair(n - 1) + (n - 1) * friendsPair(n - 2);
    }

    public static void printBinaryStrings(int n, int lastplace, String st) {
        if (n == 0) {
            System.out.println(st);
            return;
        }
        if (lastplace == 0) {
            printBinaryStrings(n - 1, 1, st + "1"); // placing one
        }
        printBinaryStrings(n - 1, 0, st + "0"); // placing zero
    }

    public static void printAllOccurence(int[] arr, int key, int i) {
        if (i == arr.length) { // base case
            return;
        }
        if (arr[i] == key) { // work
            System.out.print(i + " ");
        }
        printAllOccurence(arr, key, i + 1); // recursive function call
    }

    static String[] arr = { "Zero ", "One ", "Two ", "Three ", "Four ", 
                            "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten " };

    public static void convertNumToEngString(int n) {
        if (n == 0) { // base case
            return;
        }
        convertNumToEngString(n / 10); // recursive function call
        System.out.print(arr[n % 10]); // work
    }

    public static int lengthOfString(String st) {
        if (st.equals("")) {
            return 0;
        }
        return lengthOfString(st.substring(1)) + 1;
    }

    public static int numOfsubSt(String str, int i, int j) { // firstly read question of this code
        if (i == j) {
            return 1;
        }
        if (i > j) {
            return 0;
        }
        int num = numOfsubSt(str, i + 1, j) + numOfsubSt(str, i, j - 1) - numOfsubSt(str, i + 1, j - 1);
        if (str.charAt(i) == str.charAt(j)) {
            num++;
        }
        return num;
    }

    public static void towerOfHanoi(int n, String sc, String hl, String des) {
        if (n == 1) {
            System.out.println("Move " + 1 + " disk from " + sc + " to " + des);
            return;
        }
        // transfer top n-1 from src to helper using dest as 'helper'
        towerOfHanoi(n - 1, sc, des, hl);
        // transfer nth from src to dest
        System.out.println("Move " + n + " disk from " + sc + " to " + des);
        // transfer n-1 from helper to dest using src as 'helper'
        towerOfHanoi(n - 1, hl, sc, des);
    }

    public static void main(String[] input) {
        System.out.println(tilingProblem(5, ""));
    }
}