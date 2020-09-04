package com.phakk.transit.staticgtfs.dataproviders.repository.stoptime;

import com.phakk.transit.staticgtfs.batch.model.GtfsStopTime;
import com.phakk.transit.staticgtfs.core.trip.StopTime;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(uses = {EnumValueEntityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
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

    @Named("mapToBoolean")
    default LocalTime mapToLocalTime(String value){
        if (Objects.isNull(value)){
            return null;
        }
        List<Byte> values = Arrays.stream(value.split(":")).map(Byte::parseByte).collect(Collectors.toList());
        return LocalTime.of(values.get(0) % 24, values.get(1), values.get(2));
    }
}
