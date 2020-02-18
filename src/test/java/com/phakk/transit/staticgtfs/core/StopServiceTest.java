package com.phakk.transit.staticgtfs.core;

import com.phakk.transit.staticgtfs.core.constants.StopType;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibility;
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
                .type(StopType.STOP_1_STATION)
                .parentStation(null)
                .timezone("Asia/Singapore")
                .wheelchairBoarding(WheelchairAccessibility.WA_1_ACCESSIBLE)
                .levelId(null)
                .platformCode(null)
                .build();
    }
}
