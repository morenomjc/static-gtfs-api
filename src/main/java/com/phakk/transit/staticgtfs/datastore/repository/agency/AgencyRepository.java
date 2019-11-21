package com.phakk.transit.staticgtfs.datastore.repository.agency;

import com.phakk.transit.staticgtfs.core.agency.Agency;

public interface AgencyRepository {
    Agency getAgency(String id);
}
