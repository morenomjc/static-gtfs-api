package com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopJpaRepository extends JpaRepository<StopEntity, Long> {
    StopEntity findByStopId(String stopId);
}
