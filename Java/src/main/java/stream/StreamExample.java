package stream;

import java.util.Arrays;
import java.util.List;

public class StreamExample {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 4, 6, 9, 10 ,23);
        list.forEach(i -> {
           System.out.println(i);
        });

        testLambda(list, i -> {
            System.out.println("consume: " + i);
        });
    }

    static void testLambda(List<Integer> list, MyConsumer<Integer> consumer) {
        for(int i : list) {
            consumer.consume(i);
        }
    }
}
