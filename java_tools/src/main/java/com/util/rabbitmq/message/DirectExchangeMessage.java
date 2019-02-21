package com.util.rabbitmq.message;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;

import java.io.IOException;

public class DirectExchangeMessage implements BaseMessage {

    //队列名称
    private final static String EXCHANGE_NAME = "direct_logs";

    //通道
    private Channel channel;

    private String[] routingKeys;

    private String queue;

    //消费者
    private Consumer consumer;

    public DirectExchangeMessage(Channel channel, String[] routingKeys, Consumer consumer) throws IOException {
        this.channel = channel;
        this.routingKeys = routingKeys;
        this.consumer = consumer;
        //声明交换机
        //fanout表示分发，所有的消费者得到同样的队列信息
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //产生一个随机队列
        queue = channel.queueDeclare().getQueue();
        //根据路由关键字绑定队列
        for (String s : routingKeys) {
            channel.queueBind(queue, EXCHANGE_NAME, s);
            System.out.println(EXCHANGE_NAME + " exchange:" + EXCHANGE_NAME + "," +
                    " queue:" + queue + ", BindRoutingKey:" + s);
        }
    }

    @Override
    public String consume(boolean autoAck) throws IOException {
        String consume = channel.basicConsume(queue, autoAck, consumer);
        return consume;
    }

    @Override
    public void producer() throws IOException {
        for (String routingKey : routingKeys) {
            String message = "RoutingSendDirect Send the message level:" + routingKey;
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
            System.out.println("RoutingSendDirect Send" + routingKey + "':'" + message);
        }
    }
}
