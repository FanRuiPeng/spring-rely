package com.util.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static com.util.nio.NioTest.HOST_ADDRESS;

public class ClientTest {

    public static void main(String arags[]) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(HOST_ADDRESS);
        socketChannel.configureBlocking(false);
        while (socketChannel.finishConnect()) {
            String newData = "The new String is writing in a file ..." + System.currentTimeMillis();
            ByteBuffer buffer = ByteBuffer.allocate(256);
            buffer.clear();
            buffer.put(newData.getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
        }
        socketChannel.close();
    }

    class InnerClass {
        private String str;


    }
}
