package br.com.api.usersubscriber.infrastructure.gateway.implementation;

import br.com.api.usersubscriber.application.config.EnvironmentConfig;
import br.com.api.usersubscriber.domain.model.Movie;
import br.com.api.usersubscriber.infrastructure.gateway.MovieGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieGatewayImpl implements MovieGateway {

  private final RestTemplate restTemplate;
  private final EnvironmentConfig environmentConfig;

  @Autowired
  public MovieGatewayImpl(RestTemplate restTemplate, EnvironmentConfig environmentConfig) {
    this.restTemplate = restTemplate;
    this.environmentConfig = environmentConfig;
  }

  @Override
  public Movie getRandomMovie() {
    final Movie movie =
        this.restTemplate.getForObject(environmentConfig.getUrlMovieRandom(), Movie.class);
    return movie;
  }
}
