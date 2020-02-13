package com.phakk.transit.staticgtfs.api.rest.controller;

import com.phakk.transit.staticgtfs.api.rest.dto.DataTypes;
import com.phakk.transit.staticgtfs.api.rest.dto.TripDto;
import com.phakk.transit.staticgtfs.api.rest.mapper.RouteDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.mapper.TripDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.resource.TripResource;
import com.phakk.transit.staticgtfs.api.spec.ApiData;
import com.phakk.transit.staticgtfs.api.spec.ApiDocument;
import com.phakk.transit.staticgtfs.api.spec.ApiResource;
import com.phakk.transit.staticgtfs.core.route.RouteService;
import com.phakk.transit.staticgtfs.core.trip.TripService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@AllArgsConstructor
public class TripController implements TripResource {

    private TripService tripService;
    private TripDtoMapper tripDtoMapper;
    private RouteService routeService;
    private RouteDtoMapper routeDtoMapper;

    @Override
    public ResponseEntity<ApiDocument> getTrip(String id) {
        log.info("Action: getTrip [{}]", id);
        TripDto tripDto = tripDtoMapper.toDto(tripService.getTrip(id));
        ApiResource<?> apiResource = new ApiResource<>(
                getResourceType(),
                tripDto,
                selfLink(id, getClass()),
                routeLink(tripDto.getRouteId())
        );
        ApiData<?> routeData = new ApiData<>(
                DataTypes.ROUTE.getValue(),
                routeDtoMapper.mapToDto(routeService.getRoute(tripDto.getRouteId())),
                selfLink(tripDto.getRouteId(), RouteController.class)
        );
        apiResource.getData().addRelationship("route", routeData);

        return ResponseEntity.ok(apiResource);
    }

    private Link routeLink(String id){
        return  linkTo(RouteController.class).slash(id).withRel("route");
    }
}
