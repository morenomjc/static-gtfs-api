package com.phakk.transit.staticgtfs.dataproviders.trip;

import com.phakk.transit.staticgtfs.core.constants.BikesAllowed;
import com.phakk.transit.staticgtfs.core.constants.DropOffType;
import com.phakk.transit.staticgtfs.core.constants.PickupType;
import com.phakk.transit.staticgtfs.core.constants.Timepoint;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibility;
import com.phakk.transit.staticgtfs.core.exception.ConstantsMappingException;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.trip.StopTime;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.StopTimeJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.trip.StopTimeEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.trip.TripEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.trip.TripRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.trip.TripRepositoryImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildStopTimeEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Import(TripRepositoryTest.TripTestConfiguration.class)
@RunWith(SpringRunner.class)
public class TripRepositoryTest {

    private TripRepository tripRepository;

    @Mock
    private TripJpaRepository tripJpaRepository;

    @Autowired
    private TripEntityMapper tripEntityMapper;

    @Mock
    private StopTimeJpaRepository stopTimeJpaRepository;

    @Autowired
    private StopTimeEntityMapper stopTimeEntityMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        tripRepository = new TripRepositoryImpl(tripJpaRepository, tripEntityMapper, stopTimeJpaRepository, stopTimeEntityMapper);
    }

    @TestConfiguration
    static class TripTestConfiguration {
        @Bean
        public TripEntityMapper tripEntityMapper(){
            return Mappers.getMapper(TripEntityMapper.class);
        }

        @Bean
        public StopTimeEntityMapper stopTimeEntityMapper(){
            return Mappers.getMapper(StopTimeEntityMapper.class);
        }
    }

    @Test
    public void testIfTripIsConvertedProperly(){
        whenTripExists();

        Trip trip = tripRepository.getTrip("1");

        assertThat(trip).isNotNull();
        assertThat(trip.getRouteId()).isEqualTo("1");
        assertThat(trip.getServiceId()).isEqualTo("1");
        assertThat(trip.getTripId()).isEqualTo("1");
        assertThat(trip.getHeadsign()).isEqualTo("headsign");
        assertThat(trip.getShortName()).isEqualTo("shortname");
        assertThat(trip.getDirectionId()).isEqualTo("1");
        assertThat(trip.getBlockId()).isEqualTo("1");
        assertThat(trip.getShapeId()).isEqualTo("1");
        assertThat(trip.getWheelchairAccessible()).isEqualTo(WheelchairAccessibility.WA_1_ACCESSIBLE);
        assertThat(trip.getBikesAllowed()).isEqualTo(BikesAllowed.BIKES_ALLOWED_1);
    }

    @Test
    public void testWhenWheelchairAccessibilityIsNull(){
        expectedException.expect(ConstantsMappingException.class);
        expectedException.expectMessage(equalTo("Failed to map wheelchair accessibility details"));

        whenTripExistsWithNullWA();

        tripRepository.getTrip("1");
    }

    @Test
    public void testWhenBikesAllowedIsNull(){
        expectedException.expect(ConstantsMappingException.class);
        expectedException.expectMessage(equalTo("Failed to map bikes allowed details"));

        whenTripExistsWithNullBA();

        tripRepository.getTrip("1");
    }

    @Test
    public void testWhenTripNotFound(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Trip not found."));

        whenTripNotFound();

        tripRepository.getTrip("1");
    }

    @Test
    public void testIfStopTimeIsConvertedProperly(){
        whenTripStopsExist();

        List<StopTime> stopTimes = tripRepository.getStops("1");
        assertThat(stopTimes).hasSize(1);

        StopTime stopTime = stopTimes.get(0);
        assertThat(stopTime.getTripId()).isEqualTo("1");
        assertThat(stopTime.getArrivalTime()).isEqualTo(LocalTime.of(8, 0, 0));
        assertThat(stopTime.getDepartureTime()).isEqualTo(LocalTime.of(8, 30, 0));
        assertThat(stopTime.getStopId()).isEqualTo("1");
        assertThat(stopTime.getStopSequence()).isEqualTo(1);
        assertThat(stopTime.getStopHeadsign()).isEqualTo("headsign");
        assertThat(stopTime.getPickupType()).isEqualTo(PickupType.PT_0_REGULAR);
        assertThat(stopTime.getDropOffType()).isEqualTo(DropOffType.DOT_0_REGULAR);
        assertThat(stopTime.getDistanceTraveled()).isEqualTo(1.5);
        assertThat(stopTime.getTimepoint()).isEqualTo(Timepoint.TP_0_APPROXIMATE);
    }

    @Test
    public void testWhenPickupTypeIsNull(){
        expectedException.expect(ConstantsMappingException.class);
        expectedException.expectMessage(equalTo("Failed to map pickup type"));

        whenTripStopsExistWillNullPickupType();

        tripRepository.getStops("1");
    }

    @Test
    public void testWhenDropOffTypeIsNull(){
        expectedException.expect(ConstantsMappingException.class);
        expectedException.expectMessage(equalTo("Failed to map drop off type"));

        whenTripStopsExistWillNullDropOffType();

        tripRepository.getStops("1");
    }

    @Test
    public void testWhenTimepointIsNull(){
        expectedException.expect(ConstantsMappingException.class);
        expectedException.expectMessage(equalTo("Failed to map timepoint"));

        whenTripStopsExistWillNullTimepoint();

        tripRepository.getStops("1");
    }

    private void whenTripNotFound(){
        when(tripJpaRepository.findByTripId(anyString())).thenReturn(null);
    }

    private void whenTripExists(){
        when(tripJpaRepository.findByTripId(anyString())).thenReturn(buildTripEntity());
    }

    private void whenTripExistsWithNullWA(){
        when(tripJpaRepository.findByTripId(anyString())).thenReturn(buildTripEntityNullWA());
    }

    private void whenTripExistsWithNullBA(){
        when(tripJpaRepository.findByTripId(anyString())).thenReturn(buildTripEntityNullBA());
    }

    private void whenTripStopsExist(){
        when(stopTimeJpaRepository.findAllByTripId(anyString())).thenReturn(
                Collections.singletonList(buildStopTimeEntity())
        );
    }

    private void whenTripStopsExistWillNullPickupType(){
        when(stopTimeJpaRepository.findAllByTripId(anyString())).thenReturn(
                Collections.singletonList(buildStopTimeEntityWillNullPickupType())
        );
    }

    private void whenTripStopsExistWillNullDropOffType(){
        when(stopTimeJpaRepository.findAllByTripId(anyString())).thenReturn(
                Collections.singletonList(buildStopTimeEntityWillNullDropOffType())
        );
    }

    private void whenTripStopsExistWillNullTimepoint(){
        when(stopTimeJpaRepository.findAllByTripId(anyString())).thenReturn(
                Collections.singletonList(buildStopTimeEntityWillNullTimepoint())
        );
    }

    private TripEntity buildTripEntity(){
        TripEntity tripEntity = new TripEntity();
        tripEntity.setRouteId("1");
        tripEntity.setServiceId("1");
        tripEntity.setTripId("1");
        tripEntity.setHeadsign("headsign");
        tripEntity.setShortName("shortname");
        tripEntity.setDirectionId("1");
        tripEntity.setBlockId("1");
        tripEntity.setShapeId("1");
        tripEntity.setWheelchairAccessible("1");
        tripEntity.setBikesAllowed("1");

        return tripEntity;
    }


    private TripEntity buildTripEntityNullWA(){
        TripEntity tripEntity = buildTripEntity();
        tripEntity.setWheelchairAccessible(null);

        return tripEntity;
    }

    private TripEntity buildTripEntityNullBA(){
        TripEntity tripEntity = buildTripEntity();
        tripEntity.setBikesAllowed(null);

        return tripEntity;
    }

    private StopTimeEntity buildStopTimeEntityWillNullPickupType(){
        StopTimeEntity stopTimeEntity = buildStopTimeEntity();
        stopTimeEntity.setPickupType(null);
        return stopTimeEntity;
    }

    private StopTimeEntity buildStopTimeEntityWillNullDropOffType(){
        StopTimeEntity stopTimeEntity = buildStopTimeEntity();
        stopTimeEntity.setDropOffType(null);
        return stopTimeEntity;
    }

    private StopTimeEntity buildStopTimeEntityWillNullTimepoint(){
        StopTimeEntity stopTimeEntity = buildStopTimeEntity();
        stopTimeEntity.setTimepoint(null);
        return stopTimeEntity;
    }
}
