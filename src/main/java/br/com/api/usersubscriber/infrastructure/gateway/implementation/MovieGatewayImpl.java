package br.com.api.usersubscriber.infrastructure.gateway.implementation;

import br.com.api.usersubscriber.infrastructure.gateway.MovieGateway;
import org.springframework.stereotype.Service;

@Service
public class MovieGatewayImpl implements MovieGateway {
    @Override
    public String getRandomMovie() {
        return "Generic Movie";
    }
}
