package com.texo.worstmovie.business.entities;

import com.texo.worstmovie.business.usecase.presenter.dtos.ConsecutiveAward;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ConsecutiveAwardsFactoryImpl implements ConsecutiveAwardsFactory {

    private final Function<Map.Entry<String, Collection<Movie>>, ConsecutiveAward> mapToConsecutiveAward;

    public ConsecutiveAwardsFactoryImpl(final Function<Map.Entry<String, Collection<Movie>>, ConsecutiveAward> mapToConsecutiveAward) {
        this.mapToConsecutiveAward = mapToConsecutiveAward;
    }

    @Override
    public Collection<ConsecutiveAward> fromMovies(final Collection<Movie> movies) {
        if (CollectionUtils.isEmpty(movies)) {
            return List.of();
        }

        final var mapMovieByProducer = this.mapMovieByProducer(movies);

        return mapMovieByProducer.entrySet().stream()
                                 .map(mapToConsecutiveAward)
                                 .filter(Objects::nonNull)
                                 .collect(Collectors.toList());
    }

    private Map<String, Collection<Movie>> mapMovieByProducer(final Collection<Movie> movies) {
        final var movieByProducer = new HashMap<String, Collection<Movie>>();

        movies.forEach(movie -> movie.getProducers()
                                     .forEach(producer -> this.addMovieByProducer(movieByProducer, producer, movie)));

        return movieByProducer;
    }

    private void addMovieByProducer(final Map<String, Collection<Movie>> resultMap,
                                    final String producer,
                                    final Movie movie) {
        if (Objects.isNull(resultMap) || StringUtils.isBlank(producer) || Objects.isNull(movie)) {
            return;
        }

        final var movies = new HashSet<Movie>();
        movies.add(movie);

        if (resultMap.containsKey(producer)) {
            movies.addAll(resultMap.get(producer));
        }

        resultMap.put(producer, movies);
    }
}
