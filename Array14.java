

public class Array14 { // lecture's questions
    public static int linearSearch(int nums[], int n) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == n) {
                return i;
            }
        }
        return -1;
    }

    public static int largestInArray(int nums[]) {
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (largest < nums[i]) {
                largest = nums[i];
            }
        }
        return largest;
    }

    public static int binarySearch(int nums[], int n) {
        int l = nums.length;
        int startI = 0, lastI = l - 1;
        while (startI <= lastI) {
            int mid = (startI + lastI) / 2;
            if (nums[mid] == n) {
                return mid;
            }
            if (nums[mid] < n) {
                startI = mid + 1;
            } else {
                lastI = mid - 1;
            }
        }
        return -1;
    }

    public static void reverseArr(int nums[]) {
        int startI = 0, lastI = nums.length - 1;
        while (startI < lastI) {
            int temp = nums[startI];
            nums[startI] = nums[lastI];
            nums[lastI] = temp;
            startI++;
            lastI--;
        }
    }

    public static void printPairs(int nums[]) {
        int l = nums.length;
        int totalPairs = 0;
        for (int i = 0; i < l; i++) {
            for (int j = i; j < l; j++) {
                System.out.print("(" + nums[i] + "," + nums[j] + ")");
                totalPairs++;
            }
            System.out.println();
        }
        System.out.println("Total pairs : " + totalPairs);
    }

    public static void printSubArrarys(int nums[]) {
        int l = nums.length, totalSubArrays = 0;
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                for (int k = i; k <= j; k++) {
                    System.out.print(nums[k] + ",");
                }
                System.out.print("  ");
                totalSubArrays++;
            }
            System.out.println();
        }
        System.out.print(totalSubArrays);
    }

    public static void minOrMaxSubArraySumBruteForce(int nums[]) {
        int l = nums.length, maxSum = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE;
        for (int i = 0; i < l; i++) {
            for (int j = i; j < l; j++) {
                int currentSum = 0;
                for (int k = i; k <= j; k++) {
                    currentSum += nums[k];
                }
                minSum = Math.min(minSum, currentSum);
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        System.out.println("Maximum subarry sum is : " + maxSum);
        System.out.println("Minimum subarray sum is : " + minSum);
    }

    public static void MaxSubArraySumPrefixArray(int nums[]) {
        int l = nums.length;
        int prefixArray[] = new int[l]; // containing sum of all element till ith index
        prefixArray[0] = nums[0];
        for (int i = 1; i < l; i++) {
            prefixArray[i] = prefixArray[i - 1] + nums[i];
        }
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = 0; i < l; i++) {
            for (int j = i; j < l; j++) {
                currentSum = i == 0 ? prefixArray[j] : prefixArray[j] - prefixArray[i - 1];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        System.out.println("Maximum subarry sum is : " + maxSum);
    }

    // can handle all negative values
    public static void kadanesAlgorithm1(int[] arr) { 
        // for min sum subarray, replace all max by min 
        int n = arr.length;
        int sum = arr[0], max = arr[0];
        for (int i = 1; i < n; i++) {
            sum = Math.max(sum + arr[i], arr[i]);
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
    
    public static void kadanesAlgorithm(int nums[]) {
        int l = nums.length;
        // Check if all elements are negateive or not
        boolean isNeg = true;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < l; i++) {
            maxSum = Math.max(maxSum, nums[i]);
            if (nums[i] > 0) {
                isNeg = false;
                break;
            }
        }
        if (!isNeg) {
            // all elements are not negative
            int currentSum = 0;
            for (int i = 0; i < l; i++) {
                currentSum += nums[i];
                if (currentSum < 0) {
                    currentSum = 0;
                }
                maxSum = Math.max(currentSum, maxSum);
            }
        }
        System.out.println("Maximum subarry sum is : " + maxSum);
    }

    public static void trappedRainWater(int nums[]) { // space O(n) time O(n)
        int l = nums.length;
        // creating and calculating left max boundry
        int lMb[] = new int[l];
        lMb[0] = nums[0];
        for (int i = 1; i < l; i++) {
            lMb[i] = Math.max(lMb[i - 1], nums[i]);
        }
        // creating and calculating right max boundry
        int rMb[] = new int[l];
        rMb[l - 1] = nums[l - 1];
        for (int i = (l - 2); i >= 0; i--) {
            rMb[i] = Math.max(nums[i], rMb[i + 1]);
        }

        // we can write only one loop intead of upper two loops for example

        // int leftMB[] = new int [l], rightMB[] = new int[l];
        // leftMB[0] = hieght [0];
        // rightMB[l-1] = hieght[l-1];
        // for(int i=1, j=l-2; i<l & j>-1; i++, j--){
        // leftMB[i] = Math.max(leftMB[i-1], hieght[i]);
        // rightMB[j] = Math.max(rightMB[j+1], hieght[j]);
        // }

        // loop
        int trappedWater = 0;
        for (int i = 0; i < l; i++) {
            // calculating water
            trappedWater = trappedWater + ((Math.min(lMb[i], rMb[i])) - nums[i]);
        }
        System.out.println(trappedWater);
    }

    public static void buyAndSellStock(int prices[]) {
        int l = prices.length, buyPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int i = 0; i < l; i++) {
            if (prices[i] > buyPrice) { // profit
                int profit = prices[i] - buyPrice; // today's profit
                maxProfit = Math.max(maxProfit, profit); // updating max profit
            } else { // lose so updating buyprice
                buyPrice = prices[i];
            }
        }
        System.out.println(maxProfit);
    }

    // practice / homework questions
    public static boolean checkDistinct(int nums[]) {
        boolean dis = false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    dis = true;
                }
            }
        }
        return dis;
    }

    public static void trappedRainWaterOptimized(int hieght[]) { // space O(1) time O(n)
        int l = hieght.length, left = 0, right = l - 1;
        int leftMax = hieght[left], rightMax = hieght[right];
        int water = 0;
        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, hieght[left]);
                water = water + leftMax - hieght[left];
            } else {
                right--;
                rightMax = Math.max(rightMax, hieght[right]);
                water = water + rightMax - hieght[right];
            }
        }
        System.out.println(water);
    }

    public static void main(String[] args) {
        int arr[] = {-2, -3, 4, -1, -2, 1, 5, -3};
        kadanesAlgorithm(arr);
    }
}
