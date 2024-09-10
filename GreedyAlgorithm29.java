


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
//import java.util.Collections;
//import java.util.Collections;
import java.util.Comparator;



public class GreedyAlgorithm29 {

    public static void activitySelection1() {

        int start[] = {1, 3, 0, 5, 8, 5};
        int end[] = {2, 4, 6, 7, 9, 9};
        // end time basis sorted

        ArrayList<Integer> ans = new ArrayList<>();
        // 1st activity
        int maxAct = 1;
        ans.add(0);
        int lastEnd = end[0];

        for (int i = 1; i < end.length; i++) {
            if (start[i] >= lastEnd) {
                ans.add(i);
                maxAct++;
                lastEnd = end[i];
            }
        }

        System.out.println(maxAct);
        System.out.println(ans);
    }

    public static void activitySelection2() {
        int start[] = {3, 5, 5, 1, 0, 8};
        int end[] = {4, 7, 9, 2, 6, 9};
        // if end time is not sorted

        int activities[][] = new int [start.length][3];
        for (int i = 0; i < start.length; i++) {
            activities[i][0] = i;
            activities[i][1] = start[i];
            activities[i][2] = end[i];
        }

        // lamda function for sorting -> short form 

        Arrays.sort(activities, Comparator.comparingDouble(o -> o[2]));
        // end time basis sorted

        ArrayList<Integer> ans = new ArrayList<>();

        int maxAct = 1;
        // 1st activity
        ans.add(activities[0][0]);
        int lastEnd = activities[0][2];
         
        for (int i = 1; i < start.length; i++) {
            if (activities[i][1] >= lastEnd) {
                ans.add(activities[i][0]);
                maxAct++;
                lastEnd = activities[i][2];
            }
        }
       
        System.out.println(maxAct);
        System.out.println(ans);

    }

