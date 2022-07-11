package com.texo.worstmovie.business.entities;

import com.texo.worstmovie.business.usecase.presenter.dtos.ConsecutiveAward;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
class MapToConsecutiveAward
        implements Function<Map.Entry<String, Collection<Movie>>, ConsecutiveAward> {

    private static final int PREVIOUS_WIN_INDEX = 0;
    private static final int FOLLOW_WIN_INDEX = 1;

    @Override
    public ConsecutiveAward apply(final Map.Entry<String, Collection<Movie>> origin) {
        if (Objects.isNull(origin) || StringUtils.isBlank(origin.getKey()) ||
            CollectionUtils.isEmpty(origin.getValue()) || origin.getValue().size() < 2) {
            return null;
        }

        final var consecutiveMovies = origin.getValue().stream()
                                            .sorted(Comparator.comparing(Movie::getYear))
                                            .limit(2).toList();

        final var interval = consecutiveMovies.stream()
                                              .map(Movie::getYear)
                                              .reduce(0, (firstYear, secondYear) -> Math.abs(firstYear - secondYear));

        return new ConsecutiveAward(origin.getKey(),  interval, consecutiveMovies.get(PREVIOUS_WIN_INDEX).getYear(),
                                              consecutiveMovies.get(FOLLOW_WIN_INDEX).getYear());
    }
}
