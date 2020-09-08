package com.morssscoding.transit.staticgtfs.api.rest.resource;

import com.morssscoding.transit.staticgtfs.api.spec.ApiDocument;
import com.morssscoding.transit.staticgtfs.core.constants.DataTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/trips")
public interface TripResource extends TypedResource {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiDocument> getTripsByRouteId(@RequestParam(value = "routeId")  String routeId);

    @GetMapping(value = "/{tripId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiDocument> getTrip(@PathVariable(name = "tripId") String tripId);

    @GetMapping(value = "/{tripId}/stops", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiDocument> getStopTimes(@PathVariable(name = "tripId") String tripId);

    @Override
    default String getResourceType() {
        return DataTypes.TRIP.getValue();
    }
}
