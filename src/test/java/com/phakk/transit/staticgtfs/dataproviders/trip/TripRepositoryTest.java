package com.phakk.transit.staticgtfs.dataproviders.trip;

import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.trip.StopTime;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.StopTimeJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.stoptime.StopTimeEntityMapper;
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

import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildEnumValueBikesAllowed;
import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildEnumValueDirectionId;
import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildEnumValueDropOffType;
import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildEnumValuePickupType;
import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildEnumValueTimepoint;
import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildEnumValueWheelchairAccessible;
import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildStopTimeEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@Import(TripRepositoryTest.TripTestConfiguration.class)
@RunWith(SpringRunner.class)
public class TripRepositoryTest {

    private TripRepository tripRepository;

    @Mock
    private TripJpaRepository tripJpaRepository;
    @Mock
    private EnumValueRepository enumValueRepository;

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
        tripRepository = new TripRepositoryImpl(tripJpaRepository, tripEntityMapper, stopTimeJpaRepository, stopTimeEntityMapper, enumValueRepository);
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
        whenDirectionIdIsSearched();
        whenWheelchairAccessibleIsSearched();
        whenBikesAllowedIsSearched();

        Trip trip = tripRepository.getTrip("1");
        EnumValue direction = buildEnumValueDirectionId();
        EnumValue wheelchairAccessible = buildEnumValueWheelchairAccessible();
        EnumValue bikesAllowed = buildEnumValueBikesAllowed();

        assertThat(trip).isNotNull();
        assertThat(trip.getRouteId()).isEqualTo("1");
        assertThat(trip.getServiceId()).isEqualTo("1");
        assertThat(trip.getTripId()).isEqualTo("1");
        assertThat(trip.getHeadsign()).isEqualTo("headsign");
        assertThat(trip.getShortName()).isEqualTo("shortname");
        assertThat(trip.getDirectionId()).isEqualTo(direction);
        assertThat(trip.getBlockId()).isEqualTo("1");
        assertThat(trip.getShapeId()).isEqualTo("1");
        assertThat(trip.getWheelchairAccessible()).isEqualTo(wheelchairAccessible);
        assertThat(trip.getBikesAllowed()).isEqualTo(bikesAllowed);
    }

    @Test
    public void testWhenDirectionIdIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenTripExistsWithNullDirection();
        whenInvalidEnumValueIsSearched();

        tripRepository.getTrip("1");
    }

    @Test
    public void testWhenWheelchairAccessibilityIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenTripExistsWithNullWA();
        whenInvalidEnumValueIsSearched();

        tripRepository.getTrip("1");
    }

    @Test
    public void testWhenBikesAllowedIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenTripExistsWithNullBA();
        whenInvalidEnumValueIsSearched();

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
        whenPickupTypeIsSearched();
        whenDropOffTypeIsSearched();
        whenTimepointIsSearched();

        List<StopTime> stopTimes = tripRepository.getStops("1");
        assertThat(stopTimes).hasSize(1);

        StopTime stopTime = stopTimes.get(0);
        EnumValue pickupType = buildEnumValuePickupType();
        EnumValue dropOffType = buildEnumValueDropOffType();
        EnumValue timepoint = buildEnumValueTimepoint();

        assertThat(stopTime.getTripId()).isEqualTo("1");
        assertThat(stopTime.getArrivalTime()).isEqualTo(LocalTime.of(8, 0, 0));
        assertThat(stopTime.getDepartureTime()).isEqualTo(LocalTime.of(8, 30, 0));
        assertThat(stopTime.getStopId()).isEqualTo("1");
        assertThat(stopTime.getStopSequence()).isEqualTo(1);
        assertThat(stopTime.getStopHeadsign()).isEqualTo("headsign");
        assertThat(stopTime.getPickupType()).isEqualTo(pickupType);
        assertThat(stopTime.getDropOffType()).isEqualTo(dropOffType);
        assertThat(stopTime.getDistanceTraveled()).isEqualTo(1.5);
        assertThat(stopTime.getTimepoint()).isEqualTo(timepoint);
    }

    @Test
    public void testWhenPickupTypeIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenTripStopsExistWillNullPickupType();
        whenInvalidEnumValueIsSearched();

        tripRepository.getStops("1");
    }

    @Test
    public void testWhenDropOffTypeIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenTripStopsExistWillNullDropOffType();
        whenInvalidEnumValueIsSearched();

        tripRepository.getStops("1");
    }

    @Test
    public void testWhenTimepointIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenTripStopsExistWillNullTimepoint();
        whenInvalidEnumValueIsSearched();

        tripRepository.getStops("1");
    }

    private void whenTripNotFound(){
        when(tripJpaRepository.findByTripId(anyString())).thenReturn(null);
    }

    private void whenTripExists(){
        when(tripJpaRepository.findByTripId(anyString())).thenReturn(buildTripEntity());
    }

    private void whenTripExistsWithNullDirection(){
        when(tripJpaRepository.findByTripId(anyString())).thenReturn(buildTripEntityNullDirection());
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
        tripEntity.setDirectionId("0");
        tripEntity.setBlockId("1");
        tripEntity.setShapeId("1");
        tripEntity.setWheelchairAccessible("1");
        tripEntity.setBikesAllowed("2");

        return tripEntity;
    }

    private TripEntity buildTripEntityNullDirection(){
        TripEntity tripEntity = buildTripEntity();
        tripEntity.setDirectionId(null);

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

    private void whenDirectionIdIsSearched(){
        when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("0")))
                .thenReturn(buildEnumValueDirectionId());
    }

    private void whenWheelchairAccessibleIsSearched(){
        when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("1")))
                .thenReturn(buildEnumValueWheelchairAccessible());
    }

    private void whenBikesAllowedIsSearched(){
        when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("2")))
                .thenReturn(buildEnumValueBikesAllowed());
    }

    private void whenPickupTypeIsSearched(){
        when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("0")))
                .thenReturn(buildEnumValuePickupType());
    }

    private void whenDropOffTypeIsSearched(){
        when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("1")))
                .thenReturn(buildEnumValueDropOffType());
    }

    private void whenTimepointIsSearched(){
        when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("2")))
                .thenReturn(buildEnumValueTimepoint());
    }

    private void whenInvalidEnumValueIsSearched(){
        when(enumValueRepository.findEnumValue(anyString(), anyString(), eq(null)))
                .thenThrow(new DataNotFoundException("Enum value not found."));
    }
}