    public static int fractionalKnapsack() {
        int value[] = {100, 60, 120};
        int weight[] = {20, 10, 30};
        int capacity = 50;

        double ratio[][] = new double[value.length][2];
        for (int i = 0; i < value.length; i++) {
            ratio[i][0] = i;
            ratio[i][1] = value[i] / (double)weight[i];
        }

        Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));

        int ans = 0;
        for (int i = ratio.length - 1; i >= 0; i--) {
            int idx = (int)ratio[i][0];
            if (capacity >= weight[idx]) {
                ans += value[idx];
                capacity -= weight[idx];
            } else {
                ans += (ratio[i][1] * capacity);
                break;
            }
        }
        return ans;
    }
 
    // Min absolute difference Pairs
    public static int absoluteDifference() {

        int A[] = {4, 1, 8, 7};
        int B[] = {2, 3, 6, 5};
        Arrays.sort(A);
        Arrays.sort(B);

        int minimumSum = 0;
        for (int i = 0; i < A.length; i++) {
            minimumSum += Math.abs(A[i] - B[i]);
        }
        return minimumSum;
    }

    public static int maxLengthChainOfPairs() {
        int pairs[][] = {{5, 24}, {39, 60}, {5, 28,}, {27, 40}, {50, 90}};
        Arrays.sort(pairs, Comparator.comparingDouble(a -> a[1]));

        int chainLen = 1;  // chainLenght
        int chainEnd = pairs[0][1];   // last selected pair's end
        for (int i = 0; i < pairs.length; i++) {
            if (pairs[i][0] > chainEnd) {
                chainLen++;
                chainEnd = pairs[i][1];
            }
        }

        return chainLen;
    }

    public static void indianCoin() {

        int coin[] = {1, 2, 5, 10, 20, 50, 100, 500, 2000};
        Arrays.sort(coin);

        // Integer[] coin = {1, 2, 5, 10, 20, 50, 100, 500, 2000};
        // Arrays.sort(coin, Collections.reverseOrder());
        // sorting in reverse order to iterate from starting

        int amount = 2688;
        ArrayList<Integer> ans = new ArrayList<>();
        
        for (int i = coin.length - 1; i >= 0; i--) {
            while (coin[i] <= amount) {
                ans.add(coin[i]);
                amount -= coin[i];
            }
        } 

        System.out.println(ans);
        System.out.println(ans.size()); // total coins OR Notes
    }

    static class Job {

        int deadline;
        int profit;
        char id;
        
        public Job(int d, int p, char id) {
            deadline = d;
            profit = p;
            this.id = id;
        }

    }

    public static void jobSequencing() {
        int jobInfo[][] = {{4, 70}, {1, 20}, {1, 30}};
        ArrayList<Job> job = new ArrayList<Job>();
        char ch = 'A';
        for (int i = 0; i < jobInfo.length; i++) {
            job.add(new Job(jobInfo[i][0], jobInfo[i][1], ch++));
        }
        Collections.sort(job, (Job a, Job b) -> 
                        (a.deadline == b.deadline ? 
                        b.profit - a.profit : a.deadline - b.deadline));
        ArrayList<Character> ans = new ArrayList<>();
        int mPro = 0;
        int n = job.size();
        int time = 0;
        for (int i = 0; i < n; i++) {
            Job j = job.get(i);
            if (j.deadline > time) {
                ans.add(j.id);
                mPro += j.profit;
                time = j.deadline;
            }
        }
        System.out.println(ans);
        System.out.println(mPro);
    }

    public static void chocolaProblem() {

        //int m = 6, n = 4;

        Integer verCost[] = {2, 1, 3, 1, 4}; // m-1
        Integer horCost[] = {4, 1, 2};  // n-1
        Arrays.sort(verCost, Comparator.reverseOrder());
        Arrays.sort(horCost, Comparator.reverseOrder());

        int v = 0, h = 0; // pointers to iterate on verCost & horCost
        int  vp = 1, hp = 1;  // peices
        int ans = 0;

        while (v < verCost.length && h < horCost.length) {
            if (verCost[v] <= horCost[h]) {   // horizontal cut
                ans += (horCost[h] * vp);
                h++; hp++;       // peices will increased acc. to our cut 
            } else {                            // vertical cut
                ans += (verCost[v] * hp);
                v++; vp++;
            }
        }

        while (v < verCost.length) {   // remaining vertical cuts
            ans += (verCost[v] * hp);
            v++; vp++;
        }

        while (h < horCost.length) {   //  remaining horizontal cuts
            ans += (horCost[h] * vp);
            h++; hp++;
        }

        System.out.println(ans);
    }

    public static int maxBalancedStringPartitions() {
        String st = "LRRRRLLRLLRL";
        int n = st.length();
        if (n == 0) {
            return 0;
        }
        int ans = 0;
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            if (st.charAt(i) == 'L') {
                l++; 
            } else {
                r++;
            }
            if (l == r) {
                ans++;
            }
        }
        return ans;
    }

    public static void kthLargestOddNumberInRange() {
        int l = 1, r = 10;
        int k = 4;
        int val;
        if ((r & 1) == 0) {
            val = r - (2 * k - 1);
        } else {
            val = r - 2 * (k - 1);
        }
        if (val >= l && val <= r) { 
            System.out.println(val);
        } else {
            System.out.println(0);
        }
    }


    // (int)'a' = 97;
    // lexicographically Smallest String
    public static String getSmallestString(int n, int k) {
        char res[] = new char[n];
        Arrays.fill(res, 'a');
        k -= n;

        while (k > 0) {
            int temp = Math.min(25, k);
            res[--n] += temp;
            k -= temp;
        }

        return String.valueOf(res);
    }

    static int ans = Integer.MAX_VALUE;

    public static void splitArray(int[] arr, int n, int k, int idx, int sum, int maxsum) {
        if (k == 1) {
            sum = 0;
            for (int i = idx; i < n; i++) {
                sum += arr[i];
            }
            maxsum = Math.max(maxsum, sum);
            ans = Math.min(maxsum, ans);
            return;
        }
        sum = 0;
        for (int i = idx; i < n - 1; i++) {
            sum += arr[i];
            maxsum = Math.max(maxsum, sum);
            splitArray(arr, n, k - 1, i + 1, sum, maxsum);
        }
    }

    static class Split {
        int[] arr; int k;
        public int splitArray(int[] arr, int k) {
            this.arr = arr; this.k = k;
            int l = 0, r = 0;
            for (int i : arr) {
                l = Math.max(l, i);
                r += i;
            }
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (valid(mid)) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }
        private boolean valid(int mid) {
            int i = 0, chunks = 0, n = arr.length;
            while (i < n) {
                int sum = 0;
                while (i < n && sum + arr[i] <= mid) {
                    sum += arr[i++];
                }
                chunks++;
            }
            return chunks <= k;
        }
    }

    // leetcode - 55 - Jump game

    public static void main(String[] args) {
        
    }
}
