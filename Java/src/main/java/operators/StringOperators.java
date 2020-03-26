package operators;//: operators/StringOperators.java
import net.mindview.util.Print;

import static net.mindview.util.Print.*;

public class StringOperators {
  public static void main(String[] args) {
    int x = 0, y = 1, z = 2;
    String s = "x, y, z ";
    Print.print(s + x + y + z);
    Print.print(x + " " + s); // Converts x to a String
    s += "(summed) = "; // Concatenation operator
    Print.print(s + (x + y + z));
    Print.print("" + x); // Shorthand for Integer.toString()
  }
} /* Output:
x, y, z 012
0 x, y, z
x, y, z (summed) = 3
0
*///:~
