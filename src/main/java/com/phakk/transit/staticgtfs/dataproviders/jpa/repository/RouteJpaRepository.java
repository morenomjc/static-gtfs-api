package com.phakk.transit.staticgtfs.dataproviders.jpa.repository;

import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteJpaRepository extends JpaRepository<RouteEntity, Long> {
    RouteEntity findByRouteId(String routeId);
    List<RouteEntity> findByAgency(String agency);
}
