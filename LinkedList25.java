

public class LinkedList25 {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            next = null;
        }
    }

    public Node head;
    public Node tail;
    public int size;

    public void addFirst(int data) { // O(1)
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int data) { // O(1)
        if (size == 0) {
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        tail.next = newNode;
        tail = newNode;
    }

    public void print() { // O(n)
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void add(int idx, int data) { // O(n)
        if (size == 0) {
            addFirst(data);
        } else {
            Node prev = head;
            for (int i = 0; i < idx - 1; i++) {
                prev = prev.next;
            }
            Node newNode = new Node(data);
            size++;
            newNode.next = prev.next;
            prev.next = newNode;
        }
    }

    public int removeFirst() { // O(1)
        if (size == 0) {
            System.out.println("LinkedList is empty");
            return Integer.MIN_VALUE;
        }
        int val = head.data;
        if (size == 1) {
            head = tail = null;
            size = 0;
            return val;
        }
        head = head.next;
        size--;
        return val;

    }

    public int removeLast() {
        if (size == 0 || size == 1) {
            return removeFirst();
        } else {
            Node prev = head;
            for (int i = 0; i < size - 2; i++) {
                prev = prev.next;
            }
            int val = prev.data;
            prev.next = null;
            tail = prev;
            size--;
            return val;
        }
    }

    public int searchIterative(int key) { // O(n)
        Node temp = head;
        int i = 0;
        while (temp != null) {
            if (temp.data == key) { // found key
                return i;
            }
            temp = temp.next;
            i++;
        }
        return -1;
    }

    public int searchRecursively(int key, Node head) { // TC -> O(N) , SC -> O(N) due call stack
        if (head == null) {
            return -1;
        }
        if (head.data == key) {
            return 0;
        }
        int idx = searchRecursively(key, head.next);
        if (idx == -1) {
            return -1;
        }
        return idx + 1;
    }

    public int searchRecursively2(Node head, int i, int key) {
        if (head == null) {
            return -1;
        }
        if (head.data == key) {
            return i;
        }
        return searchRecursively2(head.next, i + 1, key);
    }

    public void reverse() {
        Node prev = null;
        Node curr = tail = head;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public int removeNthNodeFromEnd(int n) {
        // calcutaing size.
        int size = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }

        if (n == size) {
            int val = head.data;
            head = head.next;
            this.size--;
            return val;
        }
        Node prev = head;
        for (int i = 1; i < size - n; i++) {
            prev = prev.next;
        }
        int val = prev.next.data;
        prev.next = prev.next.next;
        this.size--;
        return val;
    }

    public Node findMidNode() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
        }
        return slow; // slow is mid
    }

    public boolean isPalindrome() {
        if (head == null || head.next == null) { // corner cases
            return true;
        }
        // Step 2 - reverse 2nd half
        Node prev = null;
        Node curr = findMidNode(); // Step 1 - find mid node
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // Step 3 - check left half & right half
        Node rightHead = prev;
        Node leftHead = head;
        while (rightHead != null) {
            if (leftHead.data != rightHead.data) {
                return false;
            }
            leftHead = leftHead.next;
            rightHead = rightHead.next;
        }
        return true;
    }

    public boolean detectLoop() { // Floyd's cycle finding algorithm
        Node slow = head; // Firstly we will update because in the first slow = fast
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true; // cycle exists
            }   
        }
        return false; // cycle does not exist
    }

    public void removeCycle() {

        // detecting cycle
        Node slow = head, fast = head;
        boolean loop = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                loop = true;
                break;
            }
        }

        // if cycle does not exist then return
        if (!loop) {
            return;
        }

        // finding last Node
        slow = head;
        Node prev = null; // prev will be our last Node
        while (slow != fast) {
            slow = slow.next;
            prev = fast;
            fast = fast.next;
        }
        prev.next = null; // removing cycle
    }

    // we will call this function only in this class so we will make it private type
    private Node getMid(Node head) {
        Node slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Find mid
        Node mid = getMid(head);
        Node rhead = mid.next;
        mid.next = null; // deviding

        // call Mergesort for left and right
        Node left = mergeSort(head);
        Node right = mergeSort(rhead);

        // merge left and right
        return merge(left, right);
    }

    public static Node merge(Node left, Node right) {
        // creating a dummy/fake Node we will delete it later
        Node mergedLL = new Node(-1);
        Node temp = mergedLL;
        while (left != null && right != null) {
            if (left.data < right.data) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }
        if (left == null) {
            temp.next = right;
        } else {
            temp.next = left;
        }
        return mergedLL.next;
    }

    public void zigZag() {
        // corner case
        if (head == null) {
            return;
        }
        // find midNode
        Node mid = getMid(head);
        // reversing right half
        Node curr = mid.next;
        mid.next = null;
        Node prev = null;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // converting in zig zag
        Node rHead = prev;
        Node lHead = head;
        Node nextR, nextL;
        while (rHead != null && lHead != null) {
            nextL = lHead.next;
            lHead.next = rHead;
            nextR = rHead.next;
            rHead.next = nextL;
            lHead = nextL;
            rHead = nextR;
        }
    }

    public static int size(Node head) {
        int size = 1;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        return size;
    }

    // if head1 and head2 have same length then loop will be terminated after 1st
    // iteration.
    // if lengths are different loop will be definitely terminated after 2nd
    // iteration.
    public static Node intersection(Node head1, Node head2) {
        if (head1 == null || head2 == null) // corner case
            return null;
        Node x = head1;
        Node y = head2;
        while (x != y) {
            x = x == null ? head2 : x.next;
            y = y == null ? head1 : y.next;
        }
        return x;
    }

    public void deleteNNodesAfterMNodes(int n, int m) {
        if (m == 0) {
            head = null;
            return;
        }
        Node curr = head;
        while (curr != null) {
            for (int i = 1; i < m; i++) {
                // whatever the value of i after the loop
                // curr will also be on the same number node
                if (curr == null) {
                    return;
                }
                curr = curr.next;
            }
            Node temp = curr.next;
            for (int i = 1; i <= n && temp != null; i++) {
                // whatever the value of i after the loop
                // temp will also be on the same number node
                temp = temp.next;
            }
            curr.next = temp;
            curr = temp;
        }
    }

    public void swapNodes(int a, int b) {
        if (a == b) {
            return;
        }

        // currA = currentA Node whose data is a
        // prevA = previousA which is previous of currA
        Node prevA = null, currA = head;
        while (currA != null && currA.data != a) {
            prevA = currA;
            currA = currA.next;
        }

        Node prevB = null, currB = head;
        while (currB != null && currB.data != b) {
            prevB = currB;
            currB = currB.next;
        }

        // if any Node is null, we'll not swap
        if (currA == null || currB == null) {
            return;
        }

        if (prevA != null) {
            prevA.next = currB;
        } else {
            head = currB;
        }

        if (prevB != null) {
            prevB.next = currA;
        } else {
            head = currA;
        }

        Node temp = currA.next;
        currA.next = currB.next;
        currB.next = temp;
    }

    public void evenOddLL() {
        Node evenHead = new Node(-1);
        Node tempEven = evenHead;
        Node oddHead = new Node(-1);
        Node tempOdd = oddHead;
        while (head != null) {
            if (head.data % 2 == 0) {
                tempEven.next = head;
                tempEven = tempEven.next; // OR tempEven = head;
            } else {
                tempOdd.next = head;
                tempOdd = head; // OR tempOdd = tempOdd.next;
            }
            head = head.next;
        }
        tempEven.next = oddHead.next; // linking EvenLL to OddLL
        tempOdd.next = null; // making last point of OddLL null
        head = evenHead.next;
    }

    // Here we are using divide and conquer
    // We are using two pointers i ans j and after merging i & j we put it on i
    public static Node mergeKLists(Node[] arr) {
        if (arr.length == 0) {
            return null;
        }
        int j = arr.length - 1;
        while (j > 0) {
            int i = 0;
            while (i < j) {
                arr[i] = merge(arr[i], arr[j]);
                i++; j--;
            }
        }
        return arr[0];
    }

    public static void main(String[] args) {
        int k = 3;
        Node arr[] = new Node[k];
        arr[0] = new Node(1);
        arr[0].next = new Node(3);
        arr[0].next.next = new Node(5);
        arr[0].next.next.next = new Node(7);
        arr[1] = new Node(2);
        arr[1].next = new Node(4);
        arr[1].next.next = new Node(6);
        arr[1].next.next.next = new Node(8);
        arr[2] = new Node(0);
        arr[2].next = new Node(9);
        arr[2].next.next = new Node(10);
        arr[2].next.next.next = new Node(11);
        LinkedList25 ll = new LinkedList25();
        ll.head = mergeKLists(arr);
        ll.print();
    }

}
