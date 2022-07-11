package com.texo.worstmovie.interfacesadapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("select m from Movie m where m.wonAwards = true")
    Collection<Movie> findAllByWonAwardsIsTrue();
}
