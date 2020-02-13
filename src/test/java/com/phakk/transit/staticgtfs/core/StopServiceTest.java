package com.phakk.transit.staticgtfs.core;

import com.phakk.transit.staticgtfs.core.constants.StopTypeEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibilityEnum;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.core.stop.StopService;
import com.phakk.transit.staticgtfs.core.stop.StopServiceImpl;
import com.phakk.transit.staticgtfs.dataproviders.repository.stop.StopRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class StopServiceTest {

    private StopService stopService;

    @Mock
    private StopRepository stopRepository;

    @Before
    public void setup(){
        stopService = new StopServiceImpl(stopRepository);
    }

    @Test
    public void testGetStopById(){
        Stop expected = buildStop();
        givenAStop(expected);

        Stop result = stopService.getStop("1");

        assertThat(result).isEqualTo(expected);
    }

    private void givenAStop(Stop stop){
        when(stopRepository.getStop(anyString())).thenReturn(stop);
    }

    private Stop buildStop(){
        return Stop.builder()
                .id("1")
                .code("TEST")
                .name("Test Station")
                .desc("Test Station")
                .lat(15.5737673)
                .lon(122.0481448)
                .zoneId(null)
                .url("test.com/stops/TEST")
                .type(StopTypeEnum.STOP_1)
                .parentStation(null)
                .timezone("Asia/Singapore")
                .wheelchairBoarding(WheelchairAccessibilityEnum.WA_1)
                .levelId(null)
                .platformCode(null)
                .build();
    }
}
