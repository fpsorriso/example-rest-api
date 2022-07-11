package com.texo.worstmovie.business.entities;

import com.texo.worstmovie.dtos.MovieDto;

public interface MovieFactory {
    Movie create(final MovieDto movieDto);
}
