package com.morssscoding.transit.staticgtfs.api.rest.resource;

import com.morssscoding.transit.staticgtfs.api.spec.ApiDocument;
import com.morssscoding.transit.staticgtfs.core.constants.DataTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/routes")
public interface RouteResource extends TypedResource{

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiDocument> getAvailableRoutes();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiDocument> getRoute(@PathVariable(name = "id") String id);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiDocument> getRoutesByAgency(@RequestParam(value = "agencyId") String agencyId);

    @Override
    default String getResourceType(){
        return DataTypes.ROUTE.getValue();
    }
}
