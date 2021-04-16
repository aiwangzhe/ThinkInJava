package thread;

public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal =
                new ThreadLocal<String>() {
                    @Override
                    protected String initialValue() {
                        return super.initialValue();
                    }
                };

        threadLocal.set("haha");
    }
}
