package com.phakk.transit.staticgtfs.api.rest.resource;


import com.phakk.transit.staticgtfs.api.rest.dto.StopDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/stops")
public interface StopResource {

    @GetMapping(value = "/{stopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<StopDto> getStop(@PathVariable(name = "stopId") String id);

}
