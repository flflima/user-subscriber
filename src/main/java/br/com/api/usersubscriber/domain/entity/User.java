package br.com.api.usersubscriber.domain.entity;

import de.huxhorn.sulky.ulid.ULID;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
public class User {

    private String id = new ULID().nextULID();
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