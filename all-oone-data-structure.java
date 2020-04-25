class AllOne {
    class DLLNode {
        public int value;
        public Set<String> keys;
        public DLLNode prev, next;
        public DLLNode(int v) {
            this.value = v;
            keys = new HashSet<String>();
            prev = next = null;
        }
    }

    private DLLNode head, tail;
    private Map<String, DLLNode> map;
    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<String, DLLNode>();
        head = new DLLNode(Integer.MIN_VALUE); // dummy
        tail = new DLLNode(Integer.MAX_VALUE); //dummy
        head.next = tail;
        tail.prev = head;
    }
    
    private DLLNode createNodeAfter(DLLNode node, int value) {
        DLLNode newNode = new DLLNode(value);
        newNode.next = node.next;
        newNode.prev = node;
        newNode.next.prev = newNode;
        newNode.prev.next = newNode;            
        return newNode;
    }
    private DLLNode createNodeBefore(DLLNode node, int value) {
        DLLNode newNode = new DLLNode(value);
        newNode.next = node;
        newNode.prev = node.prev;
        newNode.prev.next = newNode;
        newNode.next.prev = newNode;
        return newNode;
    }
    private void deleteNode(DLLNode node) {
        DLLNode left = node.prev;
        DLLNode right = node.next;
        left.next = right;
        right.prev = left;
    }
    private void transferKey(DLLNode currNode, DLLNode targetNode, String key) {
        currNode.keys.remove(key);
        targetNode.keys.add(key);
        if(currNode.keys.isEmpty()) {
            deleteNode(currNode);
        }
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(map.containsKey(key)) {
            DLLNode node = map.get(key);
            if(node.next.value != node.value+1)
                createNodeAfter(node,node.value+1);
            transferKey(node,node.next,key);
            map.put(key,node.next);
        } else {
            DLLNode node = head.next;
            if(node.value != 1)
                node = createNodeAfter(head,1);
            node.keys.add(key);
            map.put(key,node);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!map.containsKey(key)) return;
        DLLNode node = map.get(key);
        node.keys.remove(key);
        DLLNode prev = node.prev;
        if(node.keys.isEmpty()) 
            deleteNode(node); 
        
        if(node.value > 1) {
            if(prev.value != node.value-1)
                node = createNodeAfter(prev,node.value-1);
            else 
                node = prev;
            node.keys.add(key);
            map.put(key,node);
            }
        else 
            map.remove(key);              
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(tail.prev == head) return "";
        return tail.prev.keys.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(tail.prev == head) return "";
        return head.next.keys.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
