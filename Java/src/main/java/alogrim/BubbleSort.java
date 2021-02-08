package alogrim;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int a[] = {4, 3, 6, 2, 8, 1, 5};
        sort(a, 7);
        System.out.println(Arrays.toString(a));
    }

    private static void sort(int[] arr, int length) {
        for (int i = 0 ; i < length; i++) {
            for(int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr [j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }
}
