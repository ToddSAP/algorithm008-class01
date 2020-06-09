package Week_08.lru_cache;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class LRUCache_exercise {
    private HashMap<Integer, Node> map;
    private DoubleLinkedList cache;
    private int capacity;

    public LRUCache_exercise () {
        map = new HashMap<>();
        cache = new DoubleLinkedList();
        this.capacity = 2;
    }

    public int get (int key) {
        if (!map.containsKey(key)) return -1;
        int val = map.get(key).val;
        put(key, val);
        return val;
    }

    public void put (int key, int val) {
        Node x = new Node(key, val);
        if (map.containsKey(key)) {
            cache.remove(map.get(key));
        } else {
            if (capacity == cache.size) {
                Node last = cache.removeLast();
                map.remove(last.key);
            }
        }
        cache.addFirst(x);
        map.put(key, x);
    }

    static class DoubleLinkedList {
        private final Node head, tail;
        private int size;

        public DoubleLinkedList () {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void addFirst (Node x) {
            head.next.prev = x;
            x.next = head.next;
            head.next = x;
            x.prev = head;
            size++;
        }

        public Node removeLast () {
            if (size > 0) return remove(tail.prev);
            return null;
        }

        public Node remove (Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            x.prev = null;
            x.next = null;
            size--;
            return x;
        }

        public int size () {
            return size;
        }
    }

    static class Node {
        private int key, val;
        private Node prev, next;
        public Node (int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    @Test
    public void test_official() {
        //Given
        LRUCache_exercise lruCache = new LRUCache_exercise();
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        Assert.assertEquals(1, lruCache.get(1));
        lruCache.put(3, 3);
        Assert.assertEquals(-1, lruCache.get(2));
        lruCache.put(4, 4);
        Assert.assertEquals(-1, lruCache.get(1));
        Assert.assertEquals(3, lruCache.get(3));
        Assert.assertEquals(4, lruCache.get(4));
    }
}
