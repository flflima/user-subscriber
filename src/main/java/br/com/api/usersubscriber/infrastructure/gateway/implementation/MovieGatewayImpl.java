package br.com.api.usersubscriber.infrastructure.gateway.implementation;

import br.com.api.usersubscriber.domain.model.Movie;
import br.com.api.usersubscriber.infrastructure.gateway.MovieGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieGatewayImpl implements MovieGateway {

  private RestTemplate restTemplate;

  @Autowired
  public MovieGatewayImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public Movie getRandomMovie() {
    final Movie movie =
        this.restTemplate.getForObject("http://localhost:3000/movies/random", Movie.class);
    System.out.println(this.restTemplate.getForObject("http://localhost:3000/movies/random", Movie.class));
    System.out.println(movie);
    return movie;
  }
}
