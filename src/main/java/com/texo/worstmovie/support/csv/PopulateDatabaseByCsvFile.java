package com.texo.worstmovie.support.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.texo.worstmovie.interfacesadapters.repositories.Movie;
import com.texo.worstmovie.interfacesadapters.repositories.MovieRepository;
import com.texo.worstmovie.support.csv.dtos.CsvInitDataModel;
import com.texo.worstmovie.support.csv.services.ProducerService;
import com.texo.worstmovie.support.csv.services.StudioService;
import org.apache.commons.lang3.CharUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PopulateDatabaseByCsvFile {
    private static final Logger LOG = LoggerFactory.getLogger(PopulateDatabaseByCsvFile.class);
    private static final Character DEFAULT_CSV_SEPARATOR = ';';
    private final ProducerService producerService;
    private final Function<CsvInitDataModel, Movie> csvToMovieAdapter;
    private final MovieRepository movieRepository;
    private final StudioService studioService;

    public PopulateDatabaseByCsvFile(@Value("${csv.initialization.filename}") final String filename,
                                     @Value("${csv.initialization.separator}") final String separator,
                                     final ProducerService producerService,
                                     final Function<CsvInitDataModel, Movie> csvToMovieAdapter,
                                     final MovieRepository movieRepository,
                                     final StudioService studioService) {
        this.producerService = producerService;
        this.csvToMovieAdapter = csvToMovieAdapter;
        this.movieRepository = movieRepository;
        this.studioService = studioService;
        populateDataBase(loadCsv(Paths.get(filename), CharUtils.toChar(separator, DEFAULT_CSV_SEPARATOR)));
    }

    private Collection<CsvInitDataModel> loadCsv(final Path file, final Character separator) {
        try (final var reader = Files.newBufferedReader(file)) {
            final var mappingStrategy = new HeaderColumnNameMappingStrategy<CsvInitDataModel>();
            mappingStrategy.setType(CsvInitDataModel.class);

            final var csvToBean = new CsvToBeanBuilder<CsvInitDataModel>(reader)
                    .withType(CsvInitDataModel.class)
                    .withMappingStrategy(mappingStrategy)
                    .withSeparator(separator)
                    .build();

            return csvToBean.parse();
        } catch (IOException e) {
            LOG.error("The file {} does not exist", file, e);
        } catch (Exception e) {
            LOG.error("Occurred a error while parsing", e);
        }

        return List.of();
    }

    private void populateDataBase(final Collection<CsvInitDataModel> originalData) {
        populateProducers(originalData);
        populateStudios(originalData);
        populateMovies(originalData);
    }

    private void populateProducers(final Collection<CsvInitDataModel> originalData) {
        final var producerNames = originalData.stream()
                                          .map(CsvInitDataModel::getProducers)
                                          .collect(Collectors.toList());

        final var producers = producerService.createProducers(producerNames);

        this.producerService.insertAll(producers);
    }

    private void populateStudios(final Collection<CsvInitDataModel> originalData) {
        final var studioNames = originalData.stream()
                                          .map(CsvInitDataModel::getStudios)
                                          .collect(Collectors.toList());

        final var studios = studioService.createStudios(studioNames);

        this.studioService.insertAll(studios);
    }

    private void populateMovies(final Collection<CsvInitDataModel> originalData) {
        final var movies = originalData.stream()
                                       .filter(Objects::nonNull)
                                       .map(csvToMovieAdapter)
                                       .collect(Collectors.toList());

        movieRepository.saveAll(movies);
    }

}
