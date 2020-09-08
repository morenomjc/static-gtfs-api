package com.morssscoding.transit.staticgtfs.api.rest.mapper;

import com.morssscoding.transit.staticgtfs.core.constants.EnumValue;
import com.morssscoding.transit.staticgtfs.core.stop.Stop;
import com.morssscoding.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.morssscoding.transit.staticgtfs.api.rest.dto.StopDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Objects;

@Mapper
public interface StopDtoMapper {

    @Mapping(target = "stopTimezone", source = "timezone")
    @Mapping(target = "locationType", source = "type", qualifiedByName = "mapStopType")
    @Mapping(target = "wheelchairBoarding", source = "wheelchairBoarding", qualifiedByName = "mapWheelChairBoarding")
    StopDto toDto(Stop stop);

    @Named("mapStopType")
    default DataTypeDto mapStopType(EnumValue type){
        if (Objects.isNull(type)){
            return null;
        }
        return DataTypeDto.builder()
                .code(type.getCode())
                .desc(type.getName())
                .build();
    }

    @Named("mapWheelChairBoarding")
    default DataTypeDto mapWheelChairBoarding(EnumValue wheelchairBoarding){
        if (Objects.isNull(wheelchairBoarding)){
            return null;
        }
        return DataTypeDto.builder()
                .code(wheelchairBoarding.getCode())
                .desc(wheelchairBoarding.getName())
                .build();
    }
}
