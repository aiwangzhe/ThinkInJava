package jvmgc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;

public class Main {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) throws IOException {
//        ServerSocket ss = new ServerSocket();
//        SocketAddress address = new InetSocketAddress(9866);
//        ss.bind(address);
//        while (true){
//            Socket socket = ss.accept();
//            socket.getInputStream();
//        }
        binaryToDecimal(100000000);
        System.out.println(hash(100000000));
        HashMap
    }

    public static void binaryToDecimal(int n){
        for(int i = 31;i >= 0; i--)
            System.out.print(n >>> i & 1);
        System.out.println();
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

}
