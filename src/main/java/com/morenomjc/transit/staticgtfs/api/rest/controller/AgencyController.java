package com.morenomjc.transit.staticgtfs.api.rest.controller;

import com.morenomjc.transit.staticgtfs.api.rest.dto.AgencyDto;
import com.morenomjc.transit.staticgtfs.api.rest.mapper.AgencyDtoMapper;
import com.morenomjc.transit.staticgtfs.api.rest.resource.AgencyResource;
import com.morenomjc.transit.staticgtfs.api.spec.ApiData;
import com.morenomjc.transit.staticgtfs.api.spec.ApiDocument;
import com.morenomjc.transit.staticgtfs.api.spec.ApiResource;
import com.morenomjc.transit.staticgtfs.api.spec.ApiResources;
import com.morenomjc.transit.staticgtfs.core.agency.AgencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AgencyController implements AgencyResource {

    private final AgencyService agencyService;
    private final AgencyDtoMapper agencyDtoMapper;

    @Override
    public ResponseEntity<ApiDocument> getAgencies() {
        log.info("Action: getAgencies");
        List<ApiData<AgencyDto>> data = toApiResources(
                agencyService.getAgencies().stream()
                        .map(agencyDtoMapper::toDto)
                        .collect(Collectors.toList())
        );
        return ResponseEntity.ok(new ApiResources<>(data, data.size(), selfLink(getClass())));
    }

    @Override
    public ResponseEntity<ApiDocument> getAgency(String agencyId) {
        log.info("Action: getAgency [{}]", agencyId);
        return ResponseEntity.ok(
                new ApiResource<>(
                        getResourceType(),
                        agencyDtoMapper.toDto(agencyService.getAgency(agencyId)),
                        selfLink(agencyId, getClass())
                )
        );
    }

    private List<ApiData<AgencyDto>> toApiResources(List<AgencyDto> agencies){
        return agencies.stream()
                .map(agency ->
                        new ApiData<>(
                                getResourceType(),
                                agency,
                                selfLink(agency.getId(), getClass())
                        )
                )
                .collect(Collectors.toList());
    }
}
