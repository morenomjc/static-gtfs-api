package com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue;

import com.morssscoding.transit.staticgtfs.core.constants.EnumValue;

public interface EnumValueRepository {
    EnumValue findEnumValue(String file, String field, String code);
}
