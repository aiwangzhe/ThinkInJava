package objectsize;

import sun.misc.Unsafe;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class TestUnsafeUse {
    static class User{
        int userId;
        int age;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        User user = new User();
        user.userId = 1;
        user.age = 10;
        long valueOffset = getUnsafe().objectFieldOffset(
                user.getClass().getDeclaredField("userId"));
        System.out.println("valueOffset: " + valueOffset);
    }

    private static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe)field.get(null);

        } catch (Exception e) {
        }
        return null;
    }
}
