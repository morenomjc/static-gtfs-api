package com.phakk.transit.staticgtfs.dataproviders.repository.trip;

import com.phakk.transit.staticgtfs.core.constants.DropOffType;
import com.phakk.transit.staticgtfs.core.constants.PickupType;
import com.phakk.transit.staticgtfs.core.constants.Timepoint;
import com.phakk.transit.staticgtfs.core.exception.ConstantsMappingException;
import com.phakk.transit.staticgtfs.core.trip.StopTime;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.Optional;

@Mapper
public interface StopTimeEntityMapper {

    @Mapping(target = "pickupType", source = "pickupType", qualifiedByName = "pickupType")
    @Mapping(target = "dropOffType", source = "dropOffType", qualifiedByName = "dropOffType")
    @Mapping(target = "timepoint", source = "timepoint", qualifiedByName = "timepoint")
    StopTime fromEntity(StopTimeEntity stopTimeEntity);

    @Named("pickupType")
    default PickupType convertToPickupType(String pickupType){
        Optional<PickupType> values = Arrays.stream(PickupType.values())
                .filter(value -> value.getCode().equalsIgnoreCase(pickupType))
                .findFirst();
        return values.orElseThrow(() -> new ConstantsMappingException("Failed to map pickup type"));
    }

    @Named("dropOffType")
    default DropOffType convertToDropOffType(String dropOffType){
        Optional<DropOffType> values = Arrays.stream(DropOffType.values())
                .filter(value -> value.getCode().equalsIgnoreCase(dropOffType))
                .findFirst();
        return values.orElseThrow(() -> new ConstantsMappingException("Failed to map drop off type"));
    }

    @Named("timepoint")
    default Timepoint convertToTimepoint(String timepoint){
        Optional<Timepoint> values = Arrays.stream(Timepoint.values())
                .filter(value -> value.getCode().equalsIgnoreCase(timepoint))
                .findFirst();
        return values.orElseThrow(() -> new ConstantsMappingException("Failed to map timepoint"));
    }
}
