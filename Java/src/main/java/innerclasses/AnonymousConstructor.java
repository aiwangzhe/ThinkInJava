package innerclasses;//: innerclasses/AnonymousConstructor.java
// Creating a constructor for an anonymous inner class.
import net.mindview.util.Print;

import static net.mindview.util.Print.*;

abstract class Base {
  public Base(int i) {
    Print.print("Base constructor, i = " + i);
  }
  public abstract void f();
}	

public class AnonymousConstructor {
  public static Base getBase(int i) {
    return new Base(i) {
      { Print.print("Inside instance initializer"); }
      public void f() {
        Print.print("In anonymous f()");
      }
    };
  }
  public static void main(String[] args) {
    Base base = getBase(47);
    base.f();
  }
} /* Output:
Base constructor, i = 47
Inside instance initializer
In anonymous f()
*///:~
