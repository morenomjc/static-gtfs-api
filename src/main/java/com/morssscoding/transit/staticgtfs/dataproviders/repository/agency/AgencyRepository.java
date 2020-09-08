package com.morssscoding.transit.staticgtfs.dataproviders.repository.agency;

import com.morssscoding.transit.staticgtfs.core.agency.Agency;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.Repository;

import java.util.List;

public interface AgencyRepository extends Repository<Agency> {
    List<Agency> getAgencies();
    Agency getAgency(String id);
}
