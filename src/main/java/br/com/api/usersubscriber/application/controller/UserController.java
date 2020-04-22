package br.com.api.usersubscriber.application.controller;

import br.com.api.usersubscriber.domain.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User create() {
        return null;
    }

}