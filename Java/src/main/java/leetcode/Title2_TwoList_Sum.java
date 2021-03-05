package leetcode;

import utils.ArrayUtil;

import java.util.HashMap;
import java.util.Map;

public class Title2_TwoList_Sum {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
    public static void main(String[] args) {
        ListNode node1 = new ListNode(2, new ListNode(5));
        ListNode node2 = new ListNode(8);
        System.out.println(addTwoNumbers(node1, node2));
    }

    private static ListNode addTwoNumbers(ListNode node1, ListNode node2) {
        ListNode headNode = null;
        ListNode node = null;
        int addOne = 0;
        do {
            int node1Val = 0;
            int node2Val = 0;
            if( node1 != null) {
                node1Val = node1.val;
            }
            if( node2 != null) {
                node2Val = node2.val;
            }

            int sum = node1Val + node2Val + addOne;
            int newVal = sum < 10 ? sum : sum - 10;

            if (node == null) {
                headNode = new ListNode();
                headNode.val = newVal;
                node = headNode;
            }else {
                ListNode newNode = new ListNode();
                newNode.val = newVal;
                node.next = newNode;
                node = newNode;
            }

            addOne = sum < 10 ? 0 : 1;
            if (node1 != null)
                node1 = node1.next;
            if (node2 != null)
                node2 = node2.next;
        }while (node1 != null || node2 != null);
        if (addOne == 1) {
            ListNode newOne = new ListNode(addOne);
            node.next = newOne;
        }
        return headNode;
    }
}
