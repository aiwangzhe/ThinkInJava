package operators;//: operators/TernaryIfElse.java
import net.mindview.util.Print;

import static net.mindview.util.Print.*;

public class TernaryIfElse {
  static int ternary(int i) {
    return i < 10 ? i * 100 : i * 10;
  }
  static int standardIfElse(int i) {
    if(i < 10)
      return i * 100;
    else
      return i * 10;
  }
  public static void main(String[] args) {
    Print.print(ternary(9));
    Print.print(ternary(10));
    Print.print(standardIfElse(9));
    Print.print(standardIfElse(10));
  }
} /* Output:
900
100
900
100
*///:~
