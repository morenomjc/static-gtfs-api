package com.phakk.transit.staticgtfs.api.rest.mapper;

import com.phakk.transit.staticgtfs.api.rest.dto.CalendarDto;
import com.phakk.transit.staticgtfs.core.calendar.Calendar;
import org.mapstruct.Mapper;

@Mapper
public interface CalendarDtoMapper {
    CalendarDto toDto(Calendar calendar);
}
