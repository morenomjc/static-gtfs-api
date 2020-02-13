package com.phakk.transit.staticgtfs.dataproviders.repository.agency;

import com.phakk.transit.staticgtfs.core.agency.Agency;

import java.util.List;

public interface AgencyRepository {
    List<Agency> getAgencies();
    Agency getAgency(String id);
}
