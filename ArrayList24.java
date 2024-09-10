
import java.util.*;



public class ArrayList24 {
    
    public static int containerWithMostWater(ArrayList<Integer> height) {
        int n = height.size();
        // 2 pointer approch
        int lp = 0;
        int rp = n - 1;
        int water = 0;
        while (lp < rp) {
            int currW = Math.min(height.get(lp), height.get(rp)) * (rp - lp); // calculating water
            water = Math.max(water, currW); // updatinng water
            if (height.get(lp) < height.get(rp)) { // updaing pointers
                lp++;
            } else {
                rp--;
            }
        }
        return water;
    }

    public static boolean pairSum(ArrayList<Integer> list, int target) {
        int n = list.size();
        int lp = 0; // 2 pointers
        int rp = n - 1;
        while (lp != rp) {
            int sum = list.get(lp) + list.get(rp);
            if (sum == target) { // case 1
                System.out.println(list.get(lp) + "," + list.get(rp));
                return true;
            } else if (sum < target) { // case 2
                lp++;
            } else { // case 3
                rp--;
            }
        }
        return false;
    }

    public static boolean pairSum2(ArrayList<Integer> list, int target) {
        int n = list.size();
        int i = 0;
        for (i = 0; i < n - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                break;
            }
        }
        int lp = i + 1;
        int rp = i;
        while (lp != rp) {
            int sum = list.get(lp) + list.get(rp);
            if (sum == target) {
                System.out.println(list.get(lp) + "," + list.get(rp));
                return true;
            } else if (sum < target) {
                lp = (lp + 1) % n;
            } else {
                rp = (rp - 1 + n) % n;
            }
        }
        return false;
    }

    public static boolean isMonotonic(ArrayList<Integer> list) {
        boolean incrs = false;
        boolean decrs = false;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                incrs = true;
            } else if (list.get(i) > list.get(i + 1)) {
                decrs = true;
            }
            if (incrs && decrs) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> getLonelyNumbers(ArrayList<Integer> list) {
        int n = list.size();
        Collections.sort(list);
        ArrayList<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(list.get(0));
            return ans;
        }
        if (list.get(0) + 1 < list.get(1)) {
            ans.add(list.get(0));
        }
        for (int i = 1; i < n - 1; i++) {
            int curr = list.get(i);
            int prev = list.get(i - 1);
            int next = list.get(i + 1);
            if (curr - 1 > prev && curr + 1 < next) {
                ans.add(curr);
            }
        }
        if (list.get(n - 1) - 1 > list.get(n - 2)) {
            ans.add(list.get(n - 1));
        }
        return ans;
    }

    public static int mostFrequent(ArrayList<Integer> nums, int key) {
        int[] help = new int[1000];
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) == key) {
                help[nums.get(i + 1) - 1]++;
            }
        }
        int max = Integer.MIN_VALUE;
        int ans = 0;
        for (int i = 0; i < 1000; i++) {
            if (max < help[i]) {
                max = help[i];
                ans = i + 1;
            }
        }
        return ans;
    }

    // iterative approch
    public static ArrayList<Integer> beautifulArrayList(int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(1);
        for (int i = 1; i <= n / 2 + 1; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (Integer e : ans) {
                if (e * 2 <= n)
                    temp.add(e * 2);
            }
            for (Integer e : ans) {
                if (e * 2 - 1 <= n)
                    temp.add(e * 2 - 1);
            }
            ans = temp;
        }
        return ans;
    }

    public static void main(String[] args) {
      
        
        
    }
}