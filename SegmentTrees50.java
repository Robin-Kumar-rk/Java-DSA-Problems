// import java.util.*;

public class SegmentTrees50 {

    
    public static void main(String[] args) {
        
    }
}

class SegmentTreesMax {
    static int[] tree;

    public static int buildMax(int[] arr, int idx, int s, int e) {
        if (s == e) {
            return tree[idx] = arr[s];
        }
        int mid = (s + e) / 2;
        int lmax = buildMax(arr, idx * 2 + 1, s, mid);
        int rmax = buildMax(arr, idx * 2 + 2, mid + 1, e);
        return tree[idx] = Math.max(lmax, rmax);
    }

    public static int maxQuery(int i, int j, int s, int e, int idx) {
        if (i <= s && e <= j)
            return tree[idx];
        if (s > j || e < i)
            return Integer.MIN_VALUE;
        int mid = (s + e) / 2;
        int lmax = maxQuery(i, j, s, mid, idx * 2 + 1);
        int rmax = maxQuery(i, j, mid + 1, e, idx * 2 + 2);
        return Math.max(lmax, rmax);
    }

    public static void maxUpdate(int nidx, int nval, int s, int e, int idx) {
        if (nidx < s || nidx > e) return;
        if (s == e) {
            tree[idx] = nval;
            return;
        }
        int mid = (s + e) / 2;
        maxUpdate(nidx, nval, s, mid, idx * 2 + 1);
        maxUpdate(nidx, nval, mid + 1, e, idx * 2 + 2);
        tree[idx] = Math.max(tree[idx * 2 + 1], tree[idx * 2 + 2]);
    }

}

class SegmentTreesSum {
    static int[] tree;

    public static int buildST(int[] arr, int[] tree, int s, int e, int idx) {
        if (s == e) {
            tree[idx] = arr[s];
            return tree[idx];
        }
        int mid = (s + e) / 2;
        int l = buildST(arr, tree, s, mid, 2 * idx + 1);
        int r = buildST(arr, tree, mid + 1, e, 2 * idx + 2);
        return tree[idx] = l + r;
    }

    public static void update(int nidx, int diff, int s, int e, int idx) {
        if (nidx < s || nidx > e)
            return;
        tree[idx] += diff;
        if (s == e) {
            return;
        }
        int mid = (s + e) / 2;
        update(nidx, diff, s, mid, idx * 2 + 1);
        update(nidx, diff, mid + 1, e, idx * 2 + 2);
    }

    public static int query(int idx, int s, int e, int i, int j) {
        if (s >= i && e <= j)
            return tree[idx];
        if (s == e)
            return 0;
        int mid = (s + e) / 2;
        int l = query(idx * 2 + 1, s, mid, i, j);
        int r = query(idx * 2 + 2, mid + 1, e, i, j);
        return l + r;
    }
}