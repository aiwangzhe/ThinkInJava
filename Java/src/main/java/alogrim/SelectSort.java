package alogrim;

import java.util.Arrays;

public class SelectSort {

    public static void main(String[] args) {
        int a[] = {4, 8, 10, 12, 4, 5, 7};
        selectSort(a, 7);
        System.out.println(Arrays.toString(a));
    }

    private static void selectSort(int []a, int length) {
        for(int i = 0 ; i < length - 1; i++) {
            int min = a[i];
            int minIndex = i;
            for(int j = i + 1; j < length; j++) {
                if (min > a[j]) {
                    min = a[j];
                    minIndex = j;
                }
            }
            if (min != a[i]) {
                a[minIndex] = a[i];
                a[i] = min;
            }
        }
    }
}
