package com.morssscoding.transit.staticgtfs.api.rest.mapper;

import com.morssscoding.transit.staticgtfs.core.constants.EnumValue;
import com.morssscoding.transit.staticgtfs.core.trip.StopTime;
import com.morssscoding.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.morssscoding.transit.staticgtfs.api.rest.dto.StopTimeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Objects;

@Mapper
public interface StopTimeDtoMapper {

    @Mapping(target = "pickupType", source = "pickupType", qualifiedByName = "pickupType")
    @Mapping(target = "dropOffType", source = "dropOffType", qualifiedByName = "dropOffType")
    @Mapping(target = "timepoint", source = "timepoint", qualifiedByName = "timepoint")
    StopTimeDto toDto(StopTime stopTime);

    @Named("pickupType")
    default DataTypeDto mapPickupType(EnumValue pickupType){
        if (Objects.isNull(pickupType)){
            return null;
        }
        return DataTypeDto.builder()
                .code(pickupType.getCode())
                .desc(pickupType.getName())
                .build();
    }

    @Named("dropOffType")
    default DataTypeDto mapDropOffType(EnumValue dropOffType){
        if (Objects.isNull(dropOffType)){
            return null;
        }
        return DataTypeDto.builder()
                .code(dropOffType.getCode())
                .desc(dropOffType.getName())
                .build();
    }

    @Named("timepoint")
    default DataTypeDto mapTimepoint(EnumValue timepoint){
        if (Objects.isNull(timepoint)){
            return null;
        }
        return DataTypeDto.builder()
                .code(timepoint.getCode())
                .desc(timepoint.getName())
                .build();
    }
}
