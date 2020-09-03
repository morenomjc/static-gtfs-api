package com.phakk.transit.staticgtfs.dataproviders.repository.route;

import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.dataproviders.repository.Repository;

import java.util.List;

public interface RouteRepository extends Repository<Route> {
    Route getRouteById(String id);
    List<Route> getRoutesByAgency(String agency);
}
