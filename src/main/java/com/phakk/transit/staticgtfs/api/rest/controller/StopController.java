package com.phakk.transit.staticgtfs.api.rest.controller;

import com.phakk.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.phakk.transit.staticgtfs.api.rest.dto.StopDto;
import com.phakk.transit.staticgtfs.api.rest.resource.StopResource;
import com.phakk.transit.staticgtfs.api.spec.ApiData;
import com.phakk.transit.staticgtfs.api.spec.ApiTemplate;
import com.phakk.transit.staticgtfs.core.constants.StopTypeEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairBoardingEnum;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.core.stop.StopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class StopController implements StopResource {

    private StopService stopService;

    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @Override
    public ResponseEntity<ApiTemplate> getStop(String id) {
        log.info("Action: getStop [{}]", id);
        return ResponseEntity.ok(
                mapToApiDto(
                        mapToDto(stopService.getStop(id))
                )
        );
    }

    private StopDto mapToDto(Stop stop) {
        return StopDto.builder()
                .id(stop.getId())
                .code(stop.getCode())
                .name(stop.getName())
                .desc(stop.getDesc())
                .lat(stop.getLat())
                .lon(stop.getLon())
                .zoneId(stop.getZoneId())
                .url(stop.getUrl())
                .locationType(mapStopType(stop.getType()))
                .parentStation(stop.getParentStation())
                .stopTimezone(stop.getTimezone())
                .wheelchairBoarding(mapWheelChairBoarding(stop.getWheelchairBoarding()))
                .levelId(stop.getLevelId())
                .platformCode(stop.getPlatformCode())
                .build();
    }

    private DataTypeDto mapStopType(StopTypeEnum stopTypeEnum){
        if (Objects.isNull(stopTypeEnum)){
            return null;
        }
        return DataTypeDto.builder()
                .id(stopTypeEnum.getId())
                .desc(stopTypeEnum.getDescription())
                .build();
    }

    private DataTypeDto mapWheelChairBoarding(WheelchairBoardingEnum wbEnum){
        if (Objects.isNull(wbEnum)){
            return null;
        }
        return DataTypeDto.builder()
                .id(wbEnum.getId())
                .desc(wbEnum.getDescription())
                .build();
    }

    private ApiData<StopDto> mapToApiDto(StopDto stopDto){
        return new ApiData<>(stopDto);
    }
}
