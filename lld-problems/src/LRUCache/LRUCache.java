package LRUCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private int capacity;
    private Map<Integer, Node> m;
    private Node head;
    private Node tail;

    private void add(Node node) {
        //adds to back of the list
        Node prevNode = tail.prev;
        prevNode.next = node;
        node.prev = prevNode;
        node.next = tail;
        tail.prev = node;
    }

    private void remove(Node node) {
        //removes from front of the list
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        m = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(!m.containsKey(key))
            return -1;

        Node node = m.get(key);
        remove(node);
        add(node);

        return node.value;
    }

    public void put(int key, int value) {
        if(m.containsKey(key)) {
            Node node = m.get(key);
            remove(node);
        }
        Node newNode = new Node(key, value);
        add(newNode);
        m.put(key, newNode);

        if(m.size() > capacity) {
            Node node = head.next;
            remove(node);
            m.remove(node.key);
        }
    }
}
