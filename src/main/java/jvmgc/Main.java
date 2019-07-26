package jvmgc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        SocketAddress address = new InetSocketAddress(9866);
        ss.bind(address);
        while (true){
            Socket socket = ss.accept();
            socket.getInputStream();
        }
    }

}
