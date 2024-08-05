package com.stdpr.asur.mq.emu;

import com.stdpr.asur.mq.emu.logic.MyConsumer;
import org.apache.activemq.artemis.api.core.QueueConfiguration;
import org.apache.activemq.artemis.api.core.client.*;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class Config {


    @Bean
    public EmbeddedActiveMQ jmsServer() throws Exception {

        EmbeddedActiveMQ jmsServer = new EmbeddedActiveMQ();
        jmsServer.start();

        return jmsServer;
    }


    @Bean
    public ClientSession clientSession(EmbeddedActiveMQ jmsServer) throws Exception {
        ServerLocator serverLocator =  ActiveMQClient.createServerLocator("tcp://127.0.0.1:61616");
        ClientSessionFactory factory =  serverLocator.createSessionFactory();
        ClientSession session = factory.createSession();


        String queueInName = "Asur.Isur.9000336.CoorV6.1.Input";
        session.createQueue(new QueueConfiguration(queueInName));
        //session.createQueue(new QueueConfiguration("Asur.Isur.9000336.CoorV6.1.Input"));

/*        ClientProducer producer = session.createProducer("example");
        ClientMessage message = session.createMessage(true);
        message.getBodyBuffer().writeString("Hello");
        producer.send(message);*/

        session.start();
/*        ClientConsumer consumer = session.createConsumer(queueInName);
        ClientMessage msgReceived = consumer.receive();
        System.out.println("message = " + msgReceived.getBodyBuffer().readString());*/
        //session.close();

        return session;
    }

    @Bean
    public MyConsumer myConsumer(ClientSession clientSession) {
        return new MyConsumer(clientSession);
    }
}