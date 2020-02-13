package com.phakk.transit.staticgtfs.dataproviders.repository.route;

import com.phakk.transit.staticgtfs.core.route.Route;

public interface RouteRepository {
    Route getRouteById(String id);
}
