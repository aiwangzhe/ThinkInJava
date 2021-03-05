package utils;

public class ArrayUtil {

    public static void printArray(int[] array) {
        if(array == null || array.length == 0) {
            System.out.println("empty array!");
        }else {
            for(int i = 0 ; i < array.length ; i++) {
                System.out.print(array[i] + "\t");
            }
            System.out.println();
        }
    }
}
