package com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue;

import com.phakk.transit.staticgtfs.core.constants.EnumValue;

public interface EnumValueRepository {
    EnumValue findEnumValue(String file, String field, String code);
}
