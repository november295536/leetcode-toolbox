import java.util.HashMap;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class LRUCache {

    class DoubleLinkedNode {
        int key;
        int value;
        DoubleLinkedNode pre;
        DoubleLinkedNode post;
    }

    private int capacity;
    private int count;
    private HashMap<Integer, DoubleLinkedNode> cache;
    private DoubleLinkedNode head = new DoubleLinkedNode();
    private DoubleLinkedNode tail = new DoubleLinkedNode();


    public LRUCache(int capacity) {
        head.post = tail;
        tail.pre = head;
        this.capacity = capacity;
        this.count = 0;
        cache = new HashMap<>();

    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            DoubleLinkedNode node = cache.get(key);
            update(node);

            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)){
            DoubleLinkedNode node = cache.get(key);
            node.value = value;
            update(node);
            return;
        }
        count++;
        if (count > capacity){
            DoubleLinkedNode lastNode = popLastNode();
            cache.remove(lastNode.key);
            count--;
        }
        DoubleLinkedNode node = new DoubleLinkedNode();
        node.key = key;
        node.value = value;
        addToHead(node);
        cache.put(key, node);

    }

    private void addToHead(DoubleLinkedNode node) {
        DoubleLinkedNode firstNode = head.post;
        head.post = node;
        firstNode.pre = node;
        node.pre = head;
        node.post = firstNode;
    }

    private void update(DoubleLinkedNode node) {
        DoubleLinkedNode pre = node.pre;
        DoubleLinkedNode post = node.post;
        pre.post = post;
        post.pre = pre;
        addToHead(node);
    }

    private DoubleLinkedNode popLastNode() {
        DoubleLinkedNode lastNode = tail.pre;
        DoubleLinkedNode newLastNode = lastNode.pre;
        newLastNode.post = tail;
        tail.pre = newLastNode;
        return lastNode;
    }
}
