package innerclasses;//: innerclasses/BigEgg.java
// An inner class cannot be overriden like a method.
import net.mindview.util.Print;

import static net.mindview.util.Print.*;

class Egg {
  private Yolk y;
  protected class Yolk {
    public Yolk() { Print.print("Egg.Yolk()"); }
  }
  public Egg() {
    Print.print("New Egg()");
    y = new Yolk();
  }
}	

public class BigEgg extends Egg {
  public class Yolk {
    public Yolk() { Print.print("BigEgg.Yolk()"); }
  }
  public static void main(String[] args) {
    new BigEgg();
  }
} /* Output:
New Egg()
Egg.Yolk()
*///:~
