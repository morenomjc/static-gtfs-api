package com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.FrequencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FrequencyJpaRepository extends JpaRepository<FrequencyEntity, Long> {
    Optional<FrequencyEntity> findByTripId(String tripId);
}
