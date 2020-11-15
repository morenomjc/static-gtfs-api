package com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteJpaRepository extends JpaRepository<RouteEntity, Long> {
    RouteEntity findByRouteId(String routeId);
    List<RouteEntity> findByAgency(String agency);

    @Query(nativeQuery = true, value = "select r.route_type as type, count(r.route_type) as count from staticgtfs.routes r group by r.route_type")
    List<RouteTypeStatistics> findRouteTypeCounts();

    interface RouteTypeStatistics {
        String getType();
        Integer getCount();
    }
}
