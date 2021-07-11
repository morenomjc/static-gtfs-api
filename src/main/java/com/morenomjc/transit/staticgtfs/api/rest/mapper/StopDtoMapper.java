package com.morenomjc.transit.staticgtfs.api.rest.mapper;

import com.morenomjc.transit.staticgtfs.api.rest.dto.StopDto;
import com.morenomjc.transit.staticgtfs.core.stop.Stop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { CommonDtoMapper.class })
public interface StopDtoMapper {

    @Mapping(target = "stopTimezone", source = "timezone")
    @Mapping(target = "locationType", source = "type", qualifiedByName = "mapToDataType")
    @Mapping(target = "wheelchairBoarding", source = "wheelchairBoarding", qualifiedByName = "mapToDataType")
    StopDto toDto(Stop stop);

}
