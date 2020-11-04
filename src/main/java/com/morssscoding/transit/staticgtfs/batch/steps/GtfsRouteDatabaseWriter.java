package com.morssscoding.transit.staticgtfs.batch.steps;

import com.morssscoding.transit.staticgtfs.core.constants.EnumValue;
import com.morssscoding.transit.staticgtfs.core.route.Route;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.route.RouteEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.route.RouteRepository;
import com.morssscoding.transit.staticgtfs.batch.model.GtfsRoute;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class GtfsRouteDatabaseWriter implements ItemWriter<GtfsRoute> {

    private RouteRepository repository;
    private RouteEntityMapper mapper;
    private EnumValueRepository enumValueRepository;

    @Override
    public void write(List<? extends GtfsRoute> items) throws Exception {
        log.info("[RouteGtfsDatabaseWriter].write={}", items.size());
        items.forEach(item -> {
            Route route = mapper.convert(item);
            EnumValue routeType = enumValueRepository.findEnumValue(Route.TYPE, Route.Fields.ROUTE_TYPE.getValue(), String.valueOf(item.getRoute_type()));
            route.setType(routeType);
            repository.save(route);
        });
    }



}
