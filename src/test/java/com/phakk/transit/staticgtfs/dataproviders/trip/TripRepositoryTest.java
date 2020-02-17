package com.phakk.transit.staticgtfs.dataproviders.trip;

import com.phakk.transit.staticgtfs.core.constants.BikesAllowedEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibilityEnum;
import com.phakk.transit.staticgtfs.core.exception.ConstantsMappingException;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
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

import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildTripEntity;
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

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        tripRepository = new TripRepositoryImpl(tripJpaRepository, tripEntityMapper);
    }

    @TestConfiguration
    static class TripTestConfiguration {
        @Bean
        public TripEntityMapper tripEntityMapper(){
            return Mappers.getMapper(TripEntityMapper.class);
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
        assertThat(trip.getWheelchairAccessible()).isEqualTo(WheelchairAccessibilityEnum.WA_1);
        assertThat(trip.getBikesAllowed()).isEqualTo(BikesAllowedEnum.BIKES_ALLOWED_1);
    }

    @Test
    public void testIfWheelchairAccessibilityIsNull(){
        expectedException.expect(ConstantsMappingException.class);
        expectedException.expectMessage(equalTo("Failed to map wheelchair accessibility details"));

        whenTripExistsWithNullWA();

        tripRepository.getTrip("1");
    }

    @Test
    public void testIfBikesAllowedIsNull(){
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
}
