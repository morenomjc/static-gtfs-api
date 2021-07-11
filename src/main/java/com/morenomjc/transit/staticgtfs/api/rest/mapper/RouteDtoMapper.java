package com.morenomjc.transit.staticgtfs.api.rest.mapper;

import com.morenomjc.transit.staticgtfs.api.rest.dto.RouteTypeDto;
import com.morenomjc.transit.staticgtfs.core.route.Route;
import com.morenomjc.transit.staticgtfs.core.route.RouteType;
import com.morenomjc.transit.staticgtfs.api.rest.dto.RouteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CommonDtoMapper.class })
public interface RouteDtoMapper {

    @Mapping(target = "type", source = "type", qualifiedByName = "mapToDataType")
    RouteDto mapToDto(Route route);

    @Mapping(target = "type", source = "type", qualifiedByName = "mapToDataType")
    RouteTypeDto mapToDto(RouteType routeType);
}
