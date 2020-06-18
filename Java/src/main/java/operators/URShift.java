package operators;//: operators/URShift.java
// Test of unsigned right shift.
import net.mindview.util.Print;

import static net.mindview.util.Print.*;

public class URShift {
  public static void main(String[] args) {
    int i = -1;
    Print.print(Integer.toBinaryString(i));
    i >>>= 10;
    Print.print(Integer.toBinaryString(i));
    long l = -1;
    Print.print(Long.toBinaryString(l));
    l >>>= 10;
    Print.print(Long.toBinaryString(l));
    short s = -1;
    Print.print(Integer.toBinaryString(s));
    s >>>= 10;
    Print.print(Integer.toBinaryString(s));
    byte b = -1;
    Print.print(Integer.toBinaryString(b));
    b >>>= 10;
    Print.print(Integer.toBinaryString(b));
    b = -1;
    Print.print(Integer.toBinaryString(b));
    Print.print(Integer.toBinaryString(b>>>10));
  }
} /* Output:
11111111111111111111111111111111
1111111111111111111111
1111111111111111111111111111111111111111111111111111111111111111
111111111111111111111111111111111111111111111111111111
11111111111111111111111111111111
11111111111111111111111111111111
11111111111111111111111111111111
11111111111111111111111111111111
11111111111111111111111111111111
1111111111111111111111
*///:~
