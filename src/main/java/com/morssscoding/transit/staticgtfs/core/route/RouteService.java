package com.morssscoding.transit.staticgtfs.core.route;

import java.util.List;

public interface RouteService {
    Route getRoute(String id);
    List<Route> getRoutesByAgency(String agency);
    List<RouteType> getRouteTypes();
}
