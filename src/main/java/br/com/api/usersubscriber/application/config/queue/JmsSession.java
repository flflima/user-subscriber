package br.com.api.usersubscriber.application.config.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

@Configuration
public class JmsSession {
  // TODO getSession

  private Connection connection;

  @Autowired
  public JmsSession(Connection connection) {
    this.connection = connection;
  }

  @Bean
  public Session createSession() throws Exception {
    try {
      return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    } catch (JMSException e) {
      // TODO add logger
      e.printStackTrace();
      // TODO throw exception
      throw new Exception("");
    }
  }
}
