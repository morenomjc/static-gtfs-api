package com.phakk.transit.staticgtfs.datastore.repository.jpa;

import com.phakk.transit.staticgtfs.datastore.entity.jpa.AgencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyJpaRepository extends JpaRepository<AgencyEntity, Long> {
    AgencyEntity findByAgencyId(String agencyId);
}
