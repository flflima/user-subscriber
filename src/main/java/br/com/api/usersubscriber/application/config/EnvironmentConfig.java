package br.com.api.usersubscriber.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentConfig {

  @Autowired private Environment environment;

  public String getUrlMovieRandom() {
    return environment.getProperty("URL_MOVIE_RANDOM");
  }
}
