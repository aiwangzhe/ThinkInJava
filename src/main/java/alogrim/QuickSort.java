package alogrim;

public class QuickSort {

    public static void main(String[] args) {
        int []a = new int[] {3, 2, 5, 8, 3, 7, 1, 9};
        sort(a, 0, 7);
        for (int i = 0; i< a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    private static void sort(int a[], int l, int r) {
        if (l >= r) return;
        int p = partition(a, l, r);
        sort(a, l, p - 1); //将分裂点前半部分数组排序
        sort(a, p + 1, r); //将分裂点后半部分数组排序
    }

    private static int partition(int a[], int l, int r) {
        int value = a[l];
        int k = l, m = r + 1;
        while (true) {
            while (a[++k] <= value) {
                if (k == r) break;
            }
            while (a[--m] >= value) {
                if (m == l) break;
            }
            if(k >= m) {
                a[l] = a[m];
                a[m] = value;
                for(int i = l ; i <= r; i++) {
                    System.out.print(a[i] + " ");
                }
                System.out.println();
                return m;
            }else {
                int t = a[k];
                a[k] = a[m];
                a[m] = t;
            }
        }
    }
}
