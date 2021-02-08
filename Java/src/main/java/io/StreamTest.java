package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StreamTest {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream is = new FileInputStream("sfs.txt");
    }
}
