package com.texo.worstmovie.repositories;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "release_year")
    private Integer year;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_producer",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id"))
    private Collection<Producer> producers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_studio",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id"))
    private Collection<Studio> studios;
    @Column(name = "won_awards")
    private Boolean wonAwards;

    protected Movie() {
    }

    public Movie(final String title,
                 final Integer year,
                 final Boolean wonAwards,
                 final Collection<Producer> producers,
                 final Collection<Studio> studios) {
        this();
        this.title = title;
        this.year = year;
        this.producers = producers;
        this.wonAwards = wonAwards;
        this.studios = studios;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public Collection<Producer> getProducers() {
        return producers;
    }

    public Collection<Studio> getStudios() {
        return studios;
    }

    public Boolean getWonAwards() {
        return wonAwards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Movie movie = (Movie) o;

        return new EqualsBuilder().append(getId(), movie.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).toHashCode();
    }
}
