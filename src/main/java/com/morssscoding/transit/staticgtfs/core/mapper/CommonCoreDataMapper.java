package com.morssscoding.transit.staticgtfs.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonCoreDataMapper {

    @Named("mapToLocalTime")
    default LocalTime mapToLocalTime(String value){
        if (Objects.isNull(value)){
            return null;
        }
        List<Byte> values = Arrays.stream(value.split(":")).map(Byte::parseByte).collect(Collectors.toList());
        if (values.size() < 3){
            return null;
        }
        return LocalTime.of( values.get(0) % 24, values.get(1), values.get(2));
    }

    @Named("mapToDuration")
    default Duration mapToDuration(Integer value){
        if (Objects.isNull(value)){
            return null;
        }
        return Duration.of(value, ChronoUnit.SECONDS);
    }

    @Named("mapFromDuration")
    default Integer mapFromDuration(Duration value){
        if (Objects.isNull(value)){
            return null;
        }
        return Long.valueOf(value.getSeconds()).intValue();
    }
}
