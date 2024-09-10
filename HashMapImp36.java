import java.util.ArrayList;

import java.util.LinkedList;

public class HashMapImp36 {
    static class HashMap<K, V> {  
    
        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int n;
        private int N;
        
        private LinkedList<Node>[] bucket;

        @SuppressWarnings("unchecked")
        public HashMap() {
            N = 4;  // initial map size

            bucket = new LinkedList[N];  

            for (int i = 0; i < N; i++) {
                bucket[i] = new LinkedList<Node>();
            }
                                        
        }

        private int hashFunction(K key) {
            int hc = key.hashCode();  // hc = hash code 
            return Math.abs(hc) % N;
        }

        private int searchInLL(K key, int bi) {
            LinkedList<Node> ll = bucket[bi];
            int di = 0;
            for (Node node : ll) {
                if (node.key == key) {
                    return di;
                }
                di++;
            }
            return -1;
        }

        public void put(K key, V value) {

            int bi = hashFunction(key);  // bi = bucket index
            int di = searchInLL(key, bi);  // di = data index

            if (di != -1) {
                bucket[bi].get(di).value = value;
            } else {
                bucket[bi].add(new Node(key, value));
                n++;
            }

            double lamda = (double) n / N;

            if (lamda > 2.0) {
                rehash();
            }
        }

        @SuppressWarnings("unchecked")
        private void rehash() {
            LinkedList<Node>[] old = bucket;
            N *= 2;
            bucket = new LinkedList[N];
            for (int i = 0; i < N; i++) {
                bucket[i] = new LinkedList<Node>();
            }
            for (LinkedList<Node> ll : old) {
                for (Node node : ll) {
                    put(node.key, node.value);
                }
            }
        }

        public V get(K key) { 
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);

            if (di != -1) {
                return bucket[bi].get(di).value;
            } else {
                return null;
            }
        }

        public boolean containsKey(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);

            if (di != -1) {
                return true;
            } else {
                return false;
            }
        }

        public V remove(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);

            if (di != -1) {
                n--;
                return bucket[bi].remove(di).value;
            } else {
                return null;
            }
        }

        public boolean isEmpty() {
            return n == 0;
        }

        public ArrayList<K> keySet() {
            ArrayList<K> keys = new ArrayList<>();
            for (LinkedList<Node> list : bucket) {
                for (Node node : list) {
                    keys.add(node.key);
                }
            }
            return keys;
        }

    }
    public static void main(String[] args) {
       
    
    }
}
