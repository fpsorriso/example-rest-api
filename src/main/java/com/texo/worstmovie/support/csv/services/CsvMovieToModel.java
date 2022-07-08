package com.texo.worstmovie.support.csv.services;

import com.texo.worstmovie.repositories.Movie;
import com.texo.worstmovie.support.csv.dtos.CsvInitDataModel;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

@Component
public class CsvMovieToModel implements Function<CsvInitDataModel, Movie> {
    private final ProducerService producerService;

    private final StudioService studioService;

    public CsvMovieToModel(final ProducerService producerService, final StudioService studioService) {
        this.producerService = producerService;
        this.studioService = studioService;
    }

    @Override
    public Movie apply(CsvInitDataModel csvInitDataModel) {
        if (Objects.isNull(csvInitDataModel)) {
            return null;
        }

        final var producerNames = producerService.getAllProducerNames(csvInitDataModel.getProducers());
        final var producers = producerService.getProducerByNamesIn(producerNames);
        final var studiosNames = studioService.getAllStudioNames(csvInitDataModel.getStudios());
        final var studios = studioService.getStudioByNamesIn(studiosNames);

        return new Movie(csvInitDataModel.getTitle(), csvInitDataModel.getYear(), csvInitDataModel.isWinner(),
                         producers, studios);
    }
}
