package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Title21_MergeNode {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(10);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(10);
        System.out.print(new Title21_MergeNode().mergeTwoLists(l1, l2));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newNode = new ListNode();
        ListNode one = newNode;
        while (true) {
            if(l1 != null && l2 != null) {
                if(l1.val < l2.val) {
                    one.next = new ListNode(l1.val);
                    l1 = l1.next;
                }else {
                    one.next = new ListNode(l2.val);
                    l2= l2.next;
                }
                one = one.next;
            }else if(l1 != null) {
                one.next = l1;
                break;
            }else {
                one.next = l2;
                break;
            }
        }
        return newNode.next;
    }
}
