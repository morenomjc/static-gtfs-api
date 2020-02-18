package com.phakk.transit.staticgtfs.api.rest.controller;

import com.phakk.transit.staticgtfs.api.rest.dto.DataTypes;
import com.phakk.transit.staticgtfs.api.rest.dto.StopTimeDto;
import com.phakk.transit.staticgtfs.api.rest.dto.TripDto;
import com.phakk.transit.staticgtfs.api.rest.mapper.CalendarDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.mapper.RouteDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.mapper.StopTimeDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.mapper.TripDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.resource.TripResource;
import com.phakk.transit.staticgtfs.api.spec.ApiData;
import com.phakk.transit.staticgtfs.api.spec.ApiDocument;
import com.phakk.transit.staticgtfs.api.spec.ApiResource;
import com.phakk.transit.staticgtfs.api.spec.ApiResources;
import com.phakk.transit.staticgtfs.core.calendar.CalendarService;
import com.phakk.transit.staticgtfs.core.route.RouteService;
import com.phakk.transit.staticgtfs.core.trip.StopTime;
import com.phakk.transit.staticgtfs.core.trip.TripService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@AllArgsConstructor
public class TripController implements TripResource {

    private TripService tripService;
    private TripDtoMapper tripDtoMapper;
    private RouteService routeService;
    private RouteDtoMapper routeDtoMapper;
    private CalendarService calendarService;
    private CalendarDtoMapper calendarDtoMapper;
    private StopTimeDtoMapper stopTimeDtoMapper;

    @Override
    public ResponseEntity<ApiDocument> getTrip(String tripId) {
        log.info("Action: getTrip [{}]", tripId);
        TripDto tripDto = tripDtoMapper.toDto(tripService.getTrip(tripId));
        ApiResource<?> apiResource = new ApiResource<>(
                getResourceType(),
                tripDto,
                selfLink(tripId, getClass()),
                routeLink(tripDto.getRouteId()),
                calendarLink(tripDto.getServiceId())
        );
        apiResource.getData().addRelationship("route", buildRouteData(tripDto));
        apiResource.getData().addRelationship("schedule", buildCalendarData(tripDto));

        return ResponseEntity.ok(apiResource);
    }

    @Override
    public ResponseEntity<ApiDocument> getStopTimes(String tripId) {
        log.info("Action: getStopTimes by trip [{}]", tripId);

        List<StopTime> stops = tripService.getStops(tripId);
        return ResponseEntity.ok(new ApiResources<>(buildStopTimes(stops)));
    }

    private List<ApiData<StopTimeDto>> buildStopTimes(List<StopTime> stopTimes){
        return stopTimes.stream()
                .map(stopTime ->
                        new ApiData<>(
                                DataTypes.STOP_TIMES.getValue(),
                                stopTimeDtoMapper.toDto(stopTime),
                                stopLink(stopTime.getStopId()),
                                tripLink(stopTime.getTripId())
                        )
                ).collect(Collectors.toList());
    }

    private ApiData<?> buildRouteData(TripDto tripDto){
        return new ApiData<>(
                DataTypes.ROUTE.getValue(),
                routeDtoMapper.mapToDto(routeService.getRoute(tripDto.getRouteId())),
                selfLink(tripDto.getRouteId(), RouteController.class)
        );
    }

    private ApiData<?> buildCalendarData(TripDto tripDto){
        return new ApiData<>(
                DataTypes.CALENDAR.getValue(),
                calendarDtoMapper.toDto(calendarService.getCalendar(tripDto.getServiceId())),
                selfLink(tripDto.getServiceId(), CalendarController.class)
        );
    }

    private Link routeLink(String id){
        return  linkTo(RouteController.class).slash(id).withRel("route");
    }

    private Link calendarLink(String id){
        return  linkTo(CalendarController.class).slash(id).withRel("schedule");
    }

    private Link tripLink(String id){
        return  linkTo(TripController.class).slash(id).withRel("trip");
    }

    private Link stopLink(String id){
        return  linkTo(StopController.class).slash(id).withRel("stop");
    }
}
