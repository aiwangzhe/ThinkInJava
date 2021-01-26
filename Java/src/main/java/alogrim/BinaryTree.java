package alogrim;

public class BinaryTree {
    private Node root = null;

    static class Node {
        int data;
        Node lchild;
        Node rchild;
    }

    public BinaryTree(int rootData) {
        this.root = new Node();
        root.data = rootData;
    }

    public Node insert(Node parent, int data, boolean isLeft) throws Exception {
        Node newNode = new Node();
        newNode.data = data;
        if (isLeft && parent.lchild != null) {
            throw new Exception("left child node is not empty");
        }else if(!isLeft && parent.rchild != null) {
            throw new Exception("right child node is not empty");
        }else {
            if (isLeft) {
                parent.lchild = newNode;
            }else {
                parent.rchild = newNode;
            }
        }
        return newNode;
    }

    public void traverse() {
        traverse(root);
    }

    private void traverse(Node node) {
        if (node == null) {
            return;
        }else {
            System.out.print(node.data + "\t");
            traverse(node.lchild);
            traverse(node.rchild);
        }
    }

    public static void main(String[] args) throws Exception {
        BinaryTree binaryTree = new BinaryTree(10);
        Node l1Node = binaryTree.insert(binaryTree.root, 5, true);
        Node rNode = binaryTree.insert(binaryTree.root, 8, false);
        binaryTree.traverse();
    }
}
