package com.morssscoding.transit.staticgtfs.api.rest.mapper;

import com.morssscoding.transit.staticgtfs.api.rest.dto.FrequencyDto;
import com.morssscoding.transit.staticgtfs.core.frequency.Frequency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { CommonDtoMapper.class })
public interface FrequencyDtoMapper {

    @Mapping(target = "exactTimes", source = "exactTimes", qualifiedByName = "mapToDataType")
    FrequencyDto toDto(Frequency frequency);
}
