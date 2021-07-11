package com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue;

import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;

public interface EnumValueRepository {
    EnumValue findEnumValue(String file, String field, String code);
}
