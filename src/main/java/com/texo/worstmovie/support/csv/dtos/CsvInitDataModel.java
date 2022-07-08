package com.texo.worstmovie.support.csv.dtos;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.texo.worstmovie.support.csv.adapters.CsvConverterYesNoToBoolean;

public class CsvInitDataModel {
    @CsvBindByName(column = "title", required = true)
    private String title;

    @CsvBindByName(column = "studios", required = true)
    private String studios;

    @CsvBindByName(column = "producers", required = true)
    private String producers;

    @CsvBindByName(column = "year", required = true)
    private Integer year;

    @CsvCustomBindByName(column = "winner",
            converter = CsvConverterYesNoToBoolean.class)
    private boolean winner;

    public String getTitle() {
        return title;
    }

    public String getStudios() {
        return studios;
    }

    public String getProducers() {
        return producers;
    }

    public Integer getYear() {
        return year;
    }

    public boolean isWinner() {
        return winner;
    }
}
