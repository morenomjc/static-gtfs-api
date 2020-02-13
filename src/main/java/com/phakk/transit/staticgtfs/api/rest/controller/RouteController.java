package com.phakk.transit.staticgtfs.api.rest.controller;

import com.phakk.transit.staticgtfs.api.rest.mapper.RouteDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.resource.RouteResource;
import com.phakk.transit.staticgtfs.api.spec.ApiDocument;
import com.phakk.transit.staticgtfs.api.spec.ApiResource;
import com.phakk.transit.staticgtfs.core.route.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RouteController implements RouteResource {

    private RouteService routeService;
    private RouteDtoMapper routeDtoMapper;

    public RouteController(RouteService routeService, RouteDtoMapper routeDtoMapper) {
        this.routeService = routeService;
        this.routeDtoMapper = routeDtoMapper;
    }

    @Override
    public ResponseEntity<ApiDocument> getRoute(String id) {
        return ResponseEntity.ok(
                new ApiResource<>(
                        getResourceType(),
                        routeDtoMapper.mapToDto(routeService.getRoute(id)),
                        selfLink(id, getClass())
                )
        );
    }
}
