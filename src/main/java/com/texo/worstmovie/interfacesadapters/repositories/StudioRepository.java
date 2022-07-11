package com.texo.worstmovie.interfacesadapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {
    Collection<Studio> findAllByNameIn(Collection<String> names);
}
