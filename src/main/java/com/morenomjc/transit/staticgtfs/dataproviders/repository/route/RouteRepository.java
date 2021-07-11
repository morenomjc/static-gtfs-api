package com.morenomjc.transit.staticgtfs.dataproviders.repository.route;

import com.morenomjc.transit.staticgtfs.core.route.Route;
import com.morenomjc.transit.staticgtfs.core.route.RouteType;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.Repository;

import java.util.List;

public interface RouteRepository extends Repository<Route> {
    Route getRouteById(String id);
    List<Route> getRoutesByAgency(String agency);
    List<Route> getRoutesByType(String routeType);
    List<RouteType> getRouteTypes();
}
