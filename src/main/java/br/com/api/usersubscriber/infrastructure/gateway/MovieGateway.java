package br.com.api.usersubscriber.infrastructure.gateway;

import br.com.api.usersubscriber.domain.model.Movie;

public interface MovieGateway {
  Movie getRandomMovie();
}
