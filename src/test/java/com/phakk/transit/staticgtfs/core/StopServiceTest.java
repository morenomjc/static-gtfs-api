package com.phakk.transit.staticgtfs.core;

import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.core.stop.StopService;
import com.phakk.transit.staticgtfs.core.stop.StopServiceImpl;
import com.phakk.transit.staticgtfs.dataproviders.repository.stop.StopRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildStop;
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

}
