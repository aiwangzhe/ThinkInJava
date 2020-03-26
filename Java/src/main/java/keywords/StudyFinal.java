package keywords;

public class StudyFinal {

    public static void main(String[] args){
//        int i = 3;
//        String a = "hello" + 2 + i;
//        String b = "hello" + 2 + i;
//
//        System.out.println(a == b);

        String a = "hello";
        String b = new String("hello");
        System.out.println(a == b);

        final int i = 2;
        String c = "hello" + i;
        String d = "hello" + i;
        System.out.println(c == d);
    }
}
