import java.util.Arrays;
public class Sorting15 {
    
    public static void bubbleSort(int arr[]) {
        int l = arr.length;
        for (int i = 0; i < l - 1; i++) { // loop for count n-1 turns
            int swap = 0; // we count swap for a special case that if arr is alredy sorted
            for (int j = 0; j < l - 1 - i; j++) { // loop for swapping
                if (arr[j + 1] < arr[j]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swap++;
                }
            }
            if (swap == 0) { // if arr is sorted we will get out of loop so we can skip extra iterations
                break;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int arr[]) {
        int l = arr.length;
        for (int i = 0; i < l - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < l; j++) {
                if (arr[minIndex] > arr[j]) { // if we olny opposite enequality then we'll get arr sorted in decending order
                    minIndex = j; // we will find Index of minimum element
                }
            }
            // swap
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int arr[]) {
        int l = arr.length;
        for (int i = 1; i < l; i++) {
            int temp = arr[i];
            int prev = i - 1;
            // finding out corret position to place temp
            while ((prev >= 0) && (temp < arr[prev])) { // if we change enequality then we'll get arr in decening order
                arr[prev + 1] = arr[prev];
                prev--;
            }
            // insertion
            arr[prev + 1] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void countingSort(int arr[]) {
        int l = arr.length;
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < l; i++) { // finding largest for range
            largest = Math.max(largest, arr[i]);
        }
        int count[] = new int[largest + 1]; // we also want index=largest so size=largest+1
        for (int i = 0; i < l; i++) { // counting frequency of each element
            count[arr[i]]++;
        }
        // sorting
        int j = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[j] = i;
                j++;
                count[i]--;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
    // inbuilt sort (ascending order)
    // import java.util.Arrays;
    // Arrays.sort(arr); tc O(nlogn)
    // Arrays.sort(arr,si,ei);

    // for decending order
    // import java.util.Collections;
    // Arrays.sort(arr,Collections.reverseOrder());
    // Arrays.sort(arr,si,ei,Collections.reverseOrder());
    // but reverseOrder() function works only on objects

    public static void main(String[] args) {
        int marks[] = { 8, 9, 7, 6, 4, 2, 5, 4, 1, 2, 3, 0, 7 };
        // selectionSort(marks);
        bubbleSort(marks);
        //countingSort(marks);
    }
}