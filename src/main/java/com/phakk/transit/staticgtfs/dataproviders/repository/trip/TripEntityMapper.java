package com.phakk.transit.staticgtfs.dataproviders.repository.trip;

import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TripEntityMapper {

    @Mapping(target = "directionId", ignore = true)
    @Mapping(target = "wheelchairAccessible", ignore = true)
    @Mapping(target = "bikesAllowed", ignore = true)
    Trip fromEntity(TripEntity tripEntity);
}
