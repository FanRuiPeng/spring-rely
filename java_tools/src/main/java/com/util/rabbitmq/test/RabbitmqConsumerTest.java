package com.util.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.util.rabbitmq.RabbitmqFactory;
import com.util.rabbitmq.consumer.DefaultConsumer;
import com.util.rabbitmq.message.BaseMessage;
import com.util.rabbitmq.message.DirectExchangeMessage;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitmqConsumerTest {

    public static void main(String args[]) throws IOException, TimeoutException {
        Channel channel = RabbitmqFactory.getChannel();

        BaseMessage message = null;

//        message = new FanoutExchangeMessage(channel);

//        //声明消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel);
//        message = new SimpleMessage(channel, defaultConsumer);

//        message = new FanoutExchangeMessage(channel);

        message = new DirectExchangeMessage(channel, new String[]{"info"}, defaultConsumer);
        message.consume(true);

        DirectExchangeMessage directExchangeMessage = new DirectExchangeMessage(channel, new String[]{"error"}, defaultConsumer);
        directExchangeMessage.consume(true);
    }
}
