package com.morenomjc.transit.staticgtfs.dataproviders.repository.route;

import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.core.route.Route;
import com.morenomjc.transit.staticgtfs.core.route.RouteType;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.RouteJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RouteRepositoryJpaImpl implements RouteRepository {

    private final RouteJpaRepository routeJpaRepository;
    private final RouteEntityMapper routeEntityMapper;
    private final EnumValueRepository enumValueRepository;

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

    @Override
    public List<Route> getRoutesByType(String routeType) {
        return routeJpaRepository.findByType(routeType).stream()
                .map(routeEntity -> {
                    Route route = routeEntityMapper.fromEntity(routeEntity);
                    route.setType(findRouteType(routeEntity.getType()));
                    return route;
                })
                .sorted(Comparator.comparing(Route::getShortName))
                .collect(Collectors.toList());
    }

    private EnumValue findRouteType(String type){
        return enumValueRepository.findEnumValue(Route.TYPE, Route.Fields.ROUTE_TYPE.getValue(), type);
    }

    @Override
    public void save(Route data) {
        RouteEntity routeEntity = routeEntityMapper.toEntity(data);
        routeJpaRepository.save(routeEntity);
    }

    @Override
    public List<RouteType> getRouteTypes() {
        List<RouteType> routeTypes = routeJpaRepository.findRouteTypeCounts().stream()
                .peek(routeTypeStat -> log.info("routeType: {} = {}", routeTypeStat.getType(), routeTypeStat.getCount()))
                .map(routeTypeStat -> {
                    EnumValue routeType = findRouteType(routeTypeStat.getType());
                    return new RouteType(routeType, routeTypeStat.getCount());
                }).collect(Collectors.toList());
        log.info("{}", routeTypes.size());
        return routeTypes;
    }
}
