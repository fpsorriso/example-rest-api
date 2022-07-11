package com.texo.worstmovie.support.csv.services;

import com.texo.worstmovie.support.CustomPredicate;
import com.texo.worstmovie.interfacesadapters.repositories.Producer;
import com.texo.worstmovie.interfacesadapters.repositories.ProducerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ProducerService {
    private static final String PRODUCER_NAME_REGEX_SEPARATOR = ",|( and )";

    private final ProducerRepository producerRepository;

    public ProducerService(final ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public Collection<String> getAllProducerNames(final String producers) {
        if (StringUtils.isNotBlank(producers)) {
            return Arrays.stream(producers.split(PRODUCER_NAME_REGEX_SEPARATOR))
                         .filter(Predicate.not(StringUtils::isBlank))
                         .map(String::trim)
                         .collect(Collectors.toList());
        }

        return List.of();
    }

    public Collection<Producer> createProducers(Collection<String> producerNames) {
        if (!CollectionUtils.isEmpty(producerNames)) {
            return producerNames.stream().map(this::getAllProducerNames)
                                                .flatMap(Collection::stream)
                                                .filter(CustomPredicate.distinctBy(String::toUpperCase))
                                                .map(Producer::new)
                                                .collect(Collectors.toList());

        }
        return Set.of();
    }

    public void insertAll(Collection<Producer> producers) {
        if (!CollectionUtils.isEmpty(producers)) {
            producerRepository.saveAll(producers);
        }
    }

    public Collection<Producer> getProducerByNamesIn(Collection<String> producerNames) {
        return producerRepository.findAllByNameIn(producerNames);
    }
}
