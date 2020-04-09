package com.phakk.transit.staticgtfs.dataproviders.repository.route;

import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { EnumValueEntityMapper.class })
public interface RouteEntityMapper {

    @Mapping(target = "id", source = "routeId")
    @Mapping(ignore = true, target = "type")
    Route fromEntity(RouteEntity routeEntity);
}
