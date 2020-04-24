package br.com.api.usersubscriber.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class POCService {

  @Autowired private JavaMailSender javaMailSender;

  public void sendEmail() {

    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo("flf.felipe@gmail.com");

    msg.setSubject("Testing from Spring Boot");
    msg.setText("Hello World \n Spring Boot Email");

    javaMailSender.send(msg);
  }
}
