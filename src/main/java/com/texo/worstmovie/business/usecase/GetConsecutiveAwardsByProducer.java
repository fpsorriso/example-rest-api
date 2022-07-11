package com.texo.worstmovie.business.usecase;

import com.texo.worstmovie.business.usecase.presenter.dtos.ConsecutiveAwardsResponse;

import java.util.function.Supplier;

public interface GetConsecutiveAwardsByProducer extends Supplier<ConsecutiveAwardsResponse> {
}
