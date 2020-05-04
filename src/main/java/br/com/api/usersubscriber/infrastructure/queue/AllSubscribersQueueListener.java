package br.com.api.usersubscriber.infrastructure.queue;

import br.com.api.usersubscriber.domain.entity.User;
import br.com.api.usersubscriber.domain.extensions.JsonExtension;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;
import java.util.List;

public class AllSubscribersQueueListener implements MessageListener {

  private JsonExtension jsonExtension;
//  private UserService userService;

  public AllSubscribersQueueListener(JsonExtension jsonExtension/*, UserService userService*/) {
    this.jsonExtension = jsonExtension;
//    this.userService = userService;
  }

  @Override
  public void onMessage(Message message) {
    System.out.println("On message...");
    TextMessage textMessage = (TextMessage) message;
    try {
      final List<User> users = jsonExtension.toObjectList(textMessage.getText(), User.class);

      users.forEach(
          user -> {
            System.out.println(" received " + user);
//            userService.updateUserMovie(user);
          });
    } catch (JMSException | IOException e) {
      e.printStackTrace();
    }
  }
}
