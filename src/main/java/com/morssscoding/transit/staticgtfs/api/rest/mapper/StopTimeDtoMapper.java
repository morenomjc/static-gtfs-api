package com.morssscoding.transit.staticgtfs.api.rest.mapper;

import com.morssscoding.transit.staticgtfs.api.rest.dto.StopTimeDto;
import com.morssscoding.transit.staticgtfs.core.trip.StopTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { CommonDtoMapper.class })
public interface StopTimeDtoMapper {

    @Mapping(target = "pickupType", source = "pickupType", qualifiedByName = "mapToDataType")
    @Mapping(target = "dropOffType", source = "dropOffType", qualifiedByName = "mapToDataType")
    @Mapping(target = "timepoint", source = "timepoint", qualifiedByName = "mapToDataType")
    StopTimeDto toDto(StopTime stopTime);
}
