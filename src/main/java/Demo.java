import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashSet;

public class Demo {
    public static void main(String[] args) {
        Solution sol = new Solution();
        HashSet<ListNode> map = new HashSet<>();
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(0);
        ListNode d = new ListNode(-4);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = b;

    }

    static void printf(Object o){
        System.out.println(o);
    }
}
