package com.phakk.transit.staticgtfs.api.rest.resource;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public interface AgencyResource {

    @GetMapping(value = "/{agencyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getAgency(@PathVariable(name = "agencyId") String agencyId);
}
