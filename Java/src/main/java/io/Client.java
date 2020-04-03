package io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
            SocketAddress address = new InetSocketAddress("127.0.0.1", 8080);
            socket.connect(address);
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter outWriter = new OutputStreamWriter(out);
            outWriter.write("haha, haha, haha\n");
            outWriter.flush();
            out.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
