package br.com.api.usersubscriber.infrastructure.gateway


import br.com.api.usersubscriber.infrastructure.gateway.implementation.MovieGatewayImpl
import spock.lang.Specification

class MovieGatewaySpec extends Specification {
    void "must return a movie"() {
        given: "a request to a movie gateway."
        MovieGateway movieGateway = new MovieGatewayImpl()

        when: "a movie is returned"
        String movie = movieGateway.getRandomMovie()

        then: "it must be a valid non empty text"
        movie != ""
        movie != null
    }
}
