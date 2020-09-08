package com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarJpaRepository extends JpaRepository<CalendarEntity, Long> {
    CalendarEntity findByServiceId(String serviceId);
}
