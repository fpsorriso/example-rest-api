package com.texo.worstmovie.interfacesadapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Collection<Producer> findAllByNameIn(Collection<String> names);
}
