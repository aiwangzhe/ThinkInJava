package nio;

import java.nio.ByteBuffer;

public class ByteBufferTest {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        byte[] data = new byte[]{'b', 'a', 'h', 'e', '3', 'e'};
        byteBuffer.put(data);
        byteBuffer.flip();
        byte[] dest = new byte[6];
        byteBuffer.get(dest);
        System.out.println(dest);
    }
}
