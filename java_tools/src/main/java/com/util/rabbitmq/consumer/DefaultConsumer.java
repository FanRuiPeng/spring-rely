package com.util.rabbitmq.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DefaultConsumer extends com.rabbitmq.client.DefaultConsumer {

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public DefaultConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleCancelOk(String consumerTag) {
        super.handleCancelOk(consumerTag);
        System.out.println("handleCancelOk: " + consumerTag);
    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {
        super.handleCancel(consumerTag);
        System.out.println("handleCancel: " + consumerTag);
    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
        super.handleShutdownSignal(consumerTag, sig);
        System.out.println("handleShutdownSignal: " + consumerTag + sig.getMessage());
    }

    @Override
    public void handleRecoverOk(String consumerTag) {
        super.handleRecoverOk(consumerTag);
        System.out.println("handleRecoverOk: " + consumerTag);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        super.handleDelivery(consumerTag, envelope, properties, body);
        System.out.println("handleDelivery: " + consumerTag);
        String message = new String(body, StandardCharsets.UTF_8);
        System.out.println(message);
        try {
//            throw new IOException();
        } catch (Exception e) {
            getChannel().abort();
        } finally {
            getChannel().basicAck(envelope.getDeliveryTag(), false);
        }
    }
}