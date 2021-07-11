package com.morenomjc.transit.staticgtfs.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.morenomjc.transit.staticgtfs.core.frequency.Frequency;
import com.morenomjc.transit.staticgtfs.core.frequency.FrequencyService;
import com.morenomjc.transit.staticgtfs.core.frequency.FrequencyServiceImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.frequency.FrequencyRepository;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FrequencyServiceTest {

  private FrequencyService frequencyService;

  @MockBean
  private FrequencyRepository frequencyRepository;

  @BeforeAll
  void setup() {
    frequencyService = new FrequencyServiceImpl(frequencyRepository);
  }

  @Test
  void testGetFrequency() {
    when(frequencyRepository.getFrequency(anyString()))
        .thenReturn(TestDataProvider.buildFrequency());

    Frequency frequency = frequencyService.getFrequency("1");

    assertThat(frequency).isNotNull();
  }
}
