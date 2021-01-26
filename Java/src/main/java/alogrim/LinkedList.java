package alogrim;

public class LinkedList {
    private Node head;

    static class Node {
        int data;
        Node next;
    }

    public void insert(int data) {
        Node node = new Node();
        node.data = data;
        if (head == null) {
            head = node;
        }else {
            Node prev = head;
            Node next = head.next;
            while (next != null) {
                prev = next;
                next = next.next;
            }
            prev.next = node;
        }
    }

    public void reserve() {
        Node currentNode = head;
        Node nextNode = head.next;
        Node tmp;
        while (nextNode != null) {
            tmp = nextNode.next;
            nextNode.next = currentNode;
            currentNode = nextNode;
            nextNode = tmp;
        }
        head.next = null;
        head = currentNode;
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insert(1);
        linkedList.insert(20);
        linkedList.insert(30);
        linkedList.reserve();
        System.out.println(linkedList);
    }

    @Override
    public String toString() {
        String result = "";
        Node node = head;
        while (node != null) {
            result += node.data + " ";
            node = node.next;
        }
        return result;
    }
}
