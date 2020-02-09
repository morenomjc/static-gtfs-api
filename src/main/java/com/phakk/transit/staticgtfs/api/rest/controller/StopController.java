package com.phakk.transit.staticgtfs.api.rest.controller;

import com.phakk.transit.staticgtfs.api.rest.mapper.StopDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.resource.StopResource;
import com.phakk.transit.staticgtfs.api.spec.ApiData;
import com.phakk.transit.staticgtfs.api.spec.ApiTemplate;
import com.phakk.transit.staticgtfs.core.stop.StopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StopController implements StopResource {

    private StopService stopService;
    private StopDtoMapper stopDtoMapper;

    public StopController(StopService stopService, StopDtoMapper stopDtoMapper) {
        this.stopService = stopService;
        this.stopDtoMapper = stopDtoMapper;
    }

    @Override
    public ResponseEntity<ApiTemplate> getStop(String id) {
        log.info("Action: getStop [{}]", id);
        return ResponseEntity.ok(
                new ApiData<>(stopDtoMapper.toDto(stopService.getStop(id))
        ));
    }
}
