package br.com.api.usersubscriber.infrastructure.queue.implementation;

import br.com.api.usersubscriber.application.config.EnvironmentConfig;
import br.com.api.usersubscriber.infrastructure.queue.QueueProducer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

public class QueueProducerImpl implements QueueProducer {

    private Session session;
    private EnvironmentConfig environmentConfig;
    private Queue queue;

    @Autowired
    public QueueProducerImpl(Session session, EnvironmentConfig environmentConfig) throws Exception {
        this.session = session;
        this.environmentConfig = environmentConfig;

        //TODO queue from config
        try {
            this.queue = session.createQueue("customerQueue");
        } catch (JMSException e) {
            //TODO add logger
            e.printStackTrace();
            //TODO throw exception
            throw new Exception();
        }
    }

    @Override
    public boolean send(String payload) {
        try {
            final Message msg = session.createTextMessage(payload);
            MessageProducer producer = session.createProducer(queue);
            System.out.println("Sending text '" + payload + "'");
            producer.send(msg);
        } catch (JMSException e) {
            e.printStackTrace();
            //TODO add logger
        }

        return false;
    }
}
