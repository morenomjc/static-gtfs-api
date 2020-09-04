package com.phakk.transit.staticgtfs.dataproviders.repository.calendar;

import com.phakk.transit.staticgtfs.batch.model.GtfsCalendar;
import com.phakk.transit.staticgtfs.core.calendar.Calendar;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Objects;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CalendarEntityMapper {

    @Mapping(source = "monday", target = "monday", qualifiedByName = "mapToBoolean")
    @Mapping(source = "tuesday", target = "tuesday", qualifiedByName = "mapToBoolean")
    @Mapping(source = "wednesday", target = "wednesday", qualifiedByName = "mapToBoolean")
    @Mapping(source = "thursday", target = "thursday", qualifiedByName = "mapToBoolean")
    @Mapping(source = "friday", target = "friday", qualifiedByName = "mapToBoolean")
    @Mapping(source = "saturday", target = "saturday", qualifiedByName = "mapToBoolean")
    @Mapping(source = "sunday", target = "sunday", qualifiedByName = "mapToBoolean")
    Calendar fromEntity(CalendarEntity calendarEntity);

    @Mapping(source = "monday", target = "monday", qualifiedByName = "mapFromBoolean")
    @Mapping(source = "tuesday", target = "tuesday", qualifiedByName = "mapFromBoolean")
    @Mapping(source = "wednesday", target = "wednesday", qualifiedByName = "mapFromBoolean")
    @Mapping(source = "thursday", target = "thursday", qualifiedByName = "mapFromBoolean")
    @Mapping(source = "friday", target = "friday", qualifiedByName = "mapFromBoolean")
    @Mapping(source = "saturday", target = "saturday", qualifiedByName = "mapFromBoolean")
    @Mapping(source = "sunday", target = "sunday", qualifiedByName = "mapFromBoolean")
    CalendarEntity toEntity(Calendar calendar);

    @Mapping(source = "service_id", target = "serviceId")
    @Mapping(source = "monday", target = "monday", qualifiedByName = "mapToBoolean")
    @Mapping(source = "tuesday", target = "tuesday", qualifiedByName = "mapToBoolean")
    @Mapping(source = "wednesday", target = "wednesday", qualifiedByName = "mapToBoolean")
    @Mapping(source = "thursday", target = "thursday", qualifiedByName = "mapToBoolean")
    @Mapping(source = "friday", target = "friday", qualifiedByName = "mapToBoolean")
    @Mapping(source = "saturday", target = "saturday", qualifiedByName = "mapToBoolean")
    @Mapping(source = "sunday", target = "sunday", qualifiedByName = "mapToBoolean")
    Calendar convert(GtfsCalendar gtfsCalendar);

    @Named("mapToBoolean")
    default Boolean mapToBoolean(Integer value){
        if (Objects.isNull(value)){
            return null;
        }
        return value == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Named("mapFromBoolean")
    default Integer mapFromBoolean(Boolean value){
        if (Objects.isNull(value)){
            return null;
        }
        return value ? 1 : 0;
    }
}
