package serial;

import java.io.*;

public class BasicObjectIO {

    static class People implements Serializable {
        private static final long serialVersionUID = 1000L;

        private int id;
        private String name;

        public People(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "People{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        //        private void writeObject(ObjectOutputStream out) {
//            System.out.println("i was invoked!");
//        }
    }

    public static void main(String[] args) {
        People people = new People(2, "haha");
        saveObj(people);
        readObj();
    }

    private static void saveObj(Object obj) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("obj.out"));
            out.writeObject(obj);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readObj() {
        try {
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(new FileInputStream("obj.out"));
            Object obj = objectInputStream.readObject();
            System.out.println(obj);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


