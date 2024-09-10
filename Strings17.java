

import java.util.Arrays;

public class Strings17 {
    public static boolean checkStringPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static float shortestPath(String str) {
        int x = 0, y = 0;
        for (int i = 0; i < str.length(); i++) {
            char dir = str.charAt(i);
            if (dir == 'W') {
                x--;
            } else if (dir == 'E') {
                x++;
            } else if (dir == 'N') {
                y++;
            } else {
                y--;
            }
        }
        int x2 = x * x, y2 = y * y; // x square, y square
        return (float) Math.sqrt(x2 + y2);
    }

    public static void printLargestString(String arr[]) { // lexicographically
        int n = arr.length;
        String largest = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i].compareToIgnoreCase(largest) > 0) {
                largest = arr[i];
            }
        }
        System.out.println(largest);
    }

    public static String toUpperCase(String str) {
        StringBuilder sb = new StringBuilder("");
        sb.append(Character.toUpperCase(str.charAt(0)));
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == ' ' && i < str.length() - 1) {
                sb.append(str.charAt(i));
                i++;
                sb.append(Character.toUpperCase(str.charAt(i)));
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String stringCompression(String str) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int count = 1;
            while (i < str.length() - 1 && str.charAt(i + 1) == ch) {
                count++;
                i++;
            }
            sb.append(ch);
            if (count > 1) {
                sb.append(count);
            }
        }
        return sb.toString();
    }

    public static int countVowels(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'i' || ch == 'a' || ch == 'e' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    public static void anagrams(String st1, String st2) {
        // Convert Strings to lowercase. Why? so thatwe don't have to checkseparately
        // for lower & uppercase.
        st1 = st1.toLowerCase();
        st2 = st2.toLowerCase();
        // First check - if the lengths are the same
        if (st1.length() == st2.length()) {
            // convert strings into char array
            char st1arr[] = st1.toCharArray();
            char st2arr[] = st2.toCharArray();
            // sort the char array
            Arrays.sort(st1arr);
            Arrays.sort(st2arr);
            // if the sorted char arrays are sameor identical then the strings areanagram
            boolean result = Arrays.equals(st1arr, st2arr);
            if (result) {
                System.out.println(st1 + " and " + st2 + " are anagrams of eachother.");
            } else {
                System.out.println(st1 + " and " + st2 + " are not anagrams ofeach other.");
            }
        } else {
            System.out.println(st1 + " and " + st2 + " are not anagrams ofeach other.");
        }
    }
    // read about st.intern() and StringBuffer
    public static String toUpperCase2(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == ' ') {
                sb.setCharAt(i + 1, (Character.toUpperCase(str.charAt(i + 1))));
            }
        }
        return sb.toString();
    }
    public static String toUpperCase3(String str) {
        char arr[] = str.toCharArray();
        for (int i = 0; i < str.length() - 1; i++) {
            if (arr[i] == ' ') {
                arr[i + 1] = Character.toUpperCase(arr[i + 1]);
                i++;
            }
        }
        return new String(arr);
    }
    public static void main(String[] args) {
        System.out.println(toUpperCase3(" a man is good "));
    }
}
