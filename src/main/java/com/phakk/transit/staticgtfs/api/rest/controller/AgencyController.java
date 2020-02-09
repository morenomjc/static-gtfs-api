package com.phakk.transit.staticgtfs.api.rest.controller;

import com.phakk.transit.staticgtfs.api.rest.dto.AgencyDto;
import com.phakk.transit.staticgtfs.api.rest.mapper.AgencyDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.resource.AgencyResource;
import com.phakk.transit.staticgtfs.api.spec.ApiData;
import com.phakk.transit.staticgtfs.api.spec.ApiDocument;
import com.phakk.transit.staticgtfs.api.spec.ApiResource;
import com.phakk.transit.staticgtfs.api.spec.ApiResources;
import com.phakk.transit.staticgtfs.core.agency.AgencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class AgencyController implements AgencyResource {

    private AgencyService agencyService;
    private AgencyDtoMapper agencyDtoMapper;

    public AgencyController(AgencyService agencyService, AgencyDtoMapper agencyDtoMapper) {
        this.agencyService = agencyService;
        this.agencyDtoMapper = agencyDtoMapper;
    }

    @Override
    public ResponseEntity<ApiDocument> getAgencies() {
        log.info("Action: getAgencies");
        return ResponseEntity.ok(
                new ApiResources<>(
                        toApiResources(
                                agencyService.getAgencies().stream()
                                .map(agency -> agencyDtoMapper.toDto(agency))
                                .collect(Collectors.toList())
                        )
                )
        );
    }

    @Override
    public ResponseEntity<ApiDocument> getAgency(String agencyId) {
        log.info("Action: getAgency [{}]", agencyId);
        return ResponseEntity.ok(
                new ApiResource<>(
                        getResourceType(),
                        agencyDtoMapper.toDto(agencyService.getAgency(agencyId)))
        );
    }

    private List<ApiData<AgencyDto>> toApiResources(List<AgencyDto> agencies){
        return agencies.stream()
                .map(agency -> new ApiData<>(getResourceType(), agency))
                .collect(Collectors.toList());
    }
}
