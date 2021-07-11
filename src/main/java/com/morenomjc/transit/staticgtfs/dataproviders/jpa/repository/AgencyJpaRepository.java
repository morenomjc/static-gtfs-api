package com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyJpaRepository extends JpaRepository<AgencyEntity, Long> {
    AgencyEntity findByAgencyId(String agencyId);
}
