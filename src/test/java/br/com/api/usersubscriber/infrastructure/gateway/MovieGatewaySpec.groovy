package br.com.api.usersubscriber.infrastructure.gateway

import br.com.api.usersubscriber.UserSubscriberApplication
import br.com.api.usersubscriber.domain.model.Movie
import br.com.api.usersubscriber.infrastructure.gateway.implementation.MovieGatewayImpl
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration(classes = UserSubscriberApplication.class)
class MovieGatewaySpec extends Specification {

    @Autowired
    RestTemplate restTemplate = Mock(RestTemplate)

    void "must return a movie"() {
        given: "a request to a movie gateway."
        MovieGateway movieGateway = new MovieGatewayImpl(restTemplate)

        when: "a movie is returned"
        Movie movie = movieGateway.getRandomMovie()

        then: "it must be a valid non empty movie"
        restTemplate.getForObject("http://localhost:3000/movies/random", Movie.class) >> new Movie("Movie test", "Test", "2020")

        verifyAll {
            movie != null
            movie.getTitle() == "Movie test"
            movie.getPtTitle() == "Test"
            movie.getYear() == "2020"
        }
    }
}
