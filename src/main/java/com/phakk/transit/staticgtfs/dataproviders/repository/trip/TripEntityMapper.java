package com.phakk.transit.staticgtfs.dataproviders.repository.trip;

import com.phakk.transit.staticgtfs.core.constants.BikesAllowedEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibilityEnum;
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

    @Mapping(target = "wheelchairAccessible", source = "wheelchairAccessible", qualifiedByName = "wheelchairAccessible")
    @Mapping(target = "bikesAllowed", source = "bikesAllowed", qualifiedByName = "wheelchairAccessible")
    Trip fromEntity(TripEntity tripEntity);

    @Named("wheelchairAccessible")
    default WheelchairAccessibilityEnum convertToWheelchairAccessible(String wheelchairAccessible){
        Optional<WheelchairAccessibilityEnum> wbEnum = Arrays.stream(WheelchairAccessibilityEnum.values())
                .filter(wb -> wb.getId().equalsIgnoreCase(wheelchairAccessible))
                .findFirst();
        return wbEnum.orElseThrow(() -> new ConstantsMappingException("Failed to map wheelchair accessibility details"));
    }

    @Named("bikesAllowed")
    default BikesAllowedEnum convertToBikesAllowed(String bikesAllowed){
        Optional<BikesAllowedEnum> bikesAllowedEnum = Arrays.stream(BikesAllowedEnum.values())
                .filter(ba -> ba.getId().equalsIgnoreCase(bikesAllowed))
                .findFirst();
        return bikesAllowedEnum.orElseThrow(() -> new ConstantsMappingException("Failed to map bikes allowed details"));
    }
}
