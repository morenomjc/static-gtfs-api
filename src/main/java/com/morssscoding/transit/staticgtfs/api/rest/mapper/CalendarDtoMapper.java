package com.morssscoding.transit.staticgtfs.api.rest.mapper;

import com.morssscoding.transit.staticgtfs.core.calendar.Calendar;
import com.morssscoding.transit.staticgtfs.api.rest.dto.CalendarDto;
import org.mapstruct.Mapper;

@Mapper
public interface CalendarDtoMapper {
    CalendarDto toDto(Calendar calendar);
}
