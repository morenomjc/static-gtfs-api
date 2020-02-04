package com.phakk.transit.staticgtfs.api.rest.resource;


import com.phakk.transit.staticgtfs.api.spec.ApiTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/routes")
public interface RouteResource {

    @GetMapping(value = "/{routeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiTemplate> getRoute(@PathVariable(name = "routeId") String id);

}
