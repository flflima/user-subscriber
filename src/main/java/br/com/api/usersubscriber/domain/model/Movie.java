package br.com.api.usersubscriber.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
public class Movie {

  @ApiModelProperty(notes = "The Movie generated ID", example = "01E7ERKPTJTPYW7PPPBHRWBRVG")
  private String id = "";

  @ApiModelProperty(notes = "The Movie's title", example = "My movie")
  private String title = "";

  @ApiModelProperty(notes = "The Movie's portuguese title", example = "Meu filme")
  private String ptTitle = "";

  @ApiModelProperty(notes = "The Movie's release year", example = "2019")
  private String year = "";

  public Movie() {}

  public Movie(String id, String title, String ptTitle, String year) {
    this.id = id;
    this.title = title;
    this.ptTitle = ptTitle;
    this.year = year;
  }
}
