package br.com.api.usersubscriber.infrastructure.queue;

import br.com.api.usersubscriber.domain.entity.User;
import br.com.api.usersubscriber.domain.extensions.JsonExtension;
import br.com.api.usersubscriber.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class QueueManager {
  private JmsTemplate jmsTemplate;
  private JsonExtension jsonExtension;
  private UserService userService;

  @Autowired
  public void setJmsTemplate(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  @Autowired
  public void setJsonExtension(JsonExtension jsonExtension) {
    this.jsonExtension = jsonExtension;
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  public boolean sendMessage(String payload) {
    jmsTemplate.convertAndSend("all-subscribers", payload);
    return true;
  }

  @JmsListener(destination = "all-subscribers")
  protected void receiveMessage(String message) throws Exception {
    try {
      final List<User> users = jsonExtension.toObjectList(message, User.class);

      users.forEach(
          user -> {
            System.out.println("received " + user);
            userService.updateUserMovie(user);
          });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
