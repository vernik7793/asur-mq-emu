package com.stdpr.asur.mq.emu.logic;

import org.apache.activemq.artemis.api.core.ActiveMQException;
import org.apache.activemq.artemis.api.core.QueueConfiguration;
import org.apache.activemq.artemis.api.core.client.*;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

public class MyConsumer {
    private final ClientSession clientSession;
    private final String queueInName = "Asur.Isur.9000336.CoorV6.1.Input";

    public MyConsumer(ClientSession clientSession) {
        this.clientSession = clientSession;
    }

    public void consume() throws ActiveMQException {
        try (ClientConsumer consumer = clientSession.createConsumer(queueInName)) {
            ClientMessage msgReceived = consumer.receive();
            if (msgReceived != null) {
                System.out.println("message = " + msgReceived.getBodyBuffer().readString());
            }

        }

    }

    @Scheduled(fixedRate=1000)
    public void initConsume() throws ActiveMQException {
        consume();
    }
}
