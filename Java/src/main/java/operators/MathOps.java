package operators;//: operators/MathOps.java
// Demonstrates the mathematical operators.
import net.mindview.util.Print;

import java.util.*;

public class MathOps {
  public static void main(String[] args) {
    // Create a seeded random number generator:
    Random rand = new Random(47);
    int i, j, k;
    // Choose value from 1 to 100:
    j = rand.nextInt(100) + 1;
    Print.print("j : " + j);
    k = rand.nextInt(100) + 1;
    Print.print("k : " + k);
    i = j + k;
    Print.print("j + k : " + i);
    i = j - k;
    Print.print("j - k : " + i);
    i = k / j;
    Print.print("k / j : " + i);
    i = k * j;
    Print.print("k * j : " + i);
    i = k % j;
    Print.print("k % j : " + i);
    j %= k;
    Print.print("j %= k : " + j);
    // Floating-point number tests:
    float u, v, w; // Applies to doubles, too
    v = rand.nextFloat();
    Print.print("v : " + v);
    w = rand.nextFloat();
    Print.print("w : " + w);
    u = v + w;
    Print.print("v + w : " + u);
    u = v - w;
    Print.print("v - w : " + u);
    u = v * w;
    Print.print("v * w : " + u);
    u = v / w;
    Print.print("v / w : " + u);
    // The following also works for char,
    // byte, short, int, long, and double:
    u += v;
    Print.print("u += v : " + u);
    u -= v;
    Print.print("u -= v : " + u);
    u *= v;
    Print.print("u *= v : " + u);
    u /= v;
    Print.print("u /= v : " + u);
  }
} /* Output:
j : 59
k : 56
j + k : 115
j - k : 3
k / j : 0
k * j : 3304
k % j : 56
j %= k : 3
v : 0.5309454
w : 0.0534122
v + w : 0.5843576
v - w : 0.47753322
v * w : 0.028358962
v / w : 9.940527
u += v : 10.471473
u -= v : 9.940527
u *= v : 5.2778773
u /= v : 9.940527
*///:~
