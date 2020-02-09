package com.phakk.transit.staticgtfs.api.rest.controller;

import com.phakk.transit.staticgtfs.api.rest.dto.AgencyDto;
import com.phakk.transit.staticgtfs.api.rest.mapper.AgencyDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.resource.AgencyResource;
import com.phakk.transit.staticgtfs.api.spec.ApiData;
import com.phakk.transit.staticgtfs.api.spec.ApiTemplate;
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
    public ResponseEntity<ApiTemplate> getAgencies() {
        log.info("Action: getAgencies");
        return ResponseEntity.ok(
                new ApiData<>(
                        agencyService.getAgencies().stream()
                                .map(agency -> agencyDtoMapper.toDto(agency))
                                .collect(Collectors.toList())
                )
        );
    }

    @Override
    public ResponseEntity<ApiTemplate> getAgency(String agencyId) {
        log.info("Action: getAgency [{}]", agencyId);
        return ResponseEntity.ok(
                new ApiData<>(agencyDtoMapper.toDto(agencyService.getAgency(agencyId)))
        );
    }

    private ApiData<List<AgencyDto>> mapToApiDto(List<AgencyDto> list){
        return new ApiData<>(list);
    }
}
