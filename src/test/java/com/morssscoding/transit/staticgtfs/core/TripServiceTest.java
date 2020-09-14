package com.morssscoding.transit.staticgtfs.core;

import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import com.morssscoding.transit.staticgtfs.core.trip.StopTime;
import com.morssscoding.transit.staticgtfs.core.trip.Trip;
import com.morssscoding.transit.staticgtfs.core.trip.TripService;
import com.morssscoding.transit.staticgtfs.core.trip.TripServiceImpl;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.trip.TripRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TripServiceTest {

    private TripService tripService;

    @Mock
    private TripRepository tripRepository;

    @Before
    public void setup(){
        tripService = new TripServiceImpl(tripRepository);
    }

    @Test
    public void testGetTripById(){
        Trip expected = TestDataProvider.buildTrip();
        givenATrip(expected);

        Trip result = tripService.getTrip("1");

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGetTripsByRouteId(){
        givenTrips(TestDataProvider.buildTrip());

        List<Trip> result = tripService.getTripsByRouteId("1");

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void testGetTripStops(){
        StopTime stopTime = TestDataProvider.buildStopTime();
        givenTripStops(stopTime);

        List<StopTime> stopTimes = tripService.getStops("1");

        assertThat(stopTimes).hasSize(1);
        assertThat(stopTimes).contains(stopTime);
    }

    private void givenATrip(Trip trip){
        when(tripRepository.getTrip(anyString())).thenReturn(trip);
    }

    private void givenTrips(Trip trip){
        when(tripRepository.getTripsByRouteId(anyString())).thenReturn(Collections.singletonList(trip));
    }

    private void givenTripStops(StopTime stopTime){
        when(tripRepository.getStops(anyString())).thenReturn(
                Collections.singletonList(stopTime)
        );
    }



}