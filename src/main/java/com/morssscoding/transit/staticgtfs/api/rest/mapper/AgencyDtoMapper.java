package com.morssscoding.transit.staticgtfs.api.rest.mapper;

import com.morssscoding.transit.staticgtfs.core.agency.Agency;
import com.morssscoding.transit.staticgtfs.api.rest.dto.AgencyDto;
import org.mapstruct.Mapper;

@Mapper
public interface AgencyDtoMapper {
    AgencyDto toDto(Agency agency);
}
