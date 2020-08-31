package com.phakk.transit.staticgtfs.dataproviders.repository.agency;

import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.dataproviders.repository.Repository;

import java.util.List;

public interface AgencyRepository extends Repository<Agency> {
    List<Agency> getAgencies();
    Agency getAgency(String id);
}
