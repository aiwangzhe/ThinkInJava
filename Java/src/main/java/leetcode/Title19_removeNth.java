package leetcode;

public class Title19_removeNth {
    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        ListNode two = new ListNode(3);
        ListNode three = new ListNode(4);
        ListNode four = new ListNode(5);
        head.next = two;
        two.next = three;
        three.next = four;

        ListNode newNode = new Title19_removeNth().removeNthFromEnd(head, 3);
        System.out.println(newNode);
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode one = head;
        ListNode virtualNode = new ListNode();
        virtualNode.next = head;
        ListNode prev = virtualNode;
        ListNode toBeleted = prev.next;
        ListNode ater = toBeleted.next;
        int index = 0;
        while (one.next != null) {
            one = one.next;
            index++;
            if(index - n >= 0) {
                prev = prev.next;
                toBeleted = prev.next;
                ater = toBeleted.next;
            }
        }
        if(toBeleted == head) {
            return ater;
        }else {
            prev.next = ater;
            return head;
        }
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next;
      }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
