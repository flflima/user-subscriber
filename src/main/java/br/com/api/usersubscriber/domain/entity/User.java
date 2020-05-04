package br.com.api.usersubscriber.domain.entity;

import br.com.api.usersubscriber.domain.model.Movie;
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
  private Movie movie = new Movie();
  private boolean notifyMe = true;

  public User() {}

  public User(User that) {
    this(that.getName(), that.getBirthDate(), that.getEmail(), that.getMovie(), that.isNotifyMe());
  }

  public User(User that, Movie movie) {
    this(that.getName(), that.getBirthDate(), that.getEmail(), movie, that.isNotifyMe());
    this.id = that.id;
  }

  public User(String name, String birthDate, String email, Movie movie, boolean notifyMe) {
    this.name = name;
    this.birthDate = birthDate;
    this.email = email;
    this.movie = movie;
    this.notifyMe = notifyMe;
  }

  public User(String name, String birthDate, String email, Movie movie) {
    this.name = name;
    this.birthDate = birthDate;
    this.email = email;
    this.movie = movie;
  }

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
