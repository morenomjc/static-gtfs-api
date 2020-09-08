package com.morssscoding.transit.staticgtfs.api.rest;

import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import com.morssscoding.transit.staticgtfs.api.rest.controller.TripController;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.CalendarDtoMapper;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.RouteDtoMapper;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.StopDtoMapper;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.StopTimeDtoMapper;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.TripDtoMapper;
import com.morssscoding.transit.staticgtfs.core.calendar.CalendarService;
import com.morssscoding.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morssscoding.transit.staticgtfs.core.route.RouteService;
import com.morssscoding.transit.staticgtfs.core.stop.StopService;
import com.morssscoding.transit.staticgtfs.core.trip.Trip;
import com.morssscoding.transit.staticgtfs.core.trip.TripService;
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

import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
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
    @MockBean
    private StopService stopService;

    @Autowired
    private TripDtoMapper tripDtoMapper;
    @Autowired
    private RouteDtoMapper routeDtoMapper;
    @Autowired
    private CalendarDtoMapper calendarDtoMapper;
    @Autowired
    private StopTimeDtoMapper stopTimeDtoMapper;
    @Autowired
    private StopDtoMapper stopDtoMapper;

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

        @Bean
        public StopTimeDtoMapper stopTimeDtoMapper(){
            return Mappers.getMapper(StopTimeDtoMapper.class);
        }

        @Bean
        public StopDtoMapper stopDtoMapper(){
            return Mappers.getMapper(StopDtoMapper.class);
        }
    }

    @Test
    public void testGetTripsByRouteIdEndpoint() throws Exception{
        givenTrips(TestDataProvider.buildTrip());

        this.mockMvc.perform(get("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .param("routeId", "1")
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.data", hasSize(1))
        ).andExpect(
                jsonPath("$.data[0].trip").isNotEmpty()
        ).andExpect(
                jsonPath("$.data[0].schedule").isNotEmpty()
        ).andExpect(
                jsonPath("$.data[0].stops").isNotEmpty()
        ).andExpect(
                jsonPath("$.data[0].stops", hasSize(1))
        );
    }

    @Test
    public void testGetTripsByRouteIdEndpointWithNoRequestParam() throws Exception{
        this.mockMvc.perform(get("/trips")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().is4xxClientError()
        );
    }

    @Test
    public void testGetTripByIdEndpoint() throws Exception{
        givenTrip(TestDataProvider.buildTrip());

        this.mockMvc.perform(get("/trips/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.data.relationships.route.attributes").isNotEmpty()
        ).andExpect(
                jsonPath("$.data.relationships.schedule.attributes").isNotEmpty()
        ).andExpect(
                jsonPath("$.data.links", hasSize(4))
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
                        "            \"route_id\": \"1\",\n" +
                        "            \"service_id\": \"1\",\n" +
                        "            \"trip_id\": \"t1\",\n" +
                        "            \"trip_headsign\": \"headsign\",\n" +
                        "            \"trip_short_name\": \"shortName\",\n" +
                        "            \"direction_id\": {\n" +
                        "                \"code\": \"0\",\n" +
                        "                \"desc\": \"Inbound\"\n" +
                        "            },\n" +
                        "            \"block_id\": \"blockId\",\n" +
                        "            \"shape_id\": \"shapeId\",\n" +
                        "            \"wheelchair_accessible\": {\n" +
                        "                \"code\": \"1\",\n" +
                        "                \"desc\": \"Possible For Only One\"\n" +
                        "            },\n" +
                        "            \"bikes_allowed\": {\n" +
                        "                \"code\": \"2\",\n" +
                        "                \"desc\": \"Possible For Only One\"\n" +
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

    @Test
    public void testGetStopTimesEndpoint() throws Exception {
        givenTripStops();

        this.mockMvc.perform(get("/trips/1/stops")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.data", hasSize(1))
        ).andExpect(
                jsonPath("$.data[0].relationships.stop.attributes").isNotEmpty()
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
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"type\": \"stoptimes\",\n" +
                        "            \"attributes\": {\n" +
                        "                \"trip_id\": \"1\",\n" +
                        "                \"arrival_time\": \"08:00:00\",\n" +
                        "                \"departure_time\": \"08:30:00\",\n" +
                        "                \"stop_id\": \"1\",\n" +
                        "                \"stop_sequence\": 1,\n" +
                        "                \"stop_headsign\": \"headsign\",\n" +
                        "                \"pickup_type\": {\n" +
                        "                    \"code\": \"0\",\n" +
                        "                    \"desc\": \"Regular\"\n" +
                        "                },\n" +
                        "                \"drop_off_type\": {\n" +
                        "                    \"code\": \"1\",\n" +
                        "                    \"desc\": \"None\"\n" +
                        "                },\n" +
                        "                \"shape_dist_traveled\": 1.5,\n" +
                        "                \"timepoint\": {\n" +
                        "                    \"code\": \"2\",\n" +
                        "                    \"desc\": \"Exact\"\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}")
        );
    }

    private void givenTrips(Trip trip){
        when(tripService.getTripsByRouteId(anyString())).thenReturn(Collections.singletonList(trip));
        when(calendarService.getCalendar(anyString())).thenReturn(TestDataProvider.buildCalendar());
        when(tripService.getStops(anyString())).thenReturn(singletonList(TestDataProvider.buildStopTime()));
    }

    private void givenTrip(Trip trip){
        when(tripService.getTrip(anyString())).thenReturn(trip);
        when(routeService.getRoute(anyString())).thenReturn(TestDataProvider.buildRoute());
        when(calendarService.getCalendar(anyString())).thenReturn(TestDataProvider.buildCalendar());
    }

    private void givenTripStops(){
        when(tripService.getStops(anyString())).thenReturn(singletonList(TestDataProvider.buildStopTime()));
        when(stopService.getStop(anyString())).thenReturn(TestDataProvider.buildStop());
    }
}
