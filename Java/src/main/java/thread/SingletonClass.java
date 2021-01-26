package thread;

public class SingletonClass {
    private static SingletonClass mInstance;

    public static SingletonClass getInstance() {
        if (mInstance == null) {
            synchronized (SingletonClass.class) {
                if(mInstance == null) {
                    mInstance = new SingletonClass();
                }
            }
        }
        return mInstance;
    }
}
