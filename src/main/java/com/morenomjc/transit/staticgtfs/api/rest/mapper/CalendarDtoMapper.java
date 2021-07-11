package com.morenomjc.transit.staticgtfs.api.rest.mapper;

import com.morenomjc.transit.staticgtfs.api.rest.dto.CalendarDto;
import com.morenomjc.transit.staticgtfs.core.calendar.Calendar;
import org.mapstruct.Mapper;

@Mapper
public interface CalendarDtoMapper {
    CalendarDto toDto(Calendar calendar);
}
