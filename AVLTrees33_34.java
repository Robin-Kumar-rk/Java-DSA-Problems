
public class AVLTrees33_34 {

    static class Node {
        int data, height;
        Node left, right;
        public Node(int d) {
            data = d;
            height = 1;
        }
    }

    // Get Height of Root
    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        return root.height;
    }

    // Get Balance Factor of Root
    public static int getBalance(Node root) {
        if (root == null) {
            return 0;
        }
        return height(root.left) - height(root.right);
    }

    // Left Rotate
    public static Node leftRotate(Node x) {

        // Define Nodes
        Node y = x.right;
        Node t2 = y.left;

        // Perform Rotation
        y.left = x;
        x.right = t2;

        // Update Height
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return y as new root
        return y;

    }

    // Right Rotate
    public static Node rightRotate(Node x) {

        // Define Nodes
        Node y = x.left;
        Node t2 = y.right;

        // Perform Rotate
        y.right = x;
        x.left = t2;

        // Update Height
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return y as new root
        return y;

    }

    // 
    public static Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.data) {
            root.left = insert(root.left, key);
        } else if (key > root.data) {
            root.right = insert(root.right, key);
        } else {   // duplicate condition : we'll not insert it 
            return root;
        }

        // Update Root Height
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // Get Root's Balance Factor
        int bf = getBalance(root);

        // LL case
        if (bf > 1 && key < root.left.data) {
            return rightRotate(root);
        }

        // RR case 
        if (bf < -1 && key > root.right.data) {
            return leftRotate(root);
        }

        // LR case 
        if (bf > 1 && key > root.left.data) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RL case 
        if (key < 1 && key < root.right.data) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;  // Return if AVL Tree is balaned
    }

    public static void preorder(Node root) {
        if (root == null) {
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
    public static void main(String[] args) {
        Node root = insert(null, 10);
        root = insert(root, 20);
        root = insert(root, 30);
        root = insert(root, 40);
        root = insert(root, 50);
        root = insert(root, 60);
        root = insert(root, 70);
        preorder(root);
        System.out.println();
        inorder(root);
        
    }
}
