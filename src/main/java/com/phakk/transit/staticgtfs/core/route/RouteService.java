package com.phakk.transit.staticgtfs.core.route;

import java.util.List;

public interface RouteService {
    Route getRoute(String id);
    List<Route> getRoutesByAgency(String agency);
}
