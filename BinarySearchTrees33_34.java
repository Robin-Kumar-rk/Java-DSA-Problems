 

import java.util.ArrayList;
import java.util.Stack;

public class BinarySearchTrees33_34 {
    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int d) {
            data = d;
            left = null;
            right = null;
        }
    }

    static int idx = -1;

    public static Node buildTree(int nodes[]) {
        idx++;
        if (nodes[idx] == -1) {
            return null;
        }
        Node newNode = new Node(nodes[idx]);
        newNode.left = buildTree(nodes);
        newNode.right = buildTree(nodes);
        return newNode;
    }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static Node buildBST(int[] values) {
        Node root = null;
        for (int i = 0; i < values.length; i++) {
            root = insert(root, values[i]);
        }
        return root;
    }

    public static Node insert(Node root, int n) {
        if (root == null) {
            return new Node(n);
        }
        if (n > root.data) {
            root.right = insert(root.right, n);
        } else {
            root.left = insert(root.left, n);
        }
        return root;
    }

    public static boolean search(Node root, int key) { // O(H)
        if (root == null) {
            return false;
        }
        if (root.data == key) {
            return true;
        }
        if (root.data > key) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    public static Node delete(Node root, int n) {

        if (root.data < n) {
            root.right = delete(root.right, n);

        } else if (root.data > n) {
            root.left = delete(root.left, n);

        } else {

            // case 1 : leaf Node
            if (root.left == null && root.right == null) {
                return null;
            }

            // case 2 : one child
            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            // case 3 : both children
            Node IS = findInorderSuccessor(root.right); // IS = Inorder Successor
            root.data = IS.data;
            root.right = delete(root.right, IS.data);

        }
        return root;
    }

    public static Node findInorderSuccessor(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static void printInRange(Node root, int k1, int k2) {
        if (root == null) {
            return;
        }

        /*
         * Since the desired o/p is sorted, recurse for left subtree first
         * If root->data is greater than k1, then only we can get o/p keys
         * in left subtree
         */
        if (k1 < root.data) {
            printInRange(root.left, k1, k2);
        }

        /* if root's data lies in range, then prints root's data */
        if (k1 <= root.data && root.data <= k2) {
            System.out.print(root.data + " ");
        }

        /* recursively call the right subtree */
        if (root.data < k2) {
            printInRange(root.right, k1, k2);
        }
    }

    public static void path(Node root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.data);
        if (root.left == null && root.right == null) {
            printList(list);
        }
        path(root.left, list);
        path(root.right, list);
        list.remove(list.size() - 1);
    }

    public static void printList(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " -> ");
        }
        System.err.println("null");
    }

    public static boolean isValidBST(Node root, Node min, Node max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.data <= min.data) {
            return false;
        }
        if (max != null && root.data >= max.data) {
            return false;
        }
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }

    public static Node mirror(Node root) {
        if (root == null) {
            return null;
        }

        // finding left & right mirror
        Node leftMirror = mirror(root.left);
        Node rightMirror = mirror(root.right);

        // swapping
        root.left = rightMirror;
        root.right = leftMirror;

        return root;
    }

    // Sorted Array To Balanced BST
    public static Node createBST(int arr[], int si, int ei) {
        if (si > ei) {
            return null;
        }
        int mid = si + (ei - si) / 2;
        Node root = new Node(arr[mid]);
        root.left = createBST(arr, si, mid - 1);
        root.right = createBST(arr, mid + 1, ei);
        return root;
    }

    // Find Inorder In Form Of ArrayList
    public static void getInorder(Node root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        getInorder(root.left, list);
        list.add(root.data);
        getInorder(root.right, list);
    }

    // Sorted ArrayList To Balanced BST
    public static Node createBST(ArrayList<Integer> list, int si, int ei) {
        if (si > ei) {
            return null;
        }
        int mid = si + (ei - si) / 2; // Or mid = si + (ei - si + 1) / 2; for choosing another root
        Node root = new Node(list.get(mid));
        root.left = createBST(list, si, mid - 1);
        root.right = createBST(list, mid + 1, ei);
        return root;
    }

    // Convert BST To Balanced BST
    public static Node balancedBST(Node root) { // TC = O(n)
        if (root == null) {
            return null;
        }

        // Inorder sequence -> O(n)
        ArrayList<Integer> list = new ArrayList<>();
        getInorder(root, list);

        // Sorted Inorder -> Balanced BST -> O(n)
        root = createBST(list, 0, list.size() - 1);
        return root;
    }

    static class Info {

        boolean isValid;
        int size;
        int min;
        int max;

        public Info(boolean isValid, int size, int min, int max) {
            this.isValid = isValid;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    static int maxSize = 0;

    public static Info largestBSTsize(Node root) {

        if (root == null) {
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Info left = largestBSTsize(root.left);
        Info right = largestBSTsize(root.right);

        int size = left.size + right.size + 1;
        int min = Math.min(root.data, Math.min(left.min, right.min));
        int max = Math.max(root.data, Math.max(left.max, right.max));

        if (root.data <= left.max || root.data >= right.min) {
            return new Info(false, size, min, max);
        }

        if (left.isValid && right.isValid) {
            maxSize = Math.max(maxSize, size);
            return new Info(true, size, min, max);
        }

        return new Info(false, size, min, max);
    }

    public static ArrayList<Integer> mergeArrayList(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> list3 = new ArrayList<>();
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            int a = list1.get(i), b = list2.get(j);
            if (a <= b) {
                list3.add(a);
                i++;
            } else {
                list3.add(b);
                j++;
            }
        }

        while (i < list1.size()) {
            list3.add(list1.get(i++));
        }

        while (j < list2.size()) {
            list3.add(list2.get(j++));
        }

        return list3;
    }

    public static Node mergeBSTs(Node root1, Node root2) {

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        getInorder(root1, list1);
        getInorder(root2, list2);

        ArrayList<Integer> list3 = new ArrayList<>(list2);
        list3 = mergeArrayList(list1, list2);

        return createBST(list3, 0, list3.size() - 1);
    }

    public static int rangeSumBST(Node root, int l, int r) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        if (l <= root.data && root.data <= r) {
            sum += root.data;
        }
        if (l < root.data) {
            sum += rangeSumBST(root.left, l, r);
        }
        if (root.data < r) {
            sum += rangeSumBST(root.right, l, r);
        }
        return sum;
    }

    // Find the closest element in Binary Search Tree
    static int minDiff = Integer.MAX_VALUE, closest = -1;

    public static void closestElement(Node root, int k) {
        if (root == null) {
            return;
        }
        if (root.data == k) {
            minDiff = 0;
            closest = k;
            return;
        }
        int diff = (int) Math.abs(root.data - k);
        if (diff < minDiff) {
            closest = root.data;
            minDiff = diff;
        }
        if (root.data < k) {
            closestElement(root.right, k);
        } else {
            closestElement(root.left, k);
        }
    }

    static int count = 0;

    public static Node kthSmallestElement(Node root, int k) {
        if (root == null || k == 0) {
            return null;
        }
        Node left = kthSmallestElement(root.left, k);
        if (left != null) {
            return left;
        }
        count++;
        if (count == k) {
            return root;
        }
        return kthSmallestElement(root.right, k);
    }

    // Given two BSTs containing n1 and n2 distinct nodes respectively. Given a
    // value x.
    // The problem is to count all pairs from both the BSTs whose sum is equal to x.
    public static int twoSumBST(Node root1, Node root2, int x) {

        if (root1 == null || root2 == null) {
            return 0;
        }

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        Node top1, top2;

        while (true) {

            while (root1 != null) {
                s1.push(root1);
                root1 = root1.left;
            }

            while (root2 != null) {
                s2.push(root2);
                root2 = root2.right;
            }

            if (s1.isEmpty() || s2.isEmpty()) {
                break;
            }

            top1 = s1.peek();
            top2 = s2.peek();

            if (top1.data + top2.data == x) {
                count++;
                s1.pop();
                s2.pop();
                root1 = top1.right;
                root2 = top2.left;

            } else if (top1.data + top2.data > x) {
                s2.pop();
                root2 = top2.left;

            } else {
                s1.pop();
                root1 = top1.right;
            }
        }

        return count;
    }

    static class Info2 {
        boolean isValid;
        int sum;
        int min;
        int max;

        public Info2(boolean isValid, int sum, int min, int max) {
            this.isValid = isValid;
            this.sum = sum;
            this.min = min;
            this.max = max;
        }
    }

    static int maxSum = 0;

    public static Info2 maxSumBSTinBT(Node root) {
        if (root == null) {
            return new Info2(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Info2 leftInfo = maxSumBSTinBT(root.left);
        Info2 rightInfo = maxSumBSTinBT(root.right);

        int sum = leftInfo.sum + rightInfo.sum + root.data;
        int min = Math.min(root.data, Math.min(leftInfo.min, rightInfo.min));
        int max = Math.max(root.data, Math.max(leftInfo.max, rightInfo.max));

        if (root.data <= leftInfo.max || root.data >= rightInfo.min) {
            return new Info2(false, sum, min, max);
        }

        if (leftInfo.isValid && rightInfo.isValid) {
            maxSum = Math.max(maxSum, sum);
            return new Info2(true, sum, min, max);
        }

        return new Info2(false, sum, min, max);
    }

    class Solution {
        private int maxSum = 0;

        public int maxSumBST(Node root) {
            postOrderTraverse(root);
            return maxSum;
        }

        public int[] postOrderTraverse(Node root) {
            if (root == null)
            // {min, max, sum}, initialize// min=MAX_VALUE, max=MIN_VALUE
            return new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };                                                                 
            int[] left = postOrderTraverse(root.left);
            int[] right = postOrderTraverse(root.right);
            // The BST is the tree:
            if (!(left != null // the left subtree must be BST
                    && right != null // the right subtree must be BST
                    && root.data > left[1] // the root's key must greater than maximum keys of the left subtree
                    && root.data < right[0])) // the root's key must lower than minimum keys of the right subtree
                return null;
            int sum = root.data + left[2] + right[2]; // now it's a BST make `root` as root
            maxSum = Math.max(maxSum, sum);
            return new int[] { Math.min(root.data, left[0]), Math.max(root.data, right[1]), sum };
        }
    }

    public static void main(String[] args) {

        int values[] = { 5, 9, 6, 8, -1, -1, 7, -1, -1, -1, 2, -1, 3, -1, -1 };

        Node root1 = buildTree(values);

        maxSumBSTinBT(root1);
        System.out.println(maxSum);

    }
}
