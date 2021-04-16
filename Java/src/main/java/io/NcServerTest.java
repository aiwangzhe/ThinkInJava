package io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class NcServerTest {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            try{
                int i = 0;
                while (true) {
                    if(i > 25) {
                        i = 0;
                    }
                    Socket socket = serverSocket.accept();
                    OutputStream out = socket.getOutputStream();
                    out.write((char) (97 + i));
                    out.write('\n');
                    out.flush();
                    Thread.sleep(1000);
                    i++;
                }
            }catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
