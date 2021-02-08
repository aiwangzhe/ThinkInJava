package alogrim;

public class QuickSort {

    public static void main(String[] args) {
        int []a = new int[] {3, 2, 5, 8, 3, 7, 1, 9};
        sort(a, 0, 7);
        for (int i = 0; i< a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    private static void sort(int a[], int start, int end) {
        if ( start < end) {
            int split = splitIndex(a, start, end);
            System.out.println("split: " + split);
            sort(a, start, split - 1);
            sort(a, split + 1, end);
        }
    }

    public static int splitIndex(int a[], int start, int end) {
        int val = a[start];
        if (start >= end) {
            return start;
        }
        int i = start;
        int j = end;
        while (i != j) {
            while (a[j] > val && j > i) {
                j--;
            }
            while (a[i] <= val && i < j) {
                i++;
            }
            if(i < j) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        if (i != start) {
            a[start] = a[i];
            a[i] = val;
        }
        return i;
    }
}
