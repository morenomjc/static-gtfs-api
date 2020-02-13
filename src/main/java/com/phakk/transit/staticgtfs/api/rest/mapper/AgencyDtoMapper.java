package com.phakk.transit.staticgtfs.api.rest.mapper;

import com.phakk.transit.staticgtfs.api.rest.dto.AgencyDto;
import com.phakk.transit.staticgtfs.core.agency.Agency;
import org.mapstruct.Mapper;

@Mapper
public interface AgencyDtoMapper {
    AgencyDto toDto(Agency agency);
}
