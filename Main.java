import java.util.LinkedList;
import java.util.List;

public class Main {

  static int tree[];

  public static void init(int arr[]) {
    tree = new int[arr.length * 4];
    build(arr, 0, 0, arr.length - 1);
  }

  public static void build(int arr[], int i, int si, int ei) {
    if (si == ei) {
      tree[i] = arr[si];
      return;
    }
    int mid = si + (ei - si) / 2;
    build(arr, 2 * i + 1, si, mid);
    build(arr, 2 * i + 2, mid + 1, ei);
    tree[i] = tree[2 * i + 1] + tree[2 * i + 2];
  }

  public static int sumUtil(int i, int si, int sj, int qi, int qj) {
    if (qj <= si || qi >= sj) return 0;
    else if (qi <= si && qj >= sj) return tree[i];
    else {
      int mid = si + (sj - si) / 2;
      int left = sumUtil(2 * i + 1, si, mid, qi, qj);
      int right = sumUtil(2 * i + 2, mid + 1, sj, qi, qj);
      return left + right;
    }
  }

  public static int sum(int qi, int qj, int n) {
    return sumUtil(0, 0, n - 1, qi, qj);
  }

  public static void main(String[] args) {
    List<Integer> list = new LinkedList<>();
    list.add(null);
    list.add(null);
    list.add(null);
    System.out.println(list);
    
    
  }
}
