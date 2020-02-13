package com.phakk.transit.staticgtfs.dataproviders.jpa.repository;

import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripJpaRepository extends JpaRepository<TripEntity, Long> {
    TripEntity findByTripId(String tripId);
}
