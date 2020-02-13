package com.phakk.transit.staticgtfs.api.rest.mapper;

import com.phakk.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.phakk.transit.staticgtfs.api.rest.dto.TripDto;
import com.phakk.transit.staticgtfs.core.constants.BikesAllowedEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibilityEnum;
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
    default DataTypeDto mapWheelchairAccessible(WheelchairAccessibilityEnum wheelchairAccessibility){
        if (Objects.isNull(wheelchairAccessibility)){
            return null;
        }
        return DataTypeDto.builder()
                .id(wheelchairAccessibility.getId())
                .desc(wheelchairAccessibility.getDescription())
                .build();
    }

    @Named("bikesAllowed")
    default DataTypeDto mapBikesAllowed(BikesAllowedEnum bikesAllowed){
        if (Objects.isNull(bikesAllowed)){
            return null;
        }
        return DataTypeDto.builder()
                .id(bikesAllowed.getId())
                .desc(bikesAllowed.getDescription())
                .build();
    }
}
