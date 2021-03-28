/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

class MinStack {

    /** initialize your data structure here. */
    class LinkedNode {
        int value;
        int min;
        LinkedNode pre;
    }
    private LinkedNode head;
    public MinStack() {
        head = new LinkedNode();
    }

    public void push(int val) {
        LinkedNode node = new LinkedNode();
        node.value = val;
        if(head.pre == null) {
            node.min = val;
            head.pre = node;
            return;
        }
        LinkedNode oldTop = head.pre;
        node.min = Math.min(oldTop.min, val);
        node.pre = oldTop;
        head.pre = node;
    }

    public void pop() {
        LinkedNode top = head.pre;
        head.pre = top.pre;
    }

    public int top() {
        return head.pre.value;
    }

    public int getMin() {
        return head.pre.min;
    }
}

