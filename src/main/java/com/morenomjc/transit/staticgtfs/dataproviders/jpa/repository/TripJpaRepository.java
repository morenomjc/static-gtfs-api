package com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripJpaRepository extends JpaRepository<TripEntity, Long> {
    TripEntity findByTripId(String tripId);
    List<TripEntity> findAllByRouteId(String routeId);
}
