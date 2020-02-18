package com.phakk.transit.staticgtfs.api.rest.mapper;

import com.phakk.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.phakk.transit.staticgtfs.api.rest.dto.StopDto;
import com.phakk.transit.staticgtfs.core.constants.StopType;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibility;
import com.phakk.transit.staticgtfs.core.stop.Stop;
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
    default DataTypeDto mapStopType(StopType type){
        if (Objects.isNull(type)){
            return null;
        }
        return DataTypeDto.builder()
                .code(type.getCode())
                .desc(type.getDescription())
                .build();
    }

    @Named("mapWheelChairBoarding")
    default DataTypeDto mapWheelChairBoarding(WheelchairAccessibility wheelchairBoarding){
        if (Objects.isNull(wheelchairBoarding)){
            return null;
        }
        return DataTypeDto.builder()
                .code(wheelchairBoarding.getCode())
                .desc(wheelchairBoarding.getDescription())
                .build();
    }
}
