package com.texo.worstmovie.business.entities;

import java.util.Collection;

public interface Movie {
    String getTitle();
    Integer getYear();
    Collection<String> getStudios();
    Collection<String> getProducers();
}
