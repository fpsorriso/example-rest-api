package com.texo.worstmovie.business.entities;

import com.texo.worstmovie.business.usecase.presenter.dtos.ConsecutiveAward;

import java.util.Collection;

public interface ConsecutiveAwardsFactory {
    Collection<ConsecutiveAward> fromMovies(Collection<Movie> movies);
}
