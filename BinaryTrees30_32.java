

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;

public class BinaryTrees30_32 {

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
    

    public static void preorder(Node root) {
        if (root == null) {
            System.out.print(-1 + " ");
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void levelorder(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            Node curr = q.remove();
            if (curr == null) {
                System.out.println();
                if (!q.isEmpty()) {
                    q.add(null);
                } else {
                    break;
                }
            } else {
                System.out.print(curr.data + " ");
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }

        }
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    public static int sumOfNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return sumOfNodes(root.left) + sumOfNodes(root.right) + root.data;
    }

    public static int diameter1(Node root) { // approach 1
        if (root == null) {
            return 0;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        int selfdia = lh + rh + 1;
        int ldia = diameter1(root.left);
        int rdia = diameter1(root.right);
        return Math.max(selfdia, Math.max(ldia, rdia));
    }

    static class Info {
        int diam; // diameter
        int ht; // height

        public Info(int dia, int h) {
            diam = dia;
            ht = h;
        }
    }

    public static Info diameter2(Node root) { // approach 2
        if (root == null) {
            return new Info(0, 0);
        }
        Info linf = diameter2(root.left); // left Information
        Info rinf = diameter2(root.right); // right Information
        int diam = Math.max(linf.ht + rinf.ht + 1, Math.max(linf.diam, rinf.diam));
        int ht = Math.max(linf.ht, rinf.ht) + 1;
        return new Info(diam, ht);
    }

    public static boolean isSubtree(Node root, Node subroot) {
        if (root == null) {
            return false;
        }
        if (root.data == subroot.data) {
            if (isIndentical(root, subroot)) {
                return true;
            }
        }
        return isSubtree(root.left, subroot) || isSubtree(root.right, subroot);
    }

    public static boolean isIndentical(Node node, Node subroot) {
        if (node == null && subroot == null) {
            return true;
        } else if (node == null || subroot == null || node.data != subroot.data) {
            return false;
        }
        if (!isIndentical(node.left, subroot.left)) {
            return false;
        }
        if (!isIndentical(node.right, subroot.right)) {
            return false;
        }
        return true;
    }

    static class Info2 {
        Node node;
        int hd;

        public Info2(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public static void topview(Node root) {

        Queue<Info2> q = new LinkedList<>();
        q.add(new Info2(root, 0));
        q.add(null);

        int min = 0, max = 0;
        HashMap<Integer, Node> map = new HashMap<>();

        while (!q.isEmpty()) {

            Info2 curr = q.remove();
            if (curr == null) {

                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }

            } else {

                if (!map.containsKey(curr.hd)) {
                    map.put(curr.hd, curr.node);
                }

                if (curr.node.left != null) {
                    q.add(new Info2(curr.node.left, curr.hd - 1));
                    min = Math.min(min, curr.hd - 1);
                }

                if (curr.node.right != null) {
                    q.add(new Info2(curr.node.right, curr.hd + 1));
                    max = Math.max(max, curr.hd + 1);
                }
            }
        }

        for (int i = min; i <= max; i++) {
            System.out.print(map.get(i).data + " ");
        }
    }

    // Kth level of a tree
    public static void kLevel(Node root, int level, int k) {
        if (root == null) {
            return;
        }
        if (level == k) {
            System.out.print(root.data + " ");
            return; // because level will only increase now
        }
        kLevel(root.left, level + 1, k);
        kLevel(root.right, level + 1, k);
    }

    // Lowest common ancestor
    public static Node lca1(Node root, int n1, int n2) { // approach 1

        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();
        getPath(root, n1, path1);
        getPath(root, n2, path2);

        int i = 0;
        for (; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) != path2.get(i)) {
                break;
            }
        }
        return path1.get(i - 1);
    }

    public static boolean getPath(Node root, int n, ArrayList<Node> path) {
        if (root == null) {
            return false;
        }
        path.add(root);
        if (root.data == n) {
            return true;
        }
        if (getPath(root.left, n, path) || getPath(root.right, n, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    public static Node lca2(Node root, int n1, int n2) {
        if (root == null || root.data == n1 || root.data == n2) {
            return root;
        }
        Node leftlca = lca2(root.left, n1, n2);
        Node rightlca = lca2(root.right, n1, n2);
        if (leftlca == null) {
            return rightlca;
        }
        if (rightlca == null) {
            return leftlca;
        }
        return root;
    }

    public static int lcaDist(Node root, int n) {
        if (root == null) {
            return -1;
        }
        if (root.data == n) {
            return 0;
        }
        int leftDist = lcaDist(root.left, n);
        int rightDist = lcaDist(root.right, n);
        if (leftDist == -1 && rightDist == -1) {
            return -1;
        }
        return Math.max(leftDist, rightDist) + 1;
    }

    public static int minDist(Node root, int n1, int n2) { // Minimum Distance
        Node lca = lca2(root, n1, n2);
        int dist1 = lcaDist(lca, n1); // distance between lca and n1
        int dist2 = lcaDist(lca, n2); // distance between lca and n2
        return dist1 + dist2;
    }

    public static int kthAncestor(Node root, int n, int k) {
        if (root == null) {
            return -1;
        }
        if (root.data == n) {
            return 0;
        }

        int leftDist = kthAncestor(root.left, n, k);
        int rightDist = kthAncestor(root.right, n, k);

        if (leftDist == -1 && rightDist == -1) {
            return -1;
        }
        int max = Math.max(leftDist, rightDist);
        if (max + 1 == k) {
            System.out.println(root.data);
            // We'll print it. If we return it then it will not be returned
            // to main function
        }
        return max + 1;
    }

    public static int transformToSumTree(Node root) {
        if (root == null) {
            return 0;
        }

        int leftSum = transformToSumTree(root.left);
        int rightSum = transformToSumTree(root.right);
        int data = root.data;

        int newleft = root.left == null ? 0 : root.left.data;
        int newright = root.right == null ? 0 : root.right.data;
        root.data = newleft + leftSum + newright + rightSum;

        return data;
    }

    public static boolean isUnivalTree(Node root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.data != root.left.data) {
            return false;
        }
        if (root.right != null && root.data != root.right.data) {
            return false;
        }
        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }

    public static Node mirror(Node root) {

        if (root == null) {
            return null;
        }

        Node newNode = root.left;
        root.left = root.right;
        root.right = newNode;

        mirror(root.left);
        mirror(root.right);

        return root;
    }

    public static Node deleteLeafNodes(Node root, int n) {
        if (root == null) {
            return null;
        }

        root.left = deleteLeafNodes(root.left, n);
        root.right = deleteLeafNodes(root.right, n);

        if (root.left == null && root.right == null && root.data == n) {
            return null;
        }

        return root;
    }

    static HashMap<String, Integer> map = new HashMap<>();
    
    public static String inorderDuplicate(Node root) {
        if (root == null) {
            return "";
        }
        String str = "(";
        str += inorderDuplicate(root.left);
        str += Integer.toString(root.data);
        str += inorderDuplicate(root.right);
        str += ")";

        if (map.get(str) != null && map.get(str) == 1) {
            System.out.print(root.data + " ");
        }
        if (map.containsKey(str)) {
            map.put(str, map.get(str) + 1);
        } else {
            map.put(str, 1);
        }

        return str;
    }

    static int res;

    public static int maxSum(Node root) {
        res = Integer.MIN_VALUE;
        maxSumPath(root);
        return res;
    }

    
    public static int maxSumPath(Node root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, maxSumPath(root.left));
        int right = Math.max(0, maxSumPath(root.right));

        res = Math.max(res, left + right + root.data);
        return Math.max(left, right) + root.data;
    }

    public static void bottomViewHelper(Node root, int curr, int hd, HashMap<Integer, int[]> hm) {
        if (root == null) {
            return;
        }
        
        if (!hm.containsKey(hd)) {
            hm.put(hd, new int[] {root.data, curr});
        } else {
            int p[] = hm.get(hd);
            if (p[1] <= curr) {
                p[1] = curr;
                p[0] = root.data;
                hm.put(hd, p);
            }
        }

        bottomViewHelper(root.left, curr + 1, hd - 1, hm);
        bottomViewHelper(root.right, curr + 1, hd + 1, hm);
    }

    public static void printBottomView(Node root) {
        HashMap<Integer, int[]> hm = new HashMap<>();
        bottomViewHelper(root, 0, 0, hm);
        for (int[] p : hm.values()) {
            System.out.print(p[0] + " ");
        }
    }


    public static void main(String[] args) {
       
        

    }
}
