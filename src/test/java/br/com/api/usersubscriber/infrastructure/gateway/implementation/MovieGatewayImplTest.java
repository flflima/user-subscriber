package br.com.api.usersubscriber.infrastructure.gateway.implementation;

import br.com.api.usersubscriber.UserSubscriberApplication;
import br.com.api.usersubscriber.application.config.EnvironmentConfig;
import br.com.api.usersubscriber.domain.model.Movie;
import br.com.api.usersubscriber.infrastructure.gateway.MovieGateway;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = UserSubscriberApplication.class)
class MovieGatewayImplTest {

  private final RestTemplate restTemplate = mock(RestTemplate.class);
  private final EnvironmentConfig environmentConfig = mock(EnvironmentConfig.class);

  @Test
  void
      given_a_request_to_a_random_movie_when_a_movie_is_returned_then_it_must_be_valid_and_non_empty() {
    final MovieGateway movieGateway = new MovieGatewayImpl(restTemplate, environmentConfig);

    when(restTemplate.getForObject(anyString(), any()))
        .thenReturn(new Movie("001", "Movie test", "Test", "2020"));
    when(environmentConfig.getUrlMovieRandom()).thenReturn("");

    final Movie movie = movieGateway.getRandomMovie();

    assertThat(movie).isNotNull();
    assertThat(movie.getTitle()).isEqualTo("Movie test");
    assertThat(movie.getPtTitle()).isEqualTo("Test");
    assertThat(movie.getYear()).isEqualTo("2020");
  }

  @Test
  void
      given_a_request_to_a_random_movie_when_no_movie_is_returned_then_it_must_be_valid_and_non_empty() {
    final MovieGateway movieGateway = new MovieGatewayImpl(restTemplate, environmentConfig);

    when(restTemplate.getForObject(anyString(), any())).thenReturn(new Movie());
    when(environmentConfig.getUrlMovieRandom()).thenReturn("");

    final Movie movie = movieGateway.getRandomMovie();

    assertThat(movie).isNotNull();
    assertThat(movie.getTitle()).isEqualTo("");
    assertThat(movie.getPtTitle()).isEqualTo("");
    assertThat(movie.getYear()).isEqualTo("");
  }
}
