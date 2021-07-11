package com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopJpaRepository extends JpaRepository<StopEntity, Long> {
    StopEntity findByStopId(String stopId);
}
