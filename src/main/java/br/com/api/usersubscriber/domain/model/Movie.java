package br.com.api.usersubscriber.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
public class Movie {
  private String id = "";
  private String title = "";
  private String ptTitle = "";
  private String year = "";

  public Movie() {}

  public Movie(String id, String title, String ptTitle, String year) {
    this.id = id;
    this.title = title;
    this.ptTitle = ptTitle;
    this.year = year;
  }
}
