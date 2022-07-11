package com.texo.worstmovie.business.usecase;


import com.texo.worstmovie.business.entities.ConsecutiveAwardsFactory;
import com.texo.worstmovie.business.entities.MovieFactory;
import com.texo.worstmovie.business.usecase.presenter.dtos.ConsecutiveAward;
import com.texo.worstmovie.business.usecase.presenter.dtos.ConsecutiveAwardsResponse;
import com.texo.worstmovie.interfacesadapters.gateways.MovieDSGateway;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Function;

@Service
public class GetConsecutiveAwardsByProducerImpl
        implements GetConsecutiveAwardsByProducer {

    private final MovieDSGateway movieDSGateway;

    private final MovieFactory movieFactory;

    private final ConsecutiveAwardsFactory consecutiveAwardsFactory;

    private final Function<Collection<ConsecutiveAward>, ConsecutiveAwardsResponse> consecutiveAwardToResponse;

    public GetConsecutiveAwardsByProducerImpl(final MovieDSGateway movieDSGateway,
                                              final MovieFactory movieFactory,
                                              final ConsecutiveAwardsFactory consecutiveAwardsFactory,
                                              final Function<Collection<ConsecutiveAward>, ConsecutiveAwardsResponse> consecutiveAwardToResponse) {
        this.movieDSGateway = movieDSGateway;
        this.movieFactory = movieFactory;
        this.consecutiveAwardsFactory = consecutiveAwardsFactory;
        this.consecutiveAwardToResponse = consecutiveAwardToResponse;
    }

    @Override
    public ConsecutiveAwardsResponse get() {
        final var movies = movieDSGateway.findAllByWonAwardsIsTrue().stream()
                                         .map(movieFactory::create)
                                         .toList();

        return consecutiveAwardToResponse.apply(consecutiveAwardsFactory.fromMovies(movies));
    }
}
