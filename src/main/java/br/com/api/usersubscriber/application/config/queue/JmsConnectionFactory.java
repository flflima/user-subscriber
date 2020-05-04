package br.com.api.usersubscriber.application.config.queue;

import br.com.api.usersubscriber.application.config.EnvironmentConfig;
import com.rabbitmq.jms.admin.RMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsConnectionFactory {
  private EnvironmentConfig environmentConfig;

  @Autowired
  public JmsConnectionFactory(EnvironmentConfig environmentConfig) {
    this.environmentConfig = environmentConfig;
  }

  @Bean
  public ConnectionFactory rabbitMQConnectionFactory() {
    final RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
    // TODO use environment
    connectionFactory.setUsername("guest");
    connectionFactory.setPassword("guest");
    connectionFactory.setVirtualHost("/");
    connectionFactory.setHost("localhost");
    connectionFactory.setPort(5672);
    return connectionFactory;
  }
}
