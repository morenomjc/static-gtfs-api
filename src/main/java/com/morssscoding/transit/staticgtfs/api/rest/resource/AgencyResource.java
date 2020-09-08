package com.morssscoding.transit.staticgtfs.api.rest.resource;


import com.morssscoding.transit.staticgtfs.api.spec.ApiDocument;
import com.morssscoding.transit.staticgtfs.core.constants.DataTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/agencies")
public interface AgencyResource extends TypedResource{

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiDocument> getAgencies();

    @GetMapping(value = "/{agencyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiDocument> getAgency(@PathVariable(name = "agencyId") String agencyId);

    @Override
    default String getResourceType(){
        return DataTypes.AGENCY.getValue();
    }
}
