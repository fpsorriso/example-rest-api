package com.texo.worstmovie.business.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collection;

public record WinMovie (
        String title,
        Integer year,
        Collection<String> producers,
        Collection<String> studios) implements Movie {

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Integer getYear() {
        return year;
    }

    @Override
    public Collection<String> getStudios() {
        return studios;
    }

    @Override
    public Collection<String> getProducers() {
        return producers;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final WinMovie winMovie = (WinMovie) o;

        return new EqualsBuilder().append(getTitle(), winMovie.getTitle())
                                  .append(getYear(), winMovie.getYear()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getTitle()).append(getYear()).toHashCode();
    }
}
