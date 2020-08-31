package com.phakk.transit.staticgtfs.dataproviders.repository.route;

import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.dataproviders.repository.Repository;

public interface RouteRepository extends Repository<Route> {
    Route getRouteById(String id);
}
