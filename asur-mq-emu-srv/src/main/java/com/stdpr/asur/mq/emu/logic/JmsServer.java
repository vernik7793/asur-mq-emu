package com.stdpr.asur.mq.emu.logic;

import org.apache.activemq.artemis.api.core.QueueConfiguration;
import org.apache.activemq.artemis.api.core.client.*;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.springframework.stereotype.Component;

@Component
public class JmsServer {
    public JmsServer() throws Exception {

        EmbeddedActiveMQ jmsServer = new EmbeddedActiveMQ();
        jmsServer.start();


/*        ServerLocator serverLocator =  ActiveMQClient.createServerLocator("vm://0");
        ClientSessionFactory factory =  serverLocator.createSessionFactory();
        ClientSession session = factory.createSession();

        session.createQueue(QueueConfiguration.of("example"));

        ClientProducer producer = session.createProducer("example");
        ClientMessage message = session.createMessage(true);
        message.getBody().writeString("Hello");
        producer.send(message);

        session.start();
        ClientConsumer consumer = session.createConsumer("example");
        ClientMessage msgReceived = consumer.receive();
        System.out.println("message = " + msgReceived.getBody().readString());
        session.close();*/
    }
}
