package alogrim;

public class InsertSort {

    public static void main(String[] args) {
        int []a = new int[] {3, 2, 5, 8, 3, 7, 1, 9};
        sort(a, 0, 7);
        for (int i = 0; i< a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    private static void sort(int a[], int l, int r) {
        for(int i = l + 1; i <= r; i++) {
            int value = a[i];
            int p = i - 1;
            while (a[p] > value) {
                a[p + 1] = a[p];
                p--;
                if(p < l) {
                    break;
                }
            }
            a[p+1] = value;
        }
    }
}
