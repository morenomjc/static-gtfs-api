package com.phakk.transit.staticgtfs.api.rest;

import com.phakk.transit.staticgtfs.api.rest.controller.TripController;
import com.phakk.transit.staticgtfs.api.rest.mapper.CalendarDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.mapper.RouteDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.mapper.TripDtoMapper;
import com.phakk.transit.staticgtfs.core.calendar.CalendarService;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.route.RouteService;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.core.trip.TripService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildCalendar;
import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildRoute;
import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildTrip;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { TripController.class })
@Import(TripControllerTest.TripTestConfiguration.class)
@RunWith(SpringRunner.class)
public class TripControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService tripService;
    @MockBean
    private RouteService routeService;
    @MockBean
    private CalendarService calendarService;

    @Autowired
    private TripDtoMapper tripDtoMapper;
    @Autowired
    private RouteDtoMapper routeDtoMapper;
    @Autowired
    private CalendarDtoMapper calendarDtoMapper;

    @TestConfiguration
    static class TripTestConfiguration {
        @Bean
        public TripDtoMapper tripDtoMapper(){
            return Mappers.getMapper(TripDtoMapper.class);
        }

        @Bean
        public RouteDtoMapper routeDtoMapper(){
            return Mappers.getMapper(RouteDtoMapper.class);
        }

        @Bean
        public CalendarDtoMapper calendarDtoMapper(){
            return Mappers.getMapper(CalendarDtoMapper.class);
        }
    }

    @Test
    public void testGetTripByIdEndpoint() throws Exception{
        givenTrip(buildTrip());

        this.mockMvc.perform(get("/trips/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.data.relationships.route.attributes").isNotEmpty()
        ).andExpect(
                jsonPath("$.data.relationships.schedule.attributes").isNotEmpty()
        ).andExpect(
                content().json("{\n" +
                        "    \"meta\": {\n" +
                        "        \"api\": {\n" +
                        "            \"version\": \"v1\"\n" +
                        "        },\n" +
                        "        \"gtfs\": {\n" +
                        "            \"static\": \"v1.0\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"data\": {\n" +
                        "        \"type\": \"trips\",\n" +
                        "        \"attributes\": {\n" +
                        "            \"route_id\": \"r1\",\n" +
                        "            \"service_id\": \"s1\",\n" +
                        "            \"trip_id\": \"t1\",\n" +
                        "            \"trip_headsign\": \"headsign\",\n" +
                        "            \"trip_short_name\": \"shortName\",\n" +
                        "            \"direction_id\": \"directionId\",\n" +
                        "            \"block_id\": \"blockId\",\n" +
                        "            \"shape_id\": \"shapeId\",\n" +
                        "            \"wheelchair_accessible\": {\n" +
                        "                \"id\": \"1\",\n" +
                        "                \"desc\": \"Accessible\"\n" +
                        "            },\n" +
                        "            \"bikes_allowed\": {\n" +
                        "                \"id\": \"1\",\n" +
                        "                \"desc\": \"Bikes Allowed\"\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}")
        );
    }

    @Test
    public void testGetTripWhenNotFound() throws Exception{
        when(tripService.getTrip(anyString())).thenThrow(new DataNotFoundException("Trip not found"));

        this.mockMvc.perform(get("/trips/-1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isNotFound()
        ).andExpect(
                content().json("{\n" +
                        "    \"meta\": {\n" +
                        "        \"api\": {\n" +
                        "            \"version\": \"v1\"\n" +
                        "        },\n" +
                        "        \"gtfs\": {\n" +
                        "            \"static\": \"v1.0\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"error\": {\n" +
                        "        \"status\": 404,\n" +
                        "        \"code\": \"404.0\",\n" +
                        "        \"title\": \"Resource Not Found\",\n" +
                        "        \"detail\": \"Trip not found\"\n" +
                        "    }\n" +
                        "}")
        );
    }

    private void givenTrip(Trip trip){
        when(tripService.getTrip(anyString())).thenReturn(trip);
        when(routeService.getRoute(anyString())).thenReturn(buildRoute());
        when(calendarService.getCalendar(anyString())).thenReturn(buildCalendar());
    }
}
