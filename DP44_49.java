public class DP44_49 {
    public static int knapsack(int val[], int wt[], int w, int i) {
        if (w == 0 || i < 0) {
            return 0;
        }
        if (wt[i] <= w) {
            int val1 = val[i] + knapsack(val, wt, w - wt[i], i - 1);
            int val2 = knapsack(val, wt, w, i - 1);
            return Math.max(val1, val2);
        } else {
            return knapsack(val, wt, w, i - 1);
        }
    }
    public static void main(String[] args) {
        int val[] = {15, 14, 10, 45, 30};
        int wt[] = {2, 5, 1, 3, 4};
        int w = 7;
        System.out.println(knapsack(val, wt, w, val.length - 1));
    }
}
