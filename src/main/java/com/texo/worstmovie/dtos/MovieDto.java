package com.texo.worstmovie.dtos;

import java.util.Collection;

public record MovieDto(String title,
                       Integer year,
                       boolean hasAwards,
                       Collection<String> producers,
                       Collection<String> studios) {}

