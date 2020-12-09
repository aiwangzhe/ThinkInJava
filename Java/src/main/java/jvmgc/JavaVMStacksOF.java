package jvmgc;

/**
 * VM args -Xss256k
 */
public class JavaVMStacksOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStacksOF oom = new JavaVMStacksOF();
        try {
            oom.stackLeak();
        }catch (Throwable e) {
            System.out.println("Stack length:" + oom.stackLength);
            throw e;
        }

    }
}
