package io;

import java.io.*;

public class FileInputStreamTest {

    public static void main(String[] args) throws IOException {
        File file = new File("/home/wangzhe/wangzw21.id");
        FileInputStream fis = new FileInputStream(file);
//        Reader reader = new FileReader();
//        BufferedReader bufferedReader = new BufferedReader(reader);
//        bufferedReader.read
//        FileDescriptor fd = fis.getFD();
//        FileOutputStream fos = new FileOutputStream("sdf");
    }
}
