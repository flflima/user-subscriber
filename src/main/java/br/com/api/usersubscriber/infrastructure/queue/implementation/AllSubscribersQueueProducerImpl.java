package br.com.api.usersubscriber.infrastructure.queue.implementation;

import br.com.api.usersubscriber.application.config.EnvironmentConfig;
import br.com.api.usersubscriber.infrastructure.queue.QueueProducer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

public class AllSubscribersQueueProducerImpl implements QueueProducer {

  private Session session;
  private EnvironmentConfig environmentConfig;
  private Queue queue;

  public AllSubscribersQueueProducerImpl(Session session, EnvironmentConfig environmentConfig, Queue queue)
      throws Exception {
    this.session = session;
    this.environmentConfig = environmentConfig;
    this.queue = queue;
    System.out.println(session);
//
//    // TODO queue from config
//    try {
//      this.queue = session.createQueue("all-subscribers");
//    } catch (JMSException e) {
//      // TODO add logger
//      e.printStackTrace();
//      // TODO throw exception
//      throw new Exception();
//    }
  }

  @Override
  public boolean send(String payload) {
    try {
      final Message msg = session.createTextMessage(payload);
      MessageProducer producer = session.createProducer(queue);
      System.out.println("Sending text '" + payload + "'");
      producer.send(msg);
      return true;
    } catch (JMSException e) {
      e.printStackTrace();
      // TODO add logger
    }

    return false;
  }
}
