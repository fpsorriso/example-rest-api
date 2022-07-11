package com.texo.worstmovie.business.usecase.presenter.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public record ConsecutiveAwardsResponse(@JsonProperty("min") Collection<ConsecutiveAward> min,
                                        @JsonProperty("max") Collection<ConsecutiveAward> max ) { }
