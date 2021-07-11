package com.morenomjc.transit.staticgtfs.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.morenomjc.transit.staticgtfs.core.stop.Stop;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import com.morenomjc.transit.staticgtfs.core.stop.StopService;
import com.morenomjc.transit.staticgtfs.core.stop.StopServiceImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.stop.StopRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StopServiceTest {

  private StopService stopService;

  @Mock
  private StopRepository stopRepository;

  @BeforeAll
  void setup() {
    stopService = new StopServiceImpl(stopRepository);
  }

  @Test
  void testGetStopById() {
    Stop expected = TestDataProvider.buildStop();
    givenAStop(expected);

    Stop result = stopService.getStop("1");

    assertThat(result).isEqualTo(expected);
  }

  private void givenAStop(Stop stop) {
    when(stopRepository.getStop(anyString())).thenReturn(stop);
  }

}
