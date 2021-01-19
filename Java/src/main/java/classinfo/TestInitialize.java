package classinfo;

public class TestInitialize {
    static int i = 1;

    static {
        i = 0;
        System.out.println(i);
    }

    public static void main(String[] args) {
        System.out.println("test");
    }
}
