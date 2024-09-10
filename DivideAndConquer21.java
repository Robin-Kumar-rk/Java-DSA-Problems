
public class DivideAndConquer21 {
  public static void printarr(String[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
  }

  public static void mergeSort(int[] arr, int si, int ei) {
    if (si >= ei) {
      return;
    }
    int mid = si + (ei - si) / 2;
    mergeSort(arr, si, mid); // sortinng left part
    mergeSort(arr, mid + 1, ei); // sorting right part
    merge(arr, si, mid, ei); // merging left and right
  }

  public static void merge(int arr[], int si, int mid, int ei) {
    int[] temp = new int[ei - si + 1]; // creating temp arr. Be careful for its size.
    int i = si; // iterator for left part
    int j = mid + 1; // iterator for right part
    int k = 0; // iterator for temp
    while (i <= mid && j <= ei) { // merging
      if (arr[i] < arr[j]) {
        temp[k] = arr[i++];
      } else {
        temp[k] = arr[j++];
      }
      k++;
    }
    while (i <= mid) { // merging remaining left part
      temp[k++] = arr[i++];
    }
    while (j <= ei) { // merging remaining right part
      temp[k++] = arr[j++];
    }
    for (k = 0, i = si; k < temp.length; k++, i++) { // coping temp in oringnal
      arr[i] = temp[k];
    }
  }

  public static void quickSort(int[] arr, int si, int ei) {
    if (si >= ei) { // base case
      return;
    }
    int pivotIndex = partition(arr, si, ei);
    quickSort(arr, si, pivotIndex - 1); // sortinng left part
    quickSort(arr, pivotIndex + 1, ei); // sorting right part
  }

  public static int partition(int[] arr, int si, int ei) {
    int pivot = arr[ei];
    int i = si - 1;
    for (int j = si; j < ei; j++) {
      if (arr[j] <= pivot) {
        i++;
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
      }
    }
    i++;
    int temp = arr[ei];
    arr[ei] = arr[i];
    arr[i] = temp;
    return i;
  }

  public static int searchInRotatedSortedArr(int[] arr, int si, int ei, int key) {
    if (si > ei) { // base case
      return -1;
    }
    int mid = si + (ei - si) / 2;
    // case FOUND
    if (arr[mid] == key) {
      return mid;
    }

    // case 1 : arr[mid] on line 1
    if (arr[si] <= arr[mid]) {

      // case a : target is in left of arr[mid]
      if (arr[si] <= key && key <= arr[mid]) {
        return searchInRotatedSortedArr(arr, key, si, mid - 1);
      }

      // case a : target is in right of arr[mid]
      else {
        return searchInRotatedSortedArr(arr, key, mid + 1, ei);
      }

    }

    // case 2 : arr[mid] on line 2
    else {

      // case c : target is in right of arr[mid]
      if (arr[ei] >= key && key >= arr[mid]) {
        return searchInRotatedSortedArr(arr, key, mid + 1, ei);
      }

      // case d : target is in left of arr[mid]
      else {
        return searchInRotatedSortedArr(arr, key, si, mid - 1);
      }

    }
  }

  public static int searchInRotatedSortedArrByLoop(int arr[], int key) {
    int si = 0;
    int ei = arr.length - 1;

    while (si <= ei) {
      int mid = si + (ei - si) / 2;
      if (arr[mid] == key) {
        return mid;
      }
      if (arr[si] <= arr[mid]) {
        if (arr[si] <= key && key <= arr[mid]) {
          ei = mid - 1;
          continue;
        } else {
          si = mid + 1;
          continue;
        }
      } else {
        if (arr[ei] >= key && key >= arr[mid]) {
          si = mid + 1;
          continue;
        } else {
          ei = mid - 1;
          continue;
        }
      }
    }
    return -1;
  }

  public static void mergeSortForString(StringBuilder st, int si, int ei) {
    if (si >= ei) {
      return;
    }
    int mid = si + (ei - si) / 2;
    mergeSortForString(st, si, mid);
    mergeSortForString(st, mid + 1, ei);
    mergeString(st, si, mid, ei);
  }

  public static void mergeString(StringBuilder st, int si, int mid, int ei) {
    StringBuilder temp = new StringBuilder();
    int i = si;
    int j = mid + 1;
    int k;
    while (i <= mid && j <= ei) {
      if (st.charAt(i) < st.charAt(j)) {
        temp.append(st.charAt(i++));
      } else {
        temp.append(st.charAt(j++));
      }
    }
    while (i <= mid) {
      temp.append(st.charAt(i++));
    }
    while (j <= ei) {
      temp.append(st.charAt(j++));
    }
    for (i = si, k = 0; k < temp.length(); i++, k++) {
      st.setCharAt(i, temp.charAt(k));
    }
  }

  public static void mergeSortForStringArr(String[] arr, int si, int ei) {
    if (si >= ei) {
      return;
    }
    int mid = si + (ei - si) / 2;
    mergeSortForStringArr(arr, si, mid);
    mergeSortForStringArr(arr, mid + 1, ei);
    mergeStringarr(arr, si, mid, ei);
  }

  public static void mergeStringarr(String[] arr, int si, int mid, int ei) {
    int i = si;
    int j = mid + 1;
    int k = 0;
    String[] temp = new String[ei - si + 1];
    while (i <= mid && j <= ei) {
      if (arr[i].compareTo(arr[j]) < 0) {
        temp[k++] = arr[i++];
      } else {
        temp[k++] = arr[j++];
      }
    }
    while (i <= mid) {
      temp[k++] = arr[i++];
    }
    while (j <= ei) {
      temp[k++] = arr[j++];
    }
    for (i = si, k = 0; k < temp.length; i++, k++) {
      arr[i] = temp[k];
    }
  }

  // Function to find the majority element using recursion
  public static int findMajorityUtil(int[] arr, int low, int high) {

    // Base case: single element array
    if (low == high) {
      return arr[low];
    }
  
    // Divide the array into left and right halves
    int mid = (low + high) / 2;
    int leftMajority = findMajorityUtil(arr, low, mid);
    int rightMajority = findMajorityUtil(arr, mid + 1, high);

    // If left and right halves have the same majority element
    if (leftMajority == rightMajority) {
      return leftMajority;
    }
      
    // Count the occurrences of the majority element in entire array
    int leftCount = countOccurrences(arr, low, high, leftMajority);
    int rightCount = countOccurrences(arr, low, high, rightMajority);

    // Return the element that occurs more than n/2 times
    if (leftCount > (high - low + 1) / 2) {
      return leftMajority;
    }
    if (rightCount > (high - low + 1) / 2) {
      return rightMajority;
    }
      
    // No majority element
    return -1;
  }

  // Function to count the occurrences
  public static int countOccurrences(int[] arr, int low, int high, int num) {
    int count = 0;
    for (int i = low; i <= high; i++) {
      if (arr[i] == num) {
        count++;
      }
    }
    return count;
  }

  // Function to find the majority element
  public static void findMajority(int[] arr, int n) {
    int majority = findMajorityUtil(arr, 0, n - 1);
    if (majority != -1) {
      System.out.println(majority);
    } else {
      System.out.println("No Majority Element");
    }
      
  }

  public static int countInversion(int arr[], int si, int ei) {
    if (si >= ei) {
      return 0;
    }
    int mid = (ei - si) / 2 + si;
    int inver = 0;
    inver += countInversion(arr, si, mid);
    inver += countInversion(arr, mid + 1, ei);
    inver += mergeForInversion(arr, si, mid, ei);
    return inver;
  }

  public static int mergeForInversion(int arr[], int si, int mid, int ei) {
    int i = si, j = mid + 1, k = 0;
    int inver = 0;
    int temp[] = new int[ei - si + 1];
    while (i <= mid && j <= ei) {
      if (arr[i] <= arr[j]) {
        temp[k++] = arr[i++];
      } else {
        inver += mid - i + 1;
        temp[k++] = arr[j++];
      }
    }
    while (i <= mid) {
      temp[k++] = arr[i++];
    }
    while (j <= ei) {
      temp[k++] = arr[j++];
    }
    for (i = si, k = 0; k < temp.length; k++, i++) {
      arr[i] = temp[k];
    }
    return inver;
  }

  public static void printInversion(int arr[]) {
    System.out.println(countInversion(arr, 0, arr.length - 1));
  }

  public static void main(String args[]) {

  }
}
