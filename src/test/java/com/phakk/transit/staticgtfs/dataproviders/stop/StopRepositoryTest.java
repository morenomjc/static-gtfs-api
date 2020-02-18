package com.phakk.transit.staticgtfs.dataproviders.stop;

import com.phakk.transit.staticgtfs.core.constants.StopType;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibility;
import com.phakk.transit.staticgtfs.core.exception.ConstantsMappingException;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.StopJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.stop.StopEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.stop.StopRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.stop.StopRepositoryImpl;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Import(StopRepositoryTest.StopTestConfiguration.class)
@RunWith(SpringRunner.class)
public class StopRepositoryTest {

    private StopRepository stopRepository;

    @Mock
    private StopJpaRepository stopJpaRepository;

    @Autowired
    private StopEntityMapper stopEntityMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        stopRepository = new StopRepositoryImpl(stopJpaRepository, stopEntityMapper);
    }

    @TestConfiguration
    static class StopTestConfiguration {
        @Bean
        public StopEntityMapper stopEntityMapper(){
            return Mappers.getMapper(StopEntityMapper.class);
        }
    }

    @Test
    public void testStopIsConvertedProperly(){
        whenStopExists();

        Stop stop = stopRepository.getStop("1");

        assertThat(stop).isNotNull();
        assertThat(stop.getId()).isEqualTo("1");
        assertThat(stop.getCode()).isEqualTo("TEST");
        assertThat(stop.getName()).isEqualTo("Test Station");
        assertThat(stop.getDesc()).isEqualTo("Test Station");
        assertThat(stop.getLat()).isEqualTo(15.5737673);
        assertThat(stop.getLon()).isEqualTo(122.0481448);
        assertThat(stop.getZoneId()).isEqualTo("1");
        assertThat(stop.getUrl()).isEqualTo("test.com/stops/TEST");
        assertThat(stop.getType()).isEqualTo(StopType.STOP_1_STATION);
        assertThat(stop.getParentStation()).isEqualTo("0");
        assertThat(stop.getTimezone()).isEqualTo("Asia/Singapore");
        assertThat(stop.getWheelchairBoarding()).isEqualTo(WheelchairAccessibility.WA_1_ACCESSIBLE);
        assertThat(stop.getLevelId()).isEqualTo("0");
        assertThat(stop.getPlatformCode()).isEqualTo("0");
    }

    @Test
    public void testIfStopTypeIsNull(){
        expectedException.expect(ConstantsMappingException.class);
        expectedException.expectMessage(equalTo("Failed to map stop type"));

        whenStopExistsWithNullStopType();

        stopRepository.getStop("1");
    }

    @Test
    public void testIfWheelchairBoardingIsNull(){
        expectedException.expect(ConstantsMappingException.class);
        expectedException.expectMessage(equalTo("Failed to map wheelchair boarding details"));

        whenStopExistsWithNullWB();

        stopRepository.getStop("1");
    }

    @Test
    public void testWhenStopNotFound(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Stop not found."));

        whenStopNotFound();

        stopRepository.getStop("1");
    }

    private void whenStopNotFound(){
        when(stopJpaRepository.findByStopId(anyString())).thenReturn(null);
    }

    private void whenStopExists(){
        when(stopJpaRepository.findByStopId(anyString())).thenReturn(buildStopEntity());
    }

    private void whenStopExistsWithNullStopType(){
        when(stopJpaRepository.findByStopId(anyString())).thenReturn(buildStopEntityNullStopType());
    }

    private void whenStopExistsWithNullWB(){
        when(stopJpaRepository.findByStopId(anyString())).thenReturn(buildStopEntityNullWB());
    }

    private StopEntity buildStopEntity(){
        StopEntity stopEntity = new StopEntity();
        stopEntity.setStopId("1");
        stopEntity.setStopCode("TEST");
        stopEntity.setName("Test Station");
        stopEntity.setDesc("Test Station");
        stopEntity.setLat(15.5737673);
        stopEntity.setLon(122.0481448);
        stopEntity.setZoneId("1");
        stopEntity.setUrl("test.com/stops/TEST");
        stopEntity.setStopType("1");
        stopEntity.setParentStation("0");
        stopEntity.setTimezone("Asia/Singapore");
        stopEntity.setWheelchairBoarding("1");
        stopEntity.setLevelId("0");
        stopEntity.setPlatformCode("0");

        return stopEntity;
    }

    private StopEntity buildStopEntityNullStopType(){
        StopEntity stopEntity = buildStopEntity();
        stopEntity.setStopType(null);

        return stopEntity;
    }

    private StopEntity buildStopEntityNullWB(){
        StopEntity stopEntity = buildStopEntity();
        stopEntity.setWheelchairBoarding(null);

        return stopEntity;
    }
}
