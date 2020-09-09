package com.morssscoding.transit.staticgtfs.dataproviders.repository.stoptime;

import com.morssscoding.transit.staticgtfs.core.mapper.CommonCoreDataMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapper;
import com.morssscoding.transit.staticgtfs.batch.model.GtfsStopTime;
import com.morssscoding.transit.staticgtfs.core.trip.StopTime;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {EnumValueEntityMapper.class, CommonCoreDataMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StopTimeEntityMapper {

    @Mapping(target = "pickupType", ignore = true)
    @Mapping(target = "dropOffType", ignore = true)
    @Mapping(target = "timepoint", ignore = true)
    StopTime fromEntity(StopTimeEntity stopTimeEntity);

    @Mapping(target = "id", ignore = true)
    StopTimeEntity toEntity(StopTime stopTime);

    @Mapping(target = "tripId", source = "trip_id")
    @Mapping(target = "arrivalTime", source = "arrival_time", qualifiedByName = "mapToLocalTime")
    @Mapping(target = "departureTime", source = "departure_time", qualifiedByName = "mapToLocalTime")
    @Mapping(target = "stopId", source = "stop_id")
    @Mapping(target = "stopSequence", source = "stop_sequence")
    @Mapping(target = "stopHeadsign", source = "stop_headsign")
    @Mapping(target = "distanceTraveled", source = "shape_dist_traveled")
    @Mapping(target = "pickupType", ignore = true)
    @Mapping(target = "dropOffType", ignore = true)
    @Mapping(target = "timepoint", ignore = true)
    StopTime convert(GtfsStopTime gtfsStopTime);

}
