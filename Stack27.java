

import java.util.ArrayList;
import java.util.Stack;

public class Stack27 {

    public static void pushAtBottom(Stack<Integer> s, int data) {
        if (s.isEmpty()) {
            s.push(data);
            return;
        }
        int top = s.pop();
        pushAtBottom(s, data);
        s.push(top);
    }

    public static String reverseString(String st, Stack<Character> s) {
        if (st.length() == 1) {
            return st;
        }
        s.push(st.charAt(0));
        return reverseString(st.substring(1), s) + s.pop();
    }

    public static String reverseString(String st) {
        Stack<Character> s = new Stack<>();
        for (char c : st.toCharArray()) {
            s.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty()) {
            sb.append(s.pop());
        }
        return sb.toString();
    }

    public static String reverseString3(String st) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < st.length(); i++) {
            s.push(st.charAt(i));
        }
        st = "";
        while (!s.isEmpty()) {
            st = st + s.pop();
        }
        return st;
    }

    public static void reverseStack(Stack<Integer> s) {
        if (s.isEmpty())
            return;
        int top = s.pop();
        reverseStack(s);
        pushAtBottom(s, top);
    }

    public static void printStack(Stack<Integer> s) {
        if (s.isEmpty()) {
            System.out.println();
            return;
        }
        int top = s.pop();
        System.out.print(top + " ");
        printStack(s);
        s.push(top);
    }

    public static void printArr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] StockSpanProblem(int[] stocks) {
        int span[] = new int[stocks.length];
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < stocks.length; i++) {
            while (!s.isEmpty() && stocks[i] >= stocks[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) {
                span[i] = i + 1;
            } else {
                span[i] = i - s.peek();
            }
            s.push(i);
        }
        return span;
    }

    public static int[] nextGreater(int[] arr) {
        int[] greater = new int[arr.length];
        Stack<Integer> s = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && arr[i] >= arr[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) {
                greater[i] = -1;
            } else {
                greater[i] = arr[s.peek()];
            }
            s.push(i);
        }
        return greater;
    }

    public static boolean validParentheses(String st) {
        Stack<Character> s = new Stack<>();
        for (char ch : st.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                s.push(ch);
            } else {
                if (s.isEmpty()) {
                    return false;
                }
                if ((ch == ')' && s.peek() == '(')
                    || (ch == '}' && s.peek() == '{')
                    || (ch == ']' && s.peek() == '[')) {
                    s.pop();
                } else {
                    return false;
                }
            }
        }
        return s.isEmpty();
    }

    public boolean validParentheses2(String st) {
        ArrayList<Character> s = new ArrayList<>();
        for (char c : st.toCharArray()) {
            if (c == '(') {
                s.add(')');
            } else if (c == '{') {
                s.add('}');
            } else if (c == '[') {
                s.add(']');
            } else if (s.size() == 0 || s.remove(s.size() - 1) != c) {
                return false;
            }  
        }
        return s.size() == 0;
    }

    public static boolean duplicateParentheses(String st) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < st.length(); i++) {
            char ch = st.charAt(i);
            if (ch != ')') {
                s.push(ch);
            } else {
                int count = 0;
                while (s.pop() != '(') {
                    // if string contains spaces then this condition is useful
                    // becase we have not no count spaces
                    // if string does not have spaces
                    // then we write only count++;
                    if (s.peek() != ' ') {
                        count++;
                    }
                }
                if (count == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int MaxAreaInHistogram(int[] heights) {
        int n = heights.length;

        // Next Smaller Left
        Stack<Integer> s = new Stack<>();
        int[] nSl = new int[n];
        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && heights[i] <= heights[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) {
                nSl[i] = -1;
            } else {
                nSl[i] = s.peek();
            }
            s.push(i);
        }

        // Next Smaller Right
        s = new Stack<>();
        int[] nSr = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!s.isEmpty() && heights[i] <= heights[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) {
                nSr[i] = n; // Special case
            } else {
                nSr[i] = s.peek();
            }
            s.push(i);
        }

        // Calculating CurrentArea And Comparing to MaxArea
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int currArea = heights[i] * (nSr[i] - nSl[i] - 1);
            maxArea = Math.max(maxArea, currArea);
        }
        return maxArea;
    }

        
    // class for isPalindrome fuction
    static class LinkedList {
        static class Node {
            char data;
            Node next;

            public Node(char data) {
                this.data = data;
            }
        }

        public Node head;
        public Node tail;

        public void addLast(char data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = tail = newNode;
                return;
            }
            tail.next = newNode;
            tail = newNode;
        }
    }

    public static boolean isPalindrome(LinkedList list) {

        // finding size of LinkedList
        LinkedList.Node temp = list.head;
        int listSize = 0;
        while (temp != null) {
            listSize++;
            temp = temp.next;
        }
        temp = list.head;

        // traverse half LinkedList
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < listSize / 2; i++) {
            s.push(temp.data);
            temp = temp.next;
        }

        // odd case
        if (listSize % 2 != 0) {
            temp = temp.next;
        }

        // traverse remaining half LinkedList
        for (int i = 0; i < listSize / 2; i++) {
            if (s.pop() == temp.data) {
                temp = temp.next;
            } else {
                return false;
            }
        }
        if (s.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void isPalindromeCall() {
        LinkedList list = new LinkedList();
        list.addLast('A');
        list.addLast('B');
        list.addLast('C');
        list.addLast('D');
        list.addLast('C');
        list.addLast('B');
        list.addLast('A');
        System.out.println(isPalindrome(list));
    }


    public static String simplifyPath(String st) {
        Stack<String> s = new Stack<>();
        String res = "/";
        int n = st.length();
        for (int i = 0; i < n; i++) {
            String dir = "";
            while (i < n && st.charAt(i) == '/') {
                i++;
            }
            while (i < n && st.charAt(i) != '/') {
                dir += st.charAt(i);
                i++;
            }
            if (dir.equals("..")) {
                if (!s.isEmpty())
                    s.pop();
            } else if (dir.equals(".")) {
                continue;
            } else if (dir.length() != 0) {
                s.push(dir);
            }
        }
        Stack<String> s2 = new Stack<>();
        while (!s.isEmpty()) {
            s2.push(s.pop());
        }
        while (!s2.isEmpty()) {
            if (s2.size() == 1) {
                res += s2.pop();
            } else {
                res += (s2.pop() + "/");
            }
        }
        return res;
    }

    // 2nd code of above question.
    // import ArrayList class to use it.
    // many new fuction are used in this code.

    public static String simplifyPath2(String path) {
        Stack<String> stack = new Stack<>();
        String[] p = path.split("/");
        for (int i = 0; i < p.length; i++) {
            if (!stack.empty() && p[i].equals(".."))
                stack.pop();
            else if (!p[i].equals(".") && !p[i].equals("") && !p[i].equals(".."))
                stack.push(p[i]);
        }
        ArrayList<String> list = new ArrayList<>(stack);
        return "/" + String.join("/", list);
    }

    public static String decodeString1(String s) {
        // assume st = "3[s2[a5[b]h]]"
        // we will traverse on st
        // we have four cases when we traverse on st
        // Case 1 : we meet digit
        // Case 2 : we meet '['
        // Case 3 : we meet ']'
        // Case 4 : we meet any other character like 'a', 'g' etc.
        // we will handle all above four cases

        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            } else {
                res += s.charAt(idx++);
            }
        }
        return res;

    }

    // we use only StringBuilder in this solution so it is better solution
    public static String decodeString2(String st) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : st.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                intStack.push(k);
                k = 0;
                strStack.push(cur);
                cur = new StringBuilder(); // making cur empty
            } else if (ch == ']') {
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; --k)
                    cur.append(tmp);
            } else
                cur.append(ch);
        }
        return cur.toString();
    }

    public static int trappedRainWater(int[] height) {
        Stack<Integer> s = new Stack<>();
        int ans = 0;
        int n = height.length;
        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && height[s.peek()] < height[i]) {
                int popHeight = height[s.pop()];
                if (s.isEmpty()) {
                    break;
                }
                int distance = i - s.peek() - 1;
                int minHeight = Math.min(height[s.peek()], height[i]) - popHeight;
                ans += distance * minHeight;
            }
            s.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
       int height[] =  {7, 0, 4, 2, 5, 0, 6, 4, 0, 6};
		System.out.println(trappedRainWater(height));
    }
}


