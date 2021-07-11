package com.morenomjc.transit.staticgtfs.dataproviders.repository.route;

import com.morenomjc.transit.staticgtfs.batch.model.GtfsRoute;
import com.morenomjc.transit.staticgtfs.core.route.Route;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(uses = { EnumValueEntityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RouteEntityMapper {

    @Mapping(target = "id", source = "routeId")
    @Mapping(target = "type", ignore = true)
    Route fromEntity(RouteEntity routeEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "routeId", source = "id")
    RouteEntity toEntity(Route data);

    @Mapping(source = "route_id", target = "id")
    @Mapping(source = "agency_id", target = "agency")
    @Mapping(source = "route_short_name", target = "shortName")
    @Mapping(source = "route_long_name", target = "longName")
    @Mapping(source = "route_desc", target = "desc")
    @Mapping(target = "type", ignore = true)
    @Mapping(source = "route_url", target = "url")
    @Mapping(source = "route_color", target = "color")
    @Mapping(source = "route_text_color", target = "textColor")
    Route convert(GtfsRoute gtfsRoute);

}
