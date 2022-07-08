package com.texo.worstmovie.support.csv.services;

import com.texo.worstmovie.support.CustomPredicate;
import com.texo.worstmovie.repositories.Studio;
import com.texo.worstmovie.repositories.StudioRepository;
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
public class StudioService {
    private static final String STUDIO_NAME_REGEX_SEPARATOR = ",";

    private final StudioRepository studioRepository;

    public StudioService(final StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    public Collection<String> getAllStudioNames(final String studioNames) {
        if (StringUtils.isNotBlank(studioNames)) {
            return Arrays.stream(studioNames.split(STUDIO_NAME_REGEX_SEPARATOR))
                         .filter(Predicate.not(StringUtils::isBlank))
                         .map(String::trim)
                         .collect(Collectors.toList());
        }

        return List.of();
    }

    public Collection<Studio> createStudios(final Collection<String> studioNames) {
        if (!CollectionUtils.isEmpty(studioNames)) {
            return studioNames.stream().map(this::getAllStudioNames)
                              .flatMap(Collection::stream)
                              .filter(CustomPredicate.distinctBy(String::toUpperCase))
                              .map(Studio::new)
                              .collect(Collectors.toList());

        }

        return Set.of();
    }

    public void insertAll(final Collection<Studio> studios) {
        if (!CollectionUtils.isEmpty(studios)) {
            studioRepository.saveAll(studios);
        }
    }

    public Collection<Studio> getStudioByNamesIn(Collection<String> studiosName) {
        return studioRepository.findAllByNameIn(studiosName);
    }
}
