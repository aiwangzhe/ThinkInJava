package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerChannel {

    public static void main(String[] args) {
        try {
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.bind(new InetSocketAddress(8080));
            Selector selector = Selector.open();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int result = selector.select(3 * 1000);
                if(result > 0) {
                    System.out.println("code: " + result);
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        System.out.println("key: " + key);
                        iterator.remove();
                        handleKeyEvent(key, selector, channel);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleKeyEvent(SelectionKey key, Selector selector,
                                ServerSocketChannel channel) throws IOException {
        if (key.isAcceptable()) {
            System.out.println("accpeted!");
            SocketChannel socketChannel = channel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }else if(key.isReadable()) {
            System.out.println("connected!");
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer dest = ByteBuffer.allocate(1024);
            socketChannel.read(dest);
            dest.flip();
            byte[] data = new byte[dest.remaining()];
            dest.get(data);
            String str = new String(data);
            System.out.println("data: " + str);
        }
    }
}
