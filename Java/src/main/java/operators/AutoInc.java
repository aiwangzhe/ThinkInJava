package operators;//: operators/AutoInc.java
// Demonstrates the ++ and -- operators.
import net.mindview.util.Print;

import static net.mindview.util.Print.*;

public class AutoInc {
  public static void main(String[] args) {
    int i = 1;
    Print.print("i : " + i);
    Print.print("++i : " + ++i); // Pre-increment
    Print.print("i++ : " + i++); // Post-increment
    Print.print("i : " + i);
    Print.print("--i : " + --i); // Pre-decrement
    Print.print("i-- : " + i--); // Post-decrement
    Print.print("i : " + i);
  }
} /* Output:
i : 1
++i : 2
i++ : 2
i : 3
--i : 2
i-- : 2
i : 1
*///:~
