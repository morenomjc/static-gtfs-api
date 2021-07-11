package com.morenomjc.transit.staticgtfs.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.morenomjc.transit.staticgtfs.core.trip.StopTime;
import com.morenomjc.transit.staticgtfs.core.trip.Trip;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import com.morenomjc.transit.staticgtfs.core.trip.TripService;
import com.morenomjc.transit.staticgtfs.core.trip.TripServiceImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.trip.TripRepository;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TripServiceTest {

  private TripService tripService;

  @Mock
  private TripRepository tripRepository;

  @BeforeAll
  public void setup() {
    tripService = new TripServiceImpl(tripRepository);
  }

  @Test
  public void testGetTripById() {
    Trip expected = TestDataProvider.buildTrip();
    givenATrip(expected);

    Trip result = tripService.getTrip("1");

    assertThat(result).isEqualTo(expected);
  }

  @Test
  public void testGetTripsByRouteId() {
    givenTrips(TestDataProvider.buildTrip());

    List<Trip> result = tripService.getTripsByRouteId("1");

    assertThat(result.size()).isEqualTo(1);
  }

  @Test
  public void testGetTripStops() {
    StopTime stopTime = TestDataProvider.buildStopTime();
    givenTripStops(stopTime);

    List<StopTime> stopTimes = tripService.getStops("1");

    assertThat(stopTimes).hasSize(1);
    assertThat(stopTimes).contains(stopTime);
  }

  private void givenATrip(Trip trip) {
    when(tripRepository.getTrip(anyString())).thenReturn(trip);
  }

  private void givenTrips(Trip trip) {
    when(tripRepository.getTripsByRouteId(anyString())).thenReturn(Collections.singletonList(trip));
  }

  private void givenTripStops(StopTime stopTime) {
    when(tripRepository.getStops(anyString())).thenReturn(
        Collections.singletonList(stopTime)
    );
  }

}
