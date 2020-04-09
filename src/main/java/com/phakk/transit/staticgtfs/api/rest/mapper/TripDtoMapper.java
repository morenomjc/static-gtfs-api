package com.phakk.transit.staticgtfs.api.rest.mapper;

import com.phakk.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.phakk.transit.staticgtfs.api.rest.dto.TripDto;
import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Objects;

@Mapper
public interface TripDtoMapper {

    @Mapping(target = "directionId", source = "directionId", qualifiedByName = "directionId")
    @Mapping(target = "wheelchairAccessible", source = "wheelchairAccessible", qualifiedByName = "wheelchairAccessible")
    @Mapping(target = "bikesAllowed", source = "bikesAllowed", qualifiedByName = "bikesAllowed")
    TripDto toDto(Trip trip);

    @Named("directionId")
    default DataTypeDto mapDirectionId(EnumValue direction){
        if (Objects.isNull(direction)){
            return null;
        }
        return DataTypeDto.builder()
                .code(direction.getCode())
                .desc(direction.getName())
                .build();
    }

    @Named("wheelchairAccessible")
    default DataTypeDto mapWheelchairAccessible(EnumValue wheelchairAccessibility){
        if (Objects.isNull(wheelchairAccessibility)){
            return null;
        }
        return DataTypeDto.builder()
                .code(wheelchairAccessibility.getCode())
                .desc(wheelchairAccessibility.getName())
                .build();
    }

    @Named("bikesAllowed")
    default DataTypeDto mapBikesAllowed(EnumValue bikesAllowed){
        if (Objects.isNull(bikesAllowed)){
            return null;
        }
        return DataTypeDto.builder()
                .code(bikesAllowed.getCode())
                .desc(bikesAllowed.getName())
                .build();
    }
}
