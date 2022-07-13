package com.texo.worstmovie.business.entities;

import com.texo.worstmovie.business.usecase.presenter.dtos.ConsecutiveAward;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
class MapToConsecutiveAward
        implements Function<Map.Entry<String, Collection<Movie>>, Collection<ConsecutiveAward>> {

    @Override
    public Collection<ConsecutiveAward> apply(final Map.Entry<String, Collection<Movie>> origin) {
        if (Objects.isNull(origin) || StringUtils.isBlank(origin.getKey()) ||
            CollectionUtils.isEmpty(origin.getValue()) || origin.getValue().size() < 2) {
            return List.of();
        }

        final var allProducerConsecutiveAwards = new ArrayList<ConsecutiveAward>();

        origin.getValue().stream()
              .sorted(Comparator.comparing(Movie::getYear))
              .reduce((previousWin, followWin) -> addConsecutiveAwardOnListByReduce(allProducerConsecutiveAwards,
                                                                                    origin.getKey(), previousWin,
                                                                                    followWin));
        return allProducerConsecutiveAwards;
    }

    private ConsecutiveAward createConsecutiveAward(final String producer,
                                                    final Movie previousWin,
                                                    final Movie followWin) {
        if (StringUtils.isBlank(producer) || Objects.isNull(previousWin) || Objects.isNull(followWin)) {
            return null;
        }

        final var interval = Math.abs(previousWin.getYear() - followWin.getYear());

        return new ConsecutiveAward(producer, interval, previousWin.getYear(), followWin.getYear());
    }

    private Movie addConsecutiveAwardOnListByReduce(final Collection<ConsecutiveAward> allConsecutiveAwards,
                                                    final String producer,
                                                    final Movie previousWin,
                                                    final Movie followWin) {
        if (Objects.nonNull(allConsecutiveAwards)) {
            allConsecutiveAwards.add(createConsecutiveAward(producer, previousWin, followWin));
        }
        return followWin;
    }
}
