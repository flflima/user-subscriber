package br.com.api.usersubscriber.infrastructure.queue;

import br.com.api.usersubscriber.application.config.EnvironmentConfig;
import br.com.api.usersubscriber.domain.extensions.JsonExtension;
import br.com.api.usersubscriber.infrastructure.queue.implementation.AllSubscribersQueueProducerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

@Component
public class QueueManager {

  private Session session;
  private EnvironmentConfig environmentConfig;
  private Queue queue;

  private JsonExtension jsonExtension;
  //  private UserService userService;
  private QueueProducer allSubscribersProducer;

  @Autowired
  public QueueManager(
      Session session, EnvironmentConfig environmentConfig, JsonExtension jsonExtension /*,
      UserService userService*/)
      throws Exception {
    this.jsonExtension = jsonExtension;
    //    this.userService = userService;
    this.session = session;
    this.environmentConfig = environmentConfig;

    // TODO queue from config
    try {
      this.queue = session.createQueue("all-subscribers");
      this.allSubscribersProducer =
          new AllSubscribersQueueProducerImpl(session, environmentConfig, queue);
      startListeningAllSubscribersQueue();
    } catch (JMSException e) {
      // TODO add logger
      e.printStackTrace();
      // TODO throw exception
      throw new Exception();
    }
  }

  public boolean send(String payload) {
    return this.allSubscribersProducer.send(payload);
  }

  private void startListeningAllSubscribersQueue() throws Exception {
    System.out.println(session);
    // TODO queue from config
    try {
      //      this.queue = session.createQueue("all-subscribers");
      MessageConsumer consumer = session.createConsumer(this.queue);
      System.out.println(consumer);
      consumer.setMessageListener(new AllSubscribersQueueListener(jsonExtension /*, userService*/));
    } catch (JMSException e) {
      // TODO add logger
      e.printStackTrace();
      // TODO throw exception
      throw e;
    }
  }
}
