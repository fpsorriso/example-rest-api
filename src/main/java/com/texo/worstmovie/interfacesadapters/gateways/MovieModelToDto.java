package com.texo.worstmovie.interfacesadapters.gateways;

import com.texo.worstmovie.dtos.MovieDto;
import com.texo.worstmovie.interfacesadapters.repositories.Movie;
import com.texo.worstmovie.interfacesadapters.repositories.Producer;
import com.texo.worstmovie.interfacesadapters.repositories.Studio;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MovieModelToDto implements Function<Movie, MovieDto> {
    @Override
    public MovieDto apply(final Movie movie) {
        if (Objects.isNull(movie)) {
            return null;
        }

        final var producers = movie.getProducers().stream().map(Producer::getName).collect(Collectors.toList());
        final var studios = movie.getStudios().stream().map(Studio::getName).collect(Collectors.toList());

        return new MovieDto(movie.getTitle(), movie.getYear(), movie.hasWonAwards(), producers, studios);
    }
}
