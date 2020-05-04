package br.com.api.usersubscriber.application.config.queue;

import com.rabbitmq.client.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Configuration
public class JmsConnection {

  private ConnectionFactory connectionFactory;

  @Autowired
  public JmsConnection(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  @Bean
  public Connection getConnection() throws Exception {
    try {
      return this.connectionFactory.createConnection();
    } catch (JMSException e) {
      // TODO add logger
      e.printStackTrace();
      // TODO add message
      throw new Exception("");
    }
  }
}
