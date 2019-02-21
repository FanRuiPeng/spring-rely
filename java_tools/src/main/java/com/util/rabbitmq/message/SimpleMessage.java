package com.util.rabbitmq.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SimpleMessage implements BaseMessage {

    //队列名称
    private final static String queueName = "simple_logs";

    //通道
    private Channel channel;
    //消费者
    private Consumer consumer;

    public SimpleMessage(Channel channel, Consumer consumer) throws IOException {
        this.channel = channel;
        this.consumer = consumer;
        //声明一个队列
        channel.queueDeclare(queueName, true, false, false, null);
    }

    /**
     * @param autoAck 是否自动回执
     * @throws IOException
     */
    public String consume(boolean autoAck) throws IOException {
        String consume = channel.basicConsume(queueName, autoAck, consumer);
        System.out.println(consume + " is ready ");
        return consume;
    }

    public void producer() throws IOException {
        //发送消息到队列
        String message = "Hello World!";
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, (message + i).getBytes(StandardCharsets.UTF_8));
        }
    }
}
