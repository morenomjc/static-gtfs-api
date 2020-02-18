package com.phakk.transit.staticgtfs.core;

import com.phakk.transit.staticgtfs.core.constants.BikesAllowed;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibility;
import com.phakk.transit.staticgtfs.core.trip.StopTime;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.core.trip.TripService;
import com.phakk.transit.staticgtfs.core.trip.TripServiceImpl;
import com.phakk.transit.staticgtfs.dataproviders.repository.trip.TripRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildStopTime;
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
        Trip expected = buildTrip();
        givenATrip(expected);

        Trip result = tripService.getTrip("1");

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGetTripStops(){
        StopTime stopTime = buildStopTime();
        givenTripStops(stopTime);

        List<StopTime> stopTimes = tripService.getStops("1");

        assertThat(stopTimes).hasSize(1);
        assertThat(stopTimes).contains(stopTime);
    }

    private void givenATrip(Trip trip){
        when(tripRepository.getTrip(anyString())).thenReturn(trip);
    }

    private void givenTripStops(StopTime stopTime){
        when(tripRepository.getStops(anyString())).thenReturn(
                Collections.singletonList(stopTime)
        );
    }

    private Trip buildTrip(){
        return Trip.builder()
                .routeId("1")
                .serviceId("1")
                .tripId("1")
                .headsign("headsign")
                .shortName("shortname")
                .directionId("1")
                .blockId("1")
                .shapeId("1")
                .wheelchairAccessible(WheelchairAccessibility.WA_1_ACCESSIBLE)
                .bikesAllowed(BikesAllowed.BIKES_ALLOWED_1)
                .build();
    }
}
