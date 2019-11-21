package com.phakk.transit.staticgtfs.datastore.repository.jpa;

import com.phakk.transit.staticgtfs.datastore.entity.jpa.AgencyEntity;
import org.springframework.data.repository.CrudRepository;

public interface AgencyJpaRepository extends CrudRepository<AgencyEntity, Long> {
    AgencyEntity findByAgencyId(String agencyId);
}
