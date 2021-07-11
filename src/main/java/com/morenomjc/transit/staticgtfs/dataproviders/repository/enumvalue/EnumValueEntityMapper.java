package com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue;

import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.EnumValueEntity;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface EnumValueEntityMapper {

    EnumValue fromEntity(EnumValueEntity enumValueEntity);

    default String fromEnumValue(EnumValue enumValue){
        return Objects.nonNull(enumValue) ? enumValue.getCode() : null;
    }

}
