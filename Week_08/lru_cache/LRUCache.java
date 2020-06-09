package Week_08.lru_cache;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class LRUCache {
    private HashMap<Integer, Node>  map;
    private DoubleLink cache;
    private final static int DEFAULT_CAP = 2;
    private int cap;

    public LRUCache () {
        map = new HashMap<>();
        cache = new DoubleLink();
        cap = DEFAULT_CAP;
    }

    public int get (int key) {
        if (!map.containsKey(key)) return -1;
        int val = map.get(key).val;
        put(key, val);
        return val;
    }

    public void put (int key, int value) {
        Node x = new Node(key, value);
        if (map.containsKey(key)) {
            cache.remove(map.get(key));
        } else {
            if (cap == cache.size) {
                Node last = cache.removeLast();
                map.remove(last.key);
            }
        }
        cache.addFirst(x);
        map.put(key, x);
    }

    static class Node {
        public int key, val;
        public Node prev, next;

        public Node (int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    static class DoubleLink {
        private Node head, tail;
        private int size;

        public DoubleLink () {
            head = new Node (0, 0);
            tail = new Node (0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void addFirst (Node n) {
            n.next = head.next;
            head.next.prev = n;
            head.next = n;
            n.prev = head;
            size++;
        }

        public void remove (Node n) {
            n.next.prev = n.prev;
            n.prev.next = n.next;
            size--;
        }

        public Node removeLast () {
            if (tail.prev == head)
                return null;
            Node last = tail.prev;
            remove(last);
            return last;
        }

        public int size () {
            return size;
        }
    }

    @Test
    public void test_official() {
        //Given
        LRUCache lruCache = new LRUCache();
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        Assert.assertEquals(1, lruCache.get(1));
        lruCache.put(3, 3);
        Assert.assertEquals(-1, lruCache.get(2));
        lruCache.put(4, 4);
        Assert.assertEquals(-1, lruCache.get(1));
        Assert.assertEquals(3, lruCache.get(3));
        Assert.assertEquals(4, lruCache.get(4));

        //When

        //Then
    }
}
