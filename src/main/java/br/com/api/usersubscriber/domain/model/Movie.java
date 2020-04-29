package br.com.api.usersubscriber.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
public class Movie {
  private String title;
  private String ptTitle;
  private String year;

  public Movie() {}

  public Movie(String title, String ptTitle, String year) {
    this.title = title;
    this.ptTitle = ptTitle;
    this.year = year;
  }
}
