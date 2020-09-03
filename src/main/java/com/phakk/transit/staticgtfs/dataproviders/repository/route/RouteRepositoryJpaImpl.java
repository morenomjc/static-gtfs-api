package com.phakk.transit.staticgtfs.dataproviders.repository.route;

import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.RouteJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Repository
@AllArgsConstructor
public class RouteRepositoryJpaImpl implements RouteRepository {

    private RouteJpaRepository routeJpaRepository;
    private RouteEntityMapper routeEntityMapper;
    private EnumValueRepository enumValueRepository;

    @Override
    public Route getRouteById(String id) {
        RouteEntity routeEntity = routeJpaRepository.findByRouteId(id);
        if (Objects.isNull(routeEntity)){
            throw new DataNotFoundException("Route not found.");
        }

        Route route = routeEntityMapper.fromEntity(routeEntity);
        route.setType(findRouteType(routeEntity.getType()));
        return route;
    }

    @Override
    public List<Route> getRoutesByAgency(String agency) {
        return routeJpaRepository.findByAgency(agency).stream()
                .map(routeEntity -> {
                    Route route = routeEntityMapper.fromEntity(routeEntity);
                    route.setType(findRouteType(routeEntity.getType()));
                    return route;
                }).collect(Collectors.toList());
    }

    private EnumValue findRouteType(String type){
        return enumValueRepository.findEnumValue(Route.TYPE, Route.Fields.ROUTE_TYPE.getValue(), type);
    }

    @Override
    public void save(Route data) {
        RouteEntity routeEntity = routeEntityMapper.toEntity(data);
        routeJpaRepository.save(routeEntity);
    }

}
