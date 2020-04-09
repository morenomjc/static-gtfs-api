package com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue;

import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.EnumValueEntity;
import org.mapstruct.Mapper;

@Mapper
public interface EnumValueEntityMapper {

    EnumValue fromEntity(EnumValueEntity enumValueEntity);
}
