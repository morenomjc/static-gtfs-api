package com.phakk.transit.staticgtfs.datastore.repository.route;

import com.phakk.transit.staticgtfs.core.constants.RouteTypeEnum;
import com.phakk.transit.staticgtfs.core.exception.ConstantsMappingException;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.datastore.jpa.entity.RouteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.Optional;

@Mapper
public interface RouteEntityMapper {

    @Mapping(target = "id", source = "routeId")
    @Mapping(target = "type", source = "type", qualifiedByName = "routeType")
    Route fromEntity(RouteEntity routeEntity);

    @Named("routeType")
    default RouteTypeEnum convertToRouteType(String routeType){
        Optional<RouteTypeEnum> routeTypeEnum = Arrays.stream(RouteTypeEnum.values())
                .filter(rt -> rt.getId().equalsIgnoreCase(routeType))
                .findFirst();
        return routeTypeEnum.orElseThrow(() -> new ConstantsMappingException("Failed to map route type"));
    }
}
