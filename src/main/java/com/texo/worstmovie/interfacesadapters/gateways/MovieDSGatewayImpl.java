package com.texo.worstmovie.interfacesadapters.gateways;

import com.texo.worstmovie.dtos.MovieDto;
import com.texo.worstmovie.interfacesadapters.repositories.Movie;
import com.texo.worstmovie.interfacesadapters.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MovieDSGatewayImpl implements MovieDSGateway {
    private final MovieRepository movieRepository;

    private final Function<Movie, MovieDto> movieModelToDto;

    public MovieDSGatewayImpl(final MovieRepository movieRepository, final Function<Movie, MovieDto> movieModelToDto) {
        this.movieRepository = movieRepository;
        this.movieModelToDto = movieModelToDto;
    }

    @Override
    public Collection<MovieDto> findAllByWonAwardsIsTrue() {
        return movieRepository.findAllByWonAwardsIsTrue().stream().map(movieModelToDto).collect(Collectors.toList());
    }
}
