package alogrim;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int length = 0;
    private int top = 0;

    public MaxPQ() {
        int length = 16;
        this.length = length;
        this.pq = (Key[]) new Comparable[length];
    }

    public void insert(Key key) {
        pq[++top] = key;
        swim(top);
    }

    private void swim(int n) {
        while (n > 1 && pq[n/2].compareTo(pq[n]) < 0) {
            Object parent = pq[n/2];
            pq[n/2] = pq[n];
            pq[n] = (Key) parent;
            n = n / 2;
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i<= top; i++) {
            sb.append(pq[i]).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<>();
        maxPQ.insert(8);
        maxPQ.insert(4);
        maxPQ.insert(10);
        maxPQ.insert(12);
        maxPQ.insert(3);
        maxPQ.insert(5);
        System.out.println(maxPQ);
    }
}
