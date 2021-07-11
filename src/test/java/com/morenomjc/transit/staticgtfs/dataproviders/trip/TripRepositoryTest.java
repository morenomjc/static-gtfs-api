package com.morenomjc.transit.staticgtfs.dataproviders.trip;

import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.core.mapper.CommonCoreDataMapperImpl;
import com.morenomjc.transit.staticgtfs.core.trip.StopTime;
import com.morenomjc.transit.staticgtfs.core.trip.Trip;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.StopTimeJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapperImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.stoptime.StopTimeEntityMapper;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.stoptime.StopTimeEntityMapperImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.trip.TripEntityMapper;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.trip.TripEntityMapperImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.trip.TripRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.trip.TripRepositoryImpl;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(value = {CommonCoreDataMapperImpl.class, EnumValueEntityMapperImpl.class, TripEntityMapperImpl.class, StopTimeEntityMapperImpl.class } )
class TripRepositoryTest {

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

  @BeforeAll
  void setup() {
    tripRepository = new TripRepositoryImpl(tripJpaRepository, stopTimeJpaRepository, enumValueRepository,
            tripEntityMapper, stopTimeEntityMapper);
  }

  @Test
  void testIfTripIsConvertedProperly() {
    whenTripExists();
    whenDirectionIdIsSearched();
    whenWheelchairAccessibleIsSearched();
    whenBikesAllowedIsSearched();

    Trip trip = tripRepository.getTrip("1");
    EnumValue direction = TestDataProvider.buildEnumValueDirectionId();
    EnumValue wheelchairAccessible = TestDataProvider.buildEnumValueWheelchairAccessible();
    EnumValue bikesAllowed = TestDataProvider.buildEnumValueBikesAllowed();

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
  void testGetTripsByRouteId() {
    whenTripsExist();

    List<Trip> trips = tripRepository.getTripsByRouteId("1");

    assertThat(trips.size()).isEqualTo(1);
  }

    /*@Test //TODO: fix
    void testWhenDirectionIdIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenTripExistsWithNullDirection();
        whenInvalidEnumValueIsSearched();

        tripRepository.getTrip("1");
    }*/

    /*@Test //TODO: fix
    void testWhenWheelchairAccessibilityIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenTripExistsWithNullWA();
        whenInvalidEnumValueIsSearched();

        tripRepository.getTrip("1");
    }*/

    /*@Test //TODO: fix
    void testWhenBikesAllowedIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenTripExistsWithNullBA();
        whenInvalidEnumValueIsSearched();

        tripRepository.getTrip("1");
    }*/

    /*@Test //TODO: fix
    void testWhenTripNotFound(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Trip not found."));

        whenTripNotFound();

        tripRepository.getTrip("1");
    }*/

  @Test
  void testIfStopTimeIsConvertedProperly() {
    whenTripStopsExist();
    whenPickupTypeIsSearched();
    whenDropOffTypeIsSearched();
    whenTimepointIsSearched();

    List<StopTime> stopTimes = tripRepository.getStops("1");
    assertThat(stopTimes).hasSize(1);

    StopTime stopTime = stopTimes.get(0);
    EnumValue pickupType = TestDataProvider.buildEnumValuePickupType();
    EnumValue dropOffType = TestDataProvider.buildEnumValueDropOffType();
    EnumValue timepoint = TestDataProvider.buildEnumValueTimepoint();

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

    /*@Test //TODO: fix
    void testWhenPickupTypeIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenTripStopsExistWillNullPickupType();
        whenInvalidEnumValueIsSearched();

        tripRepository.getStops("1");
    }*/

    /*@Test //TODO: fix
    void testWhenDropOffTypeIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenTripStopsExistWillNullDropOffType();
        whenInvalidEnumValueIsSearched();

        tripRepository.getStops("1");
    }*/

    /*@Test //TODO: fix
    void testWhenTimepointIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenTripStopsExistWillNullTimepoint();
        whenInvalidEnumValueIsSearched();

        tripRepository.getStops("1");
    }*/

  private void whenTripNotFound() {
    when(tripJpaRepository.findByTripId(anyString())).thenReturn(null);
  }

  private void whenTripExists() {
    when(tripJpaRepository.findByTripId(anyString())).thenReturn(buildTripEntity());
  }

  private void whenTripsExist() {
    when(tripJpaRepository.findAllByRouteId(anyString()))
        .thenReturn(Collections.singletonList(buildTripEntity()));
  }

  private void whenTripExistsWithNullDirection() {
    when(tripJpaRepository.findByTripId(anyString())).thenReturn(buildTripEntityNullDirection());
  }

  private void whenTripExistsWithNullWA() {
    when(tripJpaRepository.findByTripId(anyString())).thenReturn(buildTripEntityNullWA());
  }

  private void whenTripExistsWithNullBA() {
    when(tripJpaRepository.findByTripId(anyString())).thenReturn(buildTripEntityNullBA());
  }

  private void whenTripStopsExist() {
    when(stopTimeJpaRepository.findAllByTripId(anyString())).thenReturn(
        Collections.singletonList(TestDataProvider.buildStopTimeEntity())
    );
  }

  private void whenTripStopsExistWillNullPickupType() {
    when(stopTimeJpaRepository.findAllByTripId(anyString())).thenReturn(
        Collections.singletonList(buildStopTimeEntityWillNullPickupType())
    );
  }

  private void whenTripStopsExistWillNullDropOffType() {
    when(stopTimeJpaRepository.findAllByTripId(anyString())).thenReturn(
        Collections.singletonList(buildStopTimeEntityWillNullDropOffType())
    );
  }

  private void whenTripStopsExistWillNullTimepoint() {
    when(stopTimeJpaRepository.findAllByTripId(anyString())).thenReturn(
        Collections.singletonList(buildStopTimeEntityWillNullTimepoint())
    );
  }

  private TripEntity buildTripEntity() {
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

  private TripEntity buildTripEntityNullDirection() {
    TripEntity tripEntity = buildTripEntity();
    tripEntity.setDirectionId(null);

    return tripEntity;
  }

  private TripEntity buildTripEntityNullWA() {
    TripEntity tripEntity = buildTripEntity();
    tripEntity.setWheelchairAccessible(null);

    return tripEntity;
  }

  private TripEntity buildTripEntityNullBA() {
    TripEntity tripEntity = buildTripEntity();
    tripEntity.setBikesAllowed(null);

    return tripEntity;
  }

  private StopTimeEntity buildStopTimeEntityWillNullPickupType() {
    StopTimeEntity stopTimeEntity = TestDataProvider.buildStopTimeEntity();
    stopTimeEntity.setPickupType(null);
    return stopTimeEntity;
  }

  private StopTimeEntity buildStopTimeEntityWillNullDropOffType() {
    StopTimeEntity stopTimeEntity = TestDataProvider.buildStopTimeEntity();
    stopTimeEntity.setDropOffType(null);
    return stopTimeEntity;
  }

  private StopTimeEntity buildStopTimeEntityWillNullTimepoint() {
    StopTimeEntity stopTimeEntity = TestDataProvider.buildStopTimeEntity();
    stopTimeEntity.setTimepoint(null);
    return stopTimeEntity;
  }

  private void whenDirectionIdIsSearched() {
    when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("0")))
        .thenReturn(TestDataProvider.buildEnumValueDirectionId());
  }

  private void whenWheelchairAccessibleIsSearched() {
    when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("1")))
        .thenReturn(TestDataProvider.buildEnumValueWheelchairAccessible());
  }

  private void whenBikesAllowedIsSearched() {
    when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("2")))
        .thenReturn(TestDataProvider.buildEnumValueBikesAllowed());
  }

  private void whenPickupTypeIsSearched() {
    when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("0")))
        .thenReturn(TestDataProvider.buildEnumValuePickupType());
  }

  private void whenDropOffTypeIsSearched() {
    when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("1")))
        .thenReturn(TestDataProvider.buildEnumValueDropOffType());
  }

  private void whenTimepointIsSearched() {
    when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("2")))
        .thenReturn(TestDataProvider.buildEnumValueTimepoint());
  }

  private void whenInvalidEnumValueIsSearched() {
    when(enumValueRepository.findEnumValue(anyString(), anyString(), eq(null)))
        .thenThrow(new DataNotFoundException("Enum value not found."));
  }
}
