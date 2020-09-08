package com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StopTimeJpaRepository extends JpaRepository<StopTimeEntity, Long> {
    List<StopTimeEntity> findAllByTripId(String tripId);
}
