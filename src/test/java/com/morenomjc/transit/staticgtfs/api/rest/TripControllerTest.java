package com.morenomjc.transit.staticgtfs.api.rest;

import com.morenomjc.transit.staticgtfs.api.rest.controller.TripController;
import com.morenomjc.transit.staticgtfs.api.rest.dto.CalendarDto;
import com.morenomjc.transit.staticgtfs.api.rest.dto.FrequencyDto;
import com.morenomjc.transit.staticgtfs.api.rest.dto.StopTimeDto;
import com.morenomjc.transit.staticgtfs.api.rest.dto.TripDto;
import com.morenomjc.transit.staticgtfs.api.rest.mapper.CalendarDtoMapperImpl;
import com.morenomjc.transit.staticgtfs.api.rest.mapper.CommonDtoMapperImpl;
import com.morenomjc.transit.staticgtfs.api.rest.mapper.FrequencyDtoMapperImpl;
import com.morenomjc.transit.staticgtfs.api.rest.mapper.RouteDtoMapperImpl;
import com.morenomjc.transit.staticgtfs.api.rest.mapper.StopDtoMapperImpl;
import com.morenomjc.transit.staticgtfs.api.rest.mapper.StopTimeDtoMapperImpl;
import com.morenomjc.transit.staticgtfs.api.rest.mapper.TripDtoMapperImpl;
import com.morenomjc.transit.staticgtfs.core.calendar.CalendarService;
import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.core.frequency.FrequencyService;
import com.morenomjc.transit.staticgtfs.core.route.RouteService;
import com.morenomjc.transit.staticgtfs.core.stop.StopService;
import com.morenomjc.transit.staticgtfs.core.trip.Trip;
import com.morenomjc.transit.staticgtfs.core.trip.TripService;
import com.morenomjc.transit.staticgtfs.utils.JsonAssertionUtil;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@WebMvcTest(controllers = {TripController.class})
@Import(value = {
        CommonDtoMapperImpl.class, TripDtoMapperImpl.class, RouteDtoMapperImpl.class, CalendarDtoMapperImpl.class,
        StopTimeDtoMapperImpl.class, StopDtoMapperImpl.class, FrequencyDtoMapperImpl.class
})
class TripControllerTest extends JsonAssertionUtil {

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
  @MockBean
  private FrequencyService frequencyService;

  @Test
  void testGetTripsByRouteIdEndpoint() throws Exception {
    givenTrips(TestDataProvider.buildTrip());

    this.mockMvc.perform(get("/trips")
        .contentType(MediaType.APPLICATION_JSON)
        .param("routeId", "1")
    ).andExpect(
        matchAll(
            status().isOk(),
            jsonPath("$.data", hasSize(1)),
            jsonPath("$.data[0].trip").isNotEmpty(),
            jsonPath("$.data[0].trip.attributes").isNotEmpty(),
            assertHasJsonFields("$.data[0].trip.attributes", TripDto.class),
            jsonPath("$.data[0].schedule").isNotEmpty(),
            jsonPath("$.data[0].schedule.attributes").isNotEmpty(),
            assertHasJsonFields("$.data[0].schedule.attributes", CalendarDto.class),
            jsonPath("$.data[0].stops").isNotEmpty(),
            jsonPath("$.data[0].stops", hasSize(1)),
            jsonPath("$.data[0].stops[0].attributes").isNotEmpty(),
            assertHasJsonFields("$.data[0].stops[0].attributes", StopTimeDto.class),
            jsonPath("$.data[0].frequency").isNotEmpty(),
            jsonPath("$.data[0].frequency.attributes").isNotEmpty(),
            assertHasJsonFields("$.data[0].frequency.attributes", FrequencyDto.class)
        )
    );
  }

  @Test
  void testGetTripsByRouteIdEndpointWithNoRequestParam() throws Exception {
    this.mockMvc.perform(get("/trips")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        status().isBadRequest()
    );
  }

  @Test
  void testGetTripByIdEndpoint() throws Exception {
    givenTrip(TestDataProvider.buildTrip());

    this.mockMvc.perform(get("/trips/1")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().isOk(),
            jsonPath("$.data.relationships.route.attributes").isNotEmpty(),
            jsonPath("$.data.relationships.schedule.attributes").isNotEmpty(),
            jsonPath("$.data.links", hasSize(4)),
            jsonPath("$.data.type", is("trips")),
            jsonPath("$.data.attributes").isNotEmpty(),
            assertHasJsonFields("$.data.attributes", TripDto.class)
        )
    );
  }

  @Test
  void testGetTripWhenNotFound() throws Exception {
    when(tripService.getTrip(anyString())).thenThrow(new DataNotFoundException("Trip not found"));

    this.mockMvc.perform(get("/trips/-1")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().isNotFound(),
            jsonPath("$.error").isNotEmpty(),
            jsonPath("$.error.status", is(404)),
            jsonPath("$.error.detail", is("Trip not found"))
        )
    );
  }

  @Test
  void testGetStopTimesEndpoint() throws Exception {
    givenTripStops();

    this.mockMvc.perform(get("/trips/1/stops")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().isOk(),
            jsonPath("$.data", hasSize(1)),
            jsonPath("$.count", is(1)),
            jsonPath("$.data[0].type", is("stoptimes")),
            jsonPath("$.data[0].attributes").isNotEmpty(),
            jsonPath("$.data[0].relationships.stop.attributes").isNotEmpty(),
            assertHasJsonFields("$.data[0].attributes", StopTimeDto.class)
        )
    );
  }

  private void givenTrips(Trip trip) {
    when(tripService.getTripsByRouteId(anyString())).thenReturn(Collections.singletonList(trip));
    when(calendarService.getCalendar(anyString())).thenReturn(TestDataProvider.buildCalendar());
    when(tripService.getStops(anyString()))
        .thenReturn(singletonList(TestDataProvider.buildStopTime()));
    when(frequencyService.getFrequency(anyString())).thenReturn(TestDataProvider.buildFrequency());
  }

  private void givenTrip(Trip trip) {
    when(tripService.getTrip(anyString())).thenReturn(trip);
    when(routeService.getRoute(anyString())).thenReturn(TestDataProvider.buildRoute());
    when(calendarService.getCalendar(anyString())).thenReturn(TestDataProvider.buildCalendar());
  }

  private void givenTripStops() {
    when(tripService.getStops(anyString()))
        .thenReturn(singletonList(TestDataProvider.buildStopTime()));
    when(stopService.getStop(anyString())).thenReturn(TestDataProvider.buildStop());
  }
}
