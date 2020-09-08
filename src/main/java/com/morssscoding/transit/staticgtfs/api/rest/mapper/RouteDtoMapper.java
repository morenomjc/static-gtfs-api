package com.morssscoding.transit.staticgtfs.api.rest.mapper;

import com.morssscoding.transit.staticgtfs.core.constants.EnumValue;
import com.morssscoding.transit.staticgtfs.core.route.Route;
import com.morssscoding.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.morssscoding.transit.staticgtfs.api.rest.dto.RouteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Objects;

@Mapper
public interface RouteDtoMapper {

    @Mapping(target = "type", source = "type", qualifiedByName = "routeType")
    RouteDto mapToDto(Route route);

    @Named("routeType")
    default DataTypeDto mapRouteType(EnumValue type){
        if (Objects.isNull(type)){
            return null;
        }
        return DataTypeDto.builder()
                .code(type.getCode())
                .desc(type.getName())
                .build();
    }
}
