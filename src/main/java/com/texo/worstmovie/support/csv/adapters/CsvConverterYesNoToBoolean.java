package com.texo.worstmovie.support.csv.adapters;

import com.opencsv.bean.AbstractBeanField;

public class CsvConverterYesNoToBoolean extends AbstractBeanField<Boolean, String> {
    private static final String BOOLEAN_TRUE = "yes";

    @Override
    protected Object convert(final String value) {
        return BOOLEAN_TRUE.equalsIgnoreCase(value);
    }
}
