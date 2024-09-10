public class Trie37 {

    static class Node {

        Node[] children = new Node[26];
        boolean eow = false;  // End of word

        public Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    public static Node root = new Node();

    public static void insert(String word) {  // O(L)
         
        Node curr = root;
        for (int level = 0; level < word.length(); level++) {
            int idx = word.charAt(level) - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }
            curr = curr.children[idx];
        }
        curr.eow = true;

    }

    public static boolean search(String key) {  // O(L)

        Node curr = root;
        for (int level = 0; level < key.length(); level++) {
            int idx = key.charAt(level) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }
        return curr.eow;

    }

    public static boolean wordBreak(String st) {

        if (st.length() == 0) {
            return true;
        }

        for (int i = 1; i <= st.length(); i++) {  // i = 1; because substring
            if (search(st.substring(0, i)) && wordBreak(st.substring(i))) {
                return true;
            }
        }
        return false;

    }

    static class Node2 {
        Node2[] children = new Node2[26];
        boolean eow = false;
        int freq;  // frequency

        public Node2() {
            for (int i = 0; i < 26; i++) {
                children[i] = null; 
            }
            freq = 1;
        }
    }

    public static Node2 root2 = new Node2();

    public static void insert2(String word) {
        Node2 curr = root2;
        for (int level = 0; level < word.length(); level++) {
            int idx = word.charAt(level) - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node2();
            } else {
                curr.children[idx].freq++;
            }
            curr = curr.children[idx];
        }
        curr.eow = true;
    }

    public static void findPrefix(Node2 root, String ans) {   // O(L)
        if (root == null) {
            return;
        }

        if (root.freq == 1) {
            System.out.println(ans);
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                findPrefix(root.children[i], ans + (char)(i + 'a'));
            }
        }
    }

    public static boolean startsWith(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }
        return true;
    }

    public static int countNodes(Node root) {

        if (root == null) {
            return 0;
        }

        int currNodes = 1;
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                currNodes += countNodes(root.children[i]);
            }
        }
        return currNodes;
    }

    public static int countUniqueSubstrings(String str) {
        for (int i = 0; i < str.length(); i++) {
            insert(str.substring(i));
        }

        return countNodes(root);
    }

    static String ans = "";
    // Longest Word with all prefixes
    public static void longestWord(Node root, StringBuilder temp) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].eow) {
                temp.append((char)(i + 'a'));
                if (temp.length() > ans.length()) {
                    ans = temp.toString();
                }
                longestWord(root.children[i], temp);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }
    

    public static void main(String[] args) {
        
    }
}
