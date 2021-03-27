class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node currentNode = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (currentNode != null) {
            next = currentNode.next;

            Node copy = new Node(currentNode.val);
            currentNode.next = copy;
            copy.next = next;

            currentNode = next;
        }

        // Second round: assign random pointers for the copy nodes.
        currentNode = head;
        while (currentNode != null) {
            if (currentNode.random != null) {
                currentNode.next.random = currentNode.random.next;
            }
            currentNode = currentNode.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        currentNode = head;
        Node pseudoHead = new Node(0);
        Node copy;
        pseudoHead.next = head.next;
        while (currentNode != null) {
            copy = currentNode.next;
            currentNode.next = copy.next;
            if(copy.next != null) copy.next = copy.next.next;
            currentNode = currentNode.next;

        }
        return pseudoHead.next;
    }
}