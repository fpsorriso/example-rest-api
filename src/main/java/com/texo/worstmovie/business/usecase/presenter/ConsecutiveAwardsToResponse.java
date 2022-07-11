package com.texo.worstmovie.business.usecase.presenter;

import com.texo.worstmovie.business.usecase.presenter.dtos.ConsecutiveAward;
import com.texo.worstmovie.business.usecase.presenter.dtos.ConsecutiveAwardsResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ConsecutiveAwardsToResponse
        implements Function<Collection<ConsecutiveAward>, ConsecutiveAwardsResponse> {

    @Override
    public ConsecutiveAwardsResponse apply(final Collection<ConsecutiveAward> origin) {
        if (CollectionUtils.isEmpty(origin)) {
            return new ConsecutiveAwardsResponse(List.of(), List.of());
        }

        final var minInterval = origin.stream().mapToInt(ConsecutiveAward::interval).min().getAsInt();
        final var maxInterval = origin.stream().mapToInt(ConsecutiveAward::interval).max().getAsInt();

        final var allFasterWinners = origin.stream()
                                           .filter(consecutiveAward -> minInterval == consecutiveAward.interval())
                                           .collect(Collectors.toList());

        final var allSlowerWinners = origin.stream()
                                           .filter(consecutiveAward -> maxInterval == consecutiveAward.interval())
                                           .collect(Collectors.toList());

        return new ConsecutiveAwardsResponse(allFasterWinners, allSlowerWinners);
    }
}
