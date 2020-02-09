package com.phakk.transit.staticgtfs.datastore.repository.route;

import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.datastore.jpa.entity.RouteEntity;
import com.phakk.transit.staticgtfs.datastore.jpa.repository.RouteJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Slf4j
@Repository
public class RouteRepositoryJpaImpl implements RouteRepository {

    private RouteJpaRepository routeJpaRepository;
    private RouteEntityMapper routeEntityMapper;

    public RouteRepositoryJpaImpl(RouteJpaRepository routeJpaRepository, RouteEntityMapper routeEntityMapper) {
        this.routeJpaRepository = routeJpaRepository;
        this.routeEntityMapper = routeEntityMapper;
    }

    @Override
    public Route getRouteById(String id) {
        RouteEntity routeEntity = routeJpaRepository.findByRouteId(id);

        if (Objects.isNull(routeEntity)){
            throw new DataNotFoundException("Route not found.");
        }

        return routeEntityMapper.fromEntity(routeEntity);
    }
}
