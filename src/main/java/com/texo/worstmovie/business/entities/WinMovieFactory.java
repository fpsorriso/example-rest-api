package com.texo.worstmovie.business.entities;

import com.texo.worstmovie.dtos.MovieDto;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WinMovieFactory implements MovieFactory {
    @Override
    public Movie create(final MovieDto dto) {
        if (Objects.isNull(dto) || !Boolean.TRUE.equals(dto.hasAwards())) {
            return null;
        }

        return new WinMovie(dto.title(), dto.year(), dto.producers(), dto.studios());
    }
}
