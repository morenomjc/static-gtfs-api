package com.morenomjc.transit.staticgtfs.api.rest.mapper;

import com.morenomjc.transit.staticgtfs.api.rest.dto.FrequencyDto;
import com.morenomjc.transit.staticgtfs.core.frequency.Frequency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CommonDtoMapper.class })
public interface FrequencyDtoMapper {

    @Mapping(target = "exactTimes", source = "exactTimes", qualifiedByName = "mapToDataType")
    FrequencyDto toDto(Frequency frequency);
}
