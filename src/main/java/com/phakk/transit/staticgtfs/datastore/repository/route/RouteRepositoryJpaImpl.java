package com.phakk.transit.staticgtfs.datastore.repository.route;

import com.phakk.transit.staticgtfs.core.constants.RouteTypeEnum;
import com.phakk.transit.staticgtfs.core.exception.ConstantsMappingException;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.datastore.jpa.entity.RouteEntity;
import com.phakk.transit.staticgtfs.datastore.jpa.repository.RouteJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Repository
public class RouteRepositoryJpaImpl implements RouteRepository {

    private RouteJpaRepository routeJpaRepository;

    public RouteRepositoryJpaImpl(RouteJpaRepository routeJpaRepository) {
        this.routeJpaRepository = routeJpaRepository;
    }

    @Override
    public Route getRouteById(String id) {
        RouteEntity routeEntity = routeJpaRepository.findByRouteId(id);

        if (Objects.isNull(routeEntity)){
            throw new DataNotFoundException("Route not found.");
        }

        return fromEntity(routeEntity);
    }

    private Route fromEntity(RouteEntity routeEntity) {
        return Route.builder()
                .id(routeEntity.getRouteId())
                .agency(routeEntity.getAgency())
                .shortName(routeEntity.getShortName())
                .longName(routeEntity.getLongName())
                .desc(routeEntity.getDesc())
                .type(convertToRouteType(routeEntity.getType()))
                .url(routeEntity.getUrl())
                .color(routeEntity.getColor())
                .textColor(routeEntity.getTextColor())
                .sortOrder(routeEntity.getSortOrder())
                .build();
    }

    private RouteTypeEnum convertToRouteType(String id){
        Optional<RouteTypeEnum> routeTypeEnum = Arrays.stream(RouteTypeEnum.values())
                .filter(rt -> rt.getId().equalsIgnoreCase(id))
                .findFirst();
        return routeTypeEnum.orElseThrow(() -> new ConstantsMappingException("Failed to map route type"));
    }
}
