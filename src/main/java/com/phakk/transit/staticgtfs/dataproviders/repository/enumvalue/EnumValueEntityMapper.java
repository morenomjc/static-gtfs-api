package com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue;

import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.EnumValueEntity;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper
public interface EnumValueEntityMapper {

    EnumValue fromEntity(EnumValueEntity enumValueEntity);

    default String fromEnumValue(EnumValue enumValue){
        return Objects.nonNull(enumValue) ? enumValue.getCode() : null;
    }

}
