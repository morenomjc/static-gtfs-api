package com.phakk.transit.staticgtfs.dataproviders.repository.trip;

import com.phakk.transit.staticgtfs.core.constants.BikesAllowed;
import com.phakk.transit.staticgtfs.core.constants.Direction;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibility;
import com.phakk.transit.staticgtfs.core.exception.ConstantsMappingException;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.Optional;

@Mapper
public interface TripEntityMapper {

    @Mapping(target = "directionId", source = "directionId", qualifiedByName = "directionId")
    @Mapping(target = "wheelchairAccessible", source = "wheelchairAccessible", qualifiedByName = "wheelchairAccessible")
    @Mapping(target = "bikesAllowed", source = "bikesAllowed", qualifiedByName = "bikesAllowed")
    Trip fromEntity(TripEntity tripEntity);

    @Named("directionId")
    default Direction convertToDirection(String direction){
        Optional<Direction> values = Arrays.stream(Direction.values())
                .filter(value -> value.getCode().equalsIgnoreCase(direction))
                .findFirst();
        return values.orElseThrow(() -> new ConstantsMappingException("Failed to map direction id"));
    }

    @Named("wheelchairAccessible")
    default WheelchairAccessibility convertToWheelchairAccessible(String wheelchairAccessible){
        Optional<WheelchairAccessibility> wbEnum = Arrays.stream(WheelchairAccessibility.values())
                .filter(wb -> wb.getCode().equalsIgnoreCase(wheelchairAccessible))
                .findFirst();
        return wbEnum.orElseThrow(() -> new ConstantsMappingException("Failed to map wheelchair accessibility details"));
    }

    @Named("bikesAllowed")
    default BikesAllowed convertToBikesAllowed(String bikesAllowed){
        Optional<BikesAllowed> bikesAllowedEnum = Arrays.stream(BikesAllowed.values())
                .filter(ba -> ba.getCode().equalsIgnoreCase(bikesAllowed))
                .findFirst();
        return bikesAllowedEnum.orElseThrow(() -> new ConstantsMappingException("Failed to map bikes allowed details"));
    }
}
