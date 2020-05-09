package br.com.api.usersubscriber.domain.entity;

import br.com.api.usersubscriber.domain.model.Movie;
import de.huxhorn.sulky.ulid.ULID;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
public class User {

  @ApiModelProperty(notes = "The User generated ID", example = "01E7ERKPTJTPYW7PPPBHRWBRVG")
  private String id = new ULID().nextULID();

  @ApiModelProperty(notes = "The User's name", example = "John Doe")
  private String name;

  @ApiModelProperty(notes = "The User's birth date", example = "1999-01-01")
  private String birthDate;

  @ApiModelProperty(notes = "The User's e-mail", example = "johns@email.com")
  private String email;

  @ApiModelProperty(notes = "The User's suggested movie")
  private Movie movie = new Movie();

  @ApiModelProperty(notes = "If the user will or will not be notified", example = "false")
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
