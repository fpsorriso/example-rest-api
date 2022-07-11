package com.texo.worstmovie.interfacesadapters.gateways;

import com.texo.worstmovie.dtos.MovieDto;

import java.util.Collection;

public interface MovieDSGateway {
    Collection<MovieDto> findAllByWonAwardsIsTrue();
}
