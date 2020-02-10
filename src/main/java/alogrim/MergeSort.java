package alogrim;

public class MergeSort {

    public static void main(String[] args) {
        int []a = new int[] {3, 2, 5, 8, 3, 7, 1, 9};
        sort(a, 0, 7);
        for (int i = 0; i< a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    private static void sort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        sort(a, l, mid);
        sort(a, mid + 1, r);
        mergeSort(a, l, mid, r);
    }

    private static void mergeSort(int[] a, int l, int mid, int r) {
        int []aux = new int[a.length];
        if (l == 0 && r == 7) {
            System.out.println("test");
        }

        for (int i = l; i <= r; i++) {
            aux[i] = a[i];
        }
        System.out.print("aux: ");
        for (int i = l; i <= r; i++) {
            System.out.print(aux[i] + " ");
        }
        System.out.println();
        int m = l, j = mid + 1;
        for(int i = l ; i <= r; i++) {
            System.out.println("m: " + m + ", j: " + j);
            if(m > mid) {
                a[i] = aux[j++];
            }else if(j > r) {
                a[i] = aux[m++];
            }else if(aux[m] > aux[j]) {
                a[i] = aux[j++];
            }else {
                a[i] = aux[m++];
            }
        }

    }
}
