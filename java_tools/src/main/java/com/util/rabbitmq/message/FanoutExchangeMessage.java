package com.util.rabbitmq.message;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.util.rabbitmq.consumer.DefaultConsumer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FanoutExchangeMessage implements BaseMessage {

    private static final String EXCHANGE_NAME = "fanout_logs";
    private String queue;
    //通道
    private Channel channel;

    public FanoutExchangeMessage(Channel channel) throws IOException {
        this.channel = channel;
        //声明交换机
        //fanout表示分发，所有的消费者得到同样的队列信息
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        //产生一个随机队列
        queue = channel.queueDeclare().getQueue();
        //绑定队列
        channel.queueBind(queue, EXCHANGE_NAME, "");
    }

    public void producer() throws IOException {
        String message = "Hello World!";
        for (int i = 0; i < 5; i++) {
            channel.basicPublish(EXCHANGE_NAME, "", null, (message + i).getBytes(StandardCharsets.UTF_8));
        }
    }

    public String consume(boolean autoAck) throws IOException {
        String consume = channel.basicConsume(queue, autoAck, new DefaultConsumer(channel));
        System.out.println(consume + " is ready ");
        return consume;
    }
}
