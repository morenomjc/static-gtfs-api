package com.morssscoding.transit.staticgtfs.api.rest.controller;

import com.morssscoding.transit.staticgtfs.api.rest.resource.RouteResource;
import com.morssscoding.transit.staticgtfs.api.spec.ApiData;
import com.morssscoding.transit.staticgtfs.api.spec.ApiDocument;
import com.morssscoding.transit.staticgtfs.api.spec.ApiResource;
import com.morssscoding.transit.staticgtfs.api.spec.ApiResources;
import com.morssscoding.transit.staticgtfs.core.route.RouteService;
import com.morssscoding.transit.staticgtfs.api.rest.dto.RouteDto;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.RouteDtoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@AllArgsConstructor
public class RouteController implements RouteResource {

    private RouteService routeService;
    private RouteDtoMapper routeDtoMapper;

    @Override
    public ResponseEntity<ApiDocument> getRoute(String id) {
        return ResponseEntity.ok(
                new ApiResource<>(
                        getResourceType(),
                        routeDtoMapper.mapToDto(routeService.getRoute(id)),
                        selfLink(id, getClass())
                )
        );
    }

    @Override
    public ResponseEntity<ApiDocument> getRoutesByAgency(String agencyId) {
        log.info("Action: getRoutesByAgency:[{}]", agencyId);
        List<ApiData<RouteDto>> data = toApiResources(routeService.getRoutesByAgency(agencyId).stream()
                .map(route -> routeDtoMapper.mapToDto(route)).collect(Collectors.toList())
        );
        return ResponseEntity.ok(
                new ApiResources<>(data, data.size(), addQueryParamLink(agencyId)
        ));
    }

    private List<ApiData<RouteDto>> toApiResources(List<RouteDto> routes) {
        return routes.stream()
                .map(route ->
                        new ApiData<>(
                                getResourceType(),
                                route,
                                selfLink(route.getId(), getClass())
                        )
                ).collect(Collectors.toList());
    }

    private Link addQueryParamLink(String value) {
        UriTemplate uriTemplate = UriTemplate.of(
                linkTo(methodOn(getClass()).getRoutesByAgency(null)).withSelfRel().getHref(),
                new TemplateVariables(
                        new TemplateVariable("agencyId", TemplateVariable.VariableType.REQUEST_PARAM)
                )
        );
        return new Link(uriTemplate, value);
    }
}
