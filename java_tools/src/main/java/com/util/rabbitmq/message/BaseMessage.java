package com.util.rabbitmq.message;

import java.io.IOException;

public interface BaseMessage {

    String consume(boolean autoAck) throws IOException;

    void producer() throws IOException;
}
