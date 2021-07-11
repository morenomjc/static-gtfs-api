package com.morenomjc.transit.staticgtfs.api.rest.mapper;

import com.morenomjc.transit.staticgtfs.api.rest.dto.AgencyDto;
import com.morenomjc.transit.staticgtfs.core.agency.Agency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgencyDtoMapper {
    AgencyDto toDto(Agency agency);
}
