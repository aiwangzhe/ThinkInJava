package reflect;

import java.util.Random;

public class ClassInitialization {
    public static Random rand = new Random(47);
    public static void main(String[] args) throws ClassNotFoundException {  
        Class initable = Initable.class;  
        System.out.println("after creating Initable ref");  
        //Does not trigger initialization  
        System.out.println(Initable.staticFinal);  
        //Does trigger initialization  
        System.out.println(Initable.staticFianl2);  
        //Does trigger initialization  
        System.out.println(Initable2.staticNonFinal);  
        Class initable3 = Class.forName("reflect.Initable3");
        System.out.println("after creating Initable3 ref");  
        System.out.println(Initable3.staticNonFinal);  
    }  
}