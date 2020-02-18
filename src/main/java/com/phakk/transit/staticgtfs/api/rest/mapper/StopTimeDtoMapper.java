package com.phakk.transit.staticgtfs.api.rest.mapper;

import com.phakk.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.phakk.transit.staticgtfs.api.rest.dto.StopTimeDto;
import com.phakk.transit.staticgtfs.core.constants.DropOffType;
import com.phakk.transit.staticgtfs.core.constants.PickupType;
import com.phakk.transit.staticgtfs.core.constants.Timepoint;
import com.phakk.transit.staticgtfs.core.trip.StopTime;
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
    default DataTypeDto mapPickupType(PickupType pickupType){
        if (Objects.isNull(pickupType)){
            return null;
        }
        return DataTypeDto.builder()
                .code(pickupType.getCode())
                .desc(pickupType.getDescription())
                .build();
    }

    @Named("dropOffType")
    default DataTypeDto mapDropOffType(DropOffType dropOffType){
        if (Objects.isNull(dropOffType)){
            return null;
        }
        return DataTypeDto.builder()
                .code(dropOffType.getCode())
                .desc(dropOffType.getDescription())
                .build();
    }

    @Named("timepoint")
    default DataTypeDto mapTimepoint(Timepoint timepoint){
        if (Objects.isNull(timepoint)){
            return null;
        }
        return DataTypeDto.builder()
                .code(timepoint.getCode())
                .desc(timepoint.getDescription())
                .build();
    }
}
