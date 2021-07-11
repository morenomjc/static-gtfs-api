package com.morenomjc.transit.staticgtfs.core.route;

import java.util.List;

public interface RouteService {
    Route getRoute(String id);
    List<Route> getByAgency(String agency);
    List<Route> getByRouteType(String routeType);
    List<RouteType> getRouteTypes();
}
