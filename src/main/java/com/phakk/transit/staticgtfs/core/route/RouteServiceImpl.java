package com.phakk.transit.staticgtfs.core.route;

import com.phakk.transit.staticgtfs.datastore.repository.route.RouteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RouteServiceImpl implements RouteService {

    private RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route getRoute(String id) {
        Route route = routeRepository.getRouteById(id);
        log.info("Found route with id: [{}]", route.getId());
        return route;
    }
}
