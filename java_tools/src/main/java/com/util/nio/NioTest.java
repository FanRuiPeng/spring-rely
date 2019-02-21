package com.util.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Random;
import java.util.stream.Stream;

public class NioTest {
    public static InetSocketAddress HOST_ADDRESS = new InetSocketAddress("127.0.0.1", 8080);

    public static void main(String args[]) throws IOException {
        Selector selector = Selector.open();
        System.out.println("Selector is open for making connection: " + selector.isOpen());

        ServerSocketChannel SS = ServerSocketChannel.open();
        SS.socket().bind(HOST_ADDRESS);
        SS.configureBlocking(false);
        int ops = SS.validOps();
        while (true) {
            SocketChannel accept = SS.accept();
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            if (null != accept) {
                accept.read(allocate);
                for (int i = 0; i < allocate.limit(); i++) {
                    System.out.print((char) allocate.get(i));
                }
        }
    }
//        SelectionKey selectionKey = SS.register(selector, ops, null);
//        for (; ; ) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            int select = selector.select();
//            if (select == 0) continue;
//            Set<SelectionKey> selectionKeys = selector.selectedKeys();
//            Iterator<SelectionKey> iterator = selectionKeys.iterator();
//            while (iterator.hasNext()) {
//                SelectionKey key = iterator.next();
//                if(!key.isValid()) continue;
//                if (key.isConnectable()) {
//                    System.out.println("connect");
//                } else if (key.isReadable()) {
//                    System.out.println("read");
//                    SocketChannel clientChannel = (SocketChannel) key.channel();
//                    ByteBuffer allocate = ByteBuffer.allocate(256);
//                    clientChannel.read(allocate);
////                    while (clientChannel.read(allocate) != -1) {
////                        allocate.flip();
////                        while (allocate.hasRemaining()) {
//
//                    for (int i = 0; i < allocate.limit(); i++) {
//                        System.out.print((char) allocate.get(i));
//                    }
////                        }
////                        allocate.compact();
////                    }
//                } else if (key.isAcceptable()) {
//                    System.out.println("accept");
//                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
//                    //接受
//                    channel.accept()
//                            //非阻塞
//                            .configureBlocking(false)
//                            //z注册读写
//                            .register(selector, SelectionKey.OP_READ);
//                    System.out.println("accept finish");
//                } else if (key.isWritable()) {
//                    System.out.println("write");
//                    SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
//                    ByteBuffer sendBuf = ByteBuffer.allocate(256);
//                    String sendText = "hello\n";
//                    sendBuf.put(sendText.getBytes());
//                    sendBuf.flip();
//                    clientChannel.write(sendBuf);
//
//                }
//                iterator.remove();
//                System.out.println("removed" + selectionKeys.size());
//            }
//        }


//        combainStream();
//        banchOPeration();
//        bufferReader();
//        read();
}

    private static void combainStream() throws IOException {
        String[] strings = {"C:\\Users\\BMF\\Desktop\\data.txt", "C:\\Users\\BMF\\Desktop\\idea激活码.txt"};
        File file = new File("C:\\Users\\BMF\\Desktop\\combain.txt");
        if (!file.exists()) {
            boolean newFile = file.createNewFile();
            if (newFile) {
                try (FileChannel channel = new FileOutputStream(file).getChannel()) {
                    Stream.of(strings)
                            .forEach(m -> {
                                try (FileChannel channel1 = new FileInputStream(m).getChannel()) {
                                    long l = channel1.transferTo(0, channel1.size(), channel);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                }
            }
        }
    }

    private static void banchOPeration() throws IOException {
        String data = "Scattering and Gathering example shown in yiibai.com";
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(400);
        Random random = new Random();
        byteBuffer.asIntBuffer().put(random.nextInt(100));
        byteBuffer1.asCharBuffer().put(data);
        //写入
        FileChannel fileChannel = new FileOutputStream("C:\\Users\\BMF\\Desktop\\data.txt").getChannel();
        fileChannel.write(new ByteBuffer[]{byteBuffer, byteBuffer1});
        //读取
        fileChannel = new FileInputStream("C:\\Users\\BMF\\Desktop\\data.txt").getChannel();
        fileChannel.read(new ByteBuffer[]{byteBuffer, byteBuffer1});
        byteBuffer.rewind();
        byteBuffer1.rewind();
        int i = byteBuffer.asIntBuffer().get();
        String s = byteBuffer1.asCharBuffer().toString();
        System.out.println(i);
        System.out.println(s);
    }

    private static void bufferReader() throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\BMF\\Desktop\\idea激活码.txt");
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
            bufferedReader.lines()
                    .forEach(System.out::println);
        }
    }

    private static void read() throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\BMF\\Desktop\\idea激活码.txt");
             FileChannel inputStreamChannel = fileInputStream.getChannel();
             FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\BMF\\Desktop\\idea激活码copy.txt");
             FileChannel outputStreamChannel = fileOutputStream.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            while (inputStreamChannel.read(byteBuffer) != -1) {
                //从写模式转换到读模式
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {

                    for (int i = 0; i < byteBuffer.limit(); i++) {
                        System.out.print((char) byteBuffer.get(i));
                    }
//                    outputStreamChannel.write(byteBuffer);
                }
                //清空缓存区
//                byteBuffer.clear();
                //清除已经读过的数据，任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面
                byteBuffer.compact();
                //将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素
//                byteBuffer.rewind();
            }
        }
    }
}
