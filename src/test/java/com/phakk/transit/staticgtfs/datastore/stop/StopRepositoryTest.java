package com.phakk.transit.staticgtfs.datastore.stop;

import com.phakk.transit.staticgtfs.core.constants.StopTypeEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairBoardingEnum;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.datastore.jpa.entity.StopEntity;
import com.phakk.transit.staticgtfs.datastore.jpa.repository.StopJpaRepository;
import com.phakk.transit.staticgtfs.datastore.repository.stop.StopRepository;
import com.phakk.transit.staticgtfs.datastore.repository.stop.StopRepositoryImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class StopRepositoryTest {

    private StopRepository stopRepository;

    @Mock
    private StopJpaRepository stopJpaRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        stopRepository = new StopRepositoryImpl(stopJpaRepository);
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
        assertThat(stop.getType()).isEqualTo(StopTypeEnum.STOP_1);
        assertThat(stop.getWheelchairBoarding()).isEqualTo(WheelchairBoardingEnum.WB_1);
    }

    @Test
    public void testIfStopEnumIsNull(){
        whenStopExistsWithNullEnum();

        Stop stop = stopRepository.getStop("1");

        assertThat(stop).isNotNull();
        assertThat(stop.getType()).isNull();
        assertThat(stop.getWheelchairBoarding()).isNull();
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

    private void whenStopExistsWithNullEnum(){
        when(stopJpaRepository.findByStopId(anyString())).thenReturn(buildStopEntityNullEnum());
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
        stopEntity.setParentStation(null);
        stopEntity.setTimezone("Asia/Singapore");
        stopEntity.setWheelchairBoarding("1");
        stopEntity.setLevelId(null);
        stopEntity.setPlatformCode(null);

        return stopEntity;
    }

    private StopEntity buildStopEntityNullEnum(){
        StopEntity stopEntity = buildStopEntity();
        stopEntity.setStopType(null);
        stopEntity.setWheelchairBoarding(null);

        return stopEntity;
    }
}
