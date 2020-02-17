package com.phakk.transit.staticgtfs.core;

import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.core.trip.TripService;
import com.phakk.transit.staticgtfs.core.trip.TripServiceImpl;
import com.phakk.transit.staticgtfs.dataproviders.repository.trip.TripRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildTrip;
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

    private void givenATrip(Trip trip){
        when(tripRepository.getTrip(anyString())).thenReturn(trip);
    }

}
