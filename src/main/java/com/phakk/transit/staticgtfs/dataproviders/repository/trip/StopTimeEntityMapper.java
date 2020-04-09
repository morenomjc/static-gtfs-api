package com.phakk.transit.staticgtfs.dataproviders.repository.trip;

import com.phakk.transit.staticgtfs.core.trip.StopTime;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StopTimeEntityMapper {

    @Mapping(target = "pickupType", ignore = true)
    @Mapping(target = "dropOffType", ignore = true)
    @Mapping(target = "timepoint", ignore = true)
    StopTime fromEntity(StopTimeEntity stopTimeEntity);
}
