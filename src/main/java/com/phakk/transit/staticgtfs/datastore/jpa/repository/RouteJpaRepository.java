package com.phakk.transit.staticgtfs.datastore.jpa.repository;

import com.phakk.transit.staticgtfs.datastore.jpa.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteJpaRepository extends JpaRepository<RouteEntity, Long> {
    RouteEntity findByRouteId(String routeId);
}
