package br.com.api.usersubscriber.infrastructure.gateway.implementation;

import br.com.api.usersubscriber.UserSubscriberApplication;
import br.com.api.usersubscriber.domain.model.Movie;
import br.com.api.usersubscriber.infrastructure.gateway.MovieGateway;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Matchers;
import org.mockito.internal.invocation.ArgumentMatcherAction;
import org.mockito.internal.invocation.MockitoMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import static com.mongodb.client.model.Filters.eq;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = UserSubscriberApplication.class)
class MovieGatewayImplTest {

  RestTemplate restTemplate = mock(RestTemplate.class);

  @Test
  void
      given_a_request_to_a_random_movie_when_a_movie_is_returned_then_it_must_be_valid_and_non_empty() {
    when(restTemplate.getForObject(anyString(), any()))
        .thenReturn(new Movie("Movie test", "Test", "2020"));

    final MovieGateway movieGateway = new MovieGatewayImpl(restTemplate);

    final Movie movie = movieGateway.getRandomMovie();

    assertThat(movie).isNotNull();
    assertThat(movie.getTitle()).isEqualTo("Movie test");
    assertThat(movie.getPtTitle()).isEqualTo("Test");
    assertThat(movie.getYear()).isEqualTo("2020");
  }
}
