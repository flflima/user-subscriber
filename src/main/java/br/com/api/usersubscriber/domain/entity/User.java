package br.com.api.usersubscriber.domain.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
public class User {

    private String id = UUID.randomUUID().toString();
    private String name;
    private String birthDate;
    private String email;
    private boolean notifyMe = true;

    public User(String name, String birthDate, String email, boolean notifyMe) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.notifyMe = notifyMe;
    }

    public User(String name, String birthDate, String email) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
    }
}