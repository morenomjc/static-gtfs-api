package com.morssscoding.transit.staticgtfs.core.agency;

import java.util.List;

public interface AgencyService {
    List<Agency> getAgencies();
    Agency getAgency(String id);
}
