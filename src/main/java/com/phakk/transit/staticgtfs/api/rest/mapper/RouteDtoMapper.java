package com.phakk.transit.staticgtfs.api.rest.mapper;

import com.phakk.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.phakk.transit.staticgtfs.api.rest.dto.RouteDto;
import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import com.phakk.transit.staticgtfs.core.route.Route;
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
