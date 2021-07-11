package com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarJpaRepository extends JpaRepository<CalendarEntity, Long> {
    CalendarEntity findByServiceId(String serviceId);
}
