package com.phakk.transit.staticgtfs.api.rest.controller;

import com.phakk.transit.staticgtfs.api.rest.dto.AgencyDto;
import com.phakk.transit.staticgtfs.api.rest.resource.AgencyResource;
import com.phakk.transit.staticgtfs.api.spec.ApiData;
import com.phakk.transit.staticgtfs.api.spec.ApiTemplate;
import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.core.agency.AgencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class AgencyController implements AgencyResource {

    private AgencyService agencyService;

    @Autowired
    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @Override
    public ResponseEntity<ApiTemplate> getAgencies() {
        log.info("Action: getAgencies");
        return ResponseEntity.ok(
                mapToApiDto(
                        agencyService.getAgencies().stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList())
                )
        );
    }

    @Override
    public ResponseEntity<ApiTemplate> getAgency(String agencyId) {
        log.info("Action: getAgency [{}]", agencyId);
        return ResponseEntity.ok(
                mapToApiDto(
                        mapToDto(agencyService.getAgency(agencyId))
                )
        );
    }

    private AgencyDto mapToDto(Agency agency){
        return AgencyDto.builder()
                .id(agency.getId())
                .name(agency.getName())
                .url(agency.getUrl())
                .timezone(agency.getTimezone())
                .lang(agency.getLang())
                .phone(agency.getPhone())
                .email(agency.getEmail())
                .build();
    }

    private ApiData<AgencyDto> mapToApiDto(AgencyDto agencyDto){
        return new ApiData<>(agencyDto);
    }

    private ApiData<List<AgencyDto>> mapToApiDto(List<AgencyDto> list){
        return new ApiData<>(list);
    }
}
