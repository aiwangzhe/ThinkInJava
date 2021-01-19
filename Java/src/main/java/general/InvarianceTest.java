package general;

import java.util.ArrayList;
import java.util.List;

public class InvarianceTest {
    abstract static class Animal {
        String name;
    }

    static class Cat extends Animal {
        public Cat(String name) {
            this.name = name;
        }
    }

    static class Dog extends Animal {
        public Dog(String name) {
            this.name = name;
        }
    }

    static void printAnimalNames(List<Animal> animals) {
       animals.forEach(animal -> System.out.println(animal.name));
    }

    public static void main(String[] args) {
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("Whiskers"));
        cats.add(new Cat("Tom"));
        List<Dog> dogs= new ArrayList<>();
        dogs.add(new Dog("Fido"));
        dogs.add(new Dog("Rex"));

        // prints: Whiskers, Tom
        //printAnimalNames(cats);
        // prints: Fido, Rex
        //printAnimalNames(dogs);
    }
}
