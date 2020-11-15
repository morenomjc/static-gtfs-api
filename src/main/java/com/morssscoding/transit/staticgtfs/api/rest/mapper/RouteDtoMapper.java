package com.morssscoding.transit.staticgtfs.api.rest.mapper;

import com.morssscoding.transit.staticgtfs.api.rest.dto.RouteDto;
import com.morssscoding.transit.staticgtfs.api.rest.dto.RouteTypeDto;
import com.morssscoding.transit.staticgtfs.core.route.Route;
import com.morssscoding.transit.staticgtfs.core.route.RouteType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { CommonDtoMapper.class })
public interface RouteDtoMapper {

    @Mapping(target = "type", source = "type", qualifiedByName = "mapToDataType")
    RouteDto mapToDto(Route route);

    @Mapping(target = "type", source = "type", qualifiedByName = "mapToDataType")
    RouteTypeDto mapToDto(RouteType routeType);
}
