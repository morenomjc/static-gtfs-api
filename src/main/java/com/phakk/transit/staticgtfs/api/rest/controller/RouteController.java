package com.phakk.transit.staticgtfs.api.rest.controller;

import com.phakk.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.phakk.transit.staticgtfs.api.rest.dto.RouteDto;
import com.phakk.transit.staticgtfs.api.rest.resource.RouteResource;
import com.phakk.transit.staticgtfs.api.spec.ApiData;
import com.phakk.transit.staticgtfs.api.spec.ApiTemplate;
import com.phakk.transit.staticgtfs.core.constants.RouteTypeEnum;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.core.route.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class RouteController implements RouteResource {

    private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public ResponseEntity<ApiTemplate> getRoute(String id) {
        return ResponseEntity.ok(
                mapToApiDto(
                        mapToDto(routeService.getRoute(id))
                )
        );
    }

    private RouteDto mapToDto(Route route){
        return new RouteDto(
                route.getId(),
                route.getAgency(),
                route.getShortName(),
                route.getLongName(),
                route.getDesc(),
                mapRouteType(route.getType()),
                route.getUrl(),
                route.getColor(),
                route.getTextColor(),
                route.getSortOrder()
        );
    }

    private DataTypeDto mapRouteType(RouteTypeEnum routeTypeEnum){
        if (Objects.isNull(routeTypeEnum)){
            log.warn("Failed to map route type");
            return null;
        }
        return DataTypeDto.builder()
                .id(routeTypeEnum.getId())
                .desc(routeTypeEnum.getDescription())
                .build();
    }

    private ApiData<RouteDto> mapToApiDto(RouteDto routeDto){
        return new ApiData<>(routeDto);
    }
}
