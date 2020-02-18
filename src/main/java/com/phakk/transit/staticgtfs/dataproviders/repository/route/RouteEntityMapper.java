package com.phakk.transit.staticgtfs.dataproviders.repository.route;

import com.phakk.transit.staticgtfs.core.constants.RouteType;
import com.phakk.transit.staticgtfs.core.exception.ConstantsMappingException;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
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
    default RouteType convertToRouteType(String routeType){
        Optional<RouteType> routeTypeEnum = Arrays.stream(RouteType.values())
                .filter(rt -> rt.getCode().equalsIgnoreCase(routeType))
                .findFirst();
        return routeTypeEnum.orElseThrow(() -> new ConstantsMappingException("Failed to map route type"));
    }
}
