import java.util.HashSet;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        int ALength = countLength(headA);
        int BLength = countLength(headB);

        ListNode currentA = headA;
        ListNode currentB = headB;

        if(ALength > BLength) {
            for (int i = 0; i < ALength-BLength; i++) {
                headA = headA.next;
            }
        }
        if(BLength > ALength) {
            for (int i = 0; i < BLength-ALength; i++) {
                headB = headB.next;
            }
        }
        while (headA != headB) {
            headA=headA.next;
            headB=headB.next;
        }
        return headA;
    }

    private int countLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
}