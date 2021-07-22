package leetcode;

public class Title24_SwapPairs {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(10);
        l1.next = l2;
        ListNode l3 = new ListNode(5);
        l2.next = l3;

        //ListNode l2 = new ListNode(5);
       // l2.next = new ListNode(10);
        System.out.print(new Title24_SwapPairs().swapPairs(l1));
    }

    public ListNode swapPairs(ListNode head) {
        ListNode prevNode = new ListNode(-2);
        ListNode currentNode = new ListNode(-1);
        ListNode newNode1 = new ListNode(-1);
        ListNode newNode = newNode1;
        currentNode.next = head;
        while (currentNode.next != null && currentNode.next.next != null) {
            currentNode = currentNode.next;
            prevNode.next = currentNode;
            prevNode = prevNode.next;
            currentNode = currentNode.next;

            ListNode i1 = new ListNode(currentNode.val);
            i1.next = new ListNode(prevNode.val);
            newNode.next = i1;
            newNode = newNode.next;

            prevNode.next = currentNode;
            prevNode = prevNode.next;
        }
        return newNode1.next;
    }
}
