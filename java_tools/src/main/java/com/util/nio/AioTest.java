package com.util.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AioTest {

    public static void main(String args[]) throws IOException, ExecutionException, InterruptedException {
//        get();
//        call();
//        socketServer();
        socketClient();
    }

    public static void get() throws IOException, ExecutionException, InterruptedException {
        Path path = Paths.get("C:\\Users\\BMF\\Desktop\\idea激活码.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
        ByteBuffer buffer = ByteBuffer.allocate(512);
        CharBuffer charBuffer = CharBuffer.allocate(512);
        read(channel, buffer, charBuffer);
    }

    private static void read(AsynchronousFileChannel channel, ByteBuffer buffer, CharBuffer charBuffer) throws InterruptedException, ExecutionException {
        String result = "";
        Integer readNumber = 0;
        Future<Integer> future = null;
        Integer get = 0;
        do {
            buffer.clear();
            future = channel.read(buffer, readNumber);
            get = future.get();
            buffer.flip();
            charBuffer.clear();
            CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
            decoder.decode(buffer, charBuffer, false);
            charBuffer.flip();
            String s = new String(charBuffer.array(), 0, charBuffer.limit());
            result += s;
            readNumber += get;
        } while (get == buffer.limit());
        System.out.println(" read number : " + readNumber);
        System.out.println(result);
    }

    public static void call() throws IOException, InterruptedException {
        Path path = Paths.get("C:\\Users\\BMF\\Desktop\\idea激活码.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println(Thread.currentThread().getName() + " read success! ");
                try {
                    Thread.sleep(1000);
                    read(channel, buffer, charBuffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("read error!");
            }
        });
        while (true) {
            System.out.println(Thread.currentThread().getName() + " sleep ");
            Thread.sleep(1000);
        }
    }

    public static void socketClient() throws IOException, ExecutionException, InterruptedException {
//        ByteBuffer recive = ByteBuffer.allocate(1024);
//        ByteBuffer send = ByteBuffer.allocate(1024);
//        final boolean[] isDone = {false};
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8000)).get();
//        socketChannel.connect(new InetSocketAddress(8000), null, new CompletionHandler<Void, Object>() {
//            @Override
//            public void completed(Void result, Object attachment) {
//                send.clear();
//                send.put("ping!".getBytes());
//                send.flip();
//                recive.clear();
//                socketChannel.write(send, null, new CompletionHandler<Integer, Object>() {
//                    @Override
//                    public void completed(Integer result, Object attachment) {
//                        try {
//                            Integer integer = socketChannel.read(recive).get();
//                            System.out.println("client get : " + new String(recive.array()));
//                            recive.flip();
//                            isDone[0] = true;
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        } catch (ExecutionException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void failed(Throwable exc, Object attachment) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void failed(Throwable exc, Object attachment) {
//
//            }
//        });
//
//        while (!isDone[0]) {
//            Thread.sleep(1);
//        }
        ByteBuffer byteBuffer = ByteBuffer.wrap("Hello World!".getBytes());
        Future<Integer> future = socketChannel.write(byteBuffer);
        Integer integer = future.get();
        System.out.println(integer);
    }

    public static void socketServer() throws IOException, InterruptedException {
        AsynchronousChannelGroup asynchronousChannelGroup = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(4));
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel
                .open()
                .bind(new InetSocketAddress(8000));
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                ByteBuffer recive = ByteBuffer.allocate(1024);
                ByteBuffer send = ByteBuffer.allocate(1024);
                recive.clear();
                try {
                    Integer integer = result.read(recive).get();
                    System.out.println("get : " + new String(recive.array()));
                    recive.flip();

                    send.clear();
                    send.put("success get!".getBytes());
                    send.flip();
                    result.write(send);
                    //递归调用  监听客户端
                    serverSocketChannel.accept(null, this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("error");
            }
        });
        asynchronousChannelGroup.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
