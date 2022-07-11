package com.texo.worstmovie.business.usecase.presenter.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsecutiveAward(@JsonProperty String Producer,
                               @JsonProperty Integer interval,
                               @JsonProperty Integer previousWin,
                               @JsonProperty Integer followingWin) {
}
