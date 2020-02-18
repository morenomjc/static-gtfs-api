package com.phakk.transit.staticgtfs.api.rest.mapper;

import com.phakk.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.phakk.transit.staticgtfs.api.rest.dto.TripDto;
import com.phakk.transit.staticgtfs.core.constants.BikesAllowed;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibility;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Objects;

@Mapper
public interface TripDtoMapper {

    @Mapping(target = "wheelchairAccessible", source = "wheelchairAccessible", qualifiedByName = "wheelchairAccessible")
    @Mapping(target = "bikesAllowed", source = "bikesAllowed", qualifiedByName = "bikesAllowed")
    TripDto toDto(Trip trip);

    @Named("wheelchairAccessible")
    default DataTypeDto mapWheelchairAccessible(WheelchairAccessibility wheelchairAccessibility){
        if (Objects.isNull(wheelchairAccessibility)){
            return null;
        }
        return DataTypeDto.builder()
                .code(wheelchairAccessibility.getCode())
                .desc(wheelchairAccessibility.getDescription())
                .build();
    }

    @Named("bikesAllowed")
    default DataTypeDto mapBikesAllowed(BikesAllowed bikesAllowed){
        if (Objects.isNull(bikesAllowed)){
            return null;
        }
        return DataTypeDto.builder()
                .code(bikesAllowed.getCode())
                .desc(bikesAllowed.getDescription())
                .build();
    }
}
