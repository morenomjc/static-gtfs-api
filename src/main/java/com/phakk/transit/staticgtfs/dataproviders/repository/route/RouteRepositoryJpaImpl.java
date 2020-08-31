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

import java.util.Objects;

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

        EnumValue routeType = enumValueRepository.findEnumValue(Route.TYPE, Route.Fields.ROUTE_TYPE.getValue(), routeEntity.getType());
        Route route = routeEntityMapper.fromEntity(routeEntity);
        route.setType(routeType);
        return route;
    }

    @Override
    public void save(Route data) {
        RouteEntity routeEntity = routeEntityMapper.toEntity(data);
        routeJpaRepository.save(routeEntity);
    }
}
