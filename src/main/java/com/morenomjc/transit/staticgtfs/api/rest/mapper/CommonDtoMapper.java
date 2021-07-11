package com.morenomjc.transit.staticgtfs.api.rest.mapper;

import com.morenomjc.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Objects;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonDtoMapper {

    @Named("mapToDataType")
    default DataTypeDto mapToDataType(EnumValue enumValue){
        if (Objects.isNull(enumValue)){
            return null;
        }
        return DataTypeDto.builder()
                .code(enumValue.getCode())
                .desc(enumValue.getName())
                .build();
    }
}
