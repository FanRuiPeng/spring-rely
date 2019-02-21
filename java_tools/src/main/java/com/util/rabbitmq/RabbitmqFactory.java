package com.util.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class RabbitmqFactory {

    private static final ConnectionFactory connectionFactory = new ConnectionFactory();
    //创建连接
    private static Connection connection;
    //创建通道
    private static Channel channel;

    static {
        init();
    }

    private static void init() {
        try {
            connectionFactory.setUri("amqp://bmf:123456@192.168.48.100:5672/bmf_host");
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException | TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (null == connection || !connection.isOpen()) {
            init();
        }
        return connection;
    }

    public static Channel getChannel() {
        if (null == channel || !channel.isOpen()) {
            init();
        }
        return channel;
    }
}
