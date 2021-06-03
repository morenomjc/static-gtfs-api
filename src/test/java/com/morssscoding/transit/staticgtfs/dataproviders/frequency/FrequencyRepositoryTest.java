package com.morssscoding.transit.staticgtfs.dataproviders.frequency;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildEnumValueExactTimes;
import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildFrequencyEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.morssscoding.transit.staticgtfs.configuration.MapperConfiguration;
import com.morssscoding.transit.staticgtfs.core.constants.EnumValue;
import com.morssscoding.transit.staticgtfs.core.frequency.Frequency;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.FrequencyJpaRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.frequency.FrequencyEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.frequency.FrequencyRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.frequency.FrequencyRepositoryImpl;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(MapperConfiguration.class)
class FrequencyRepositoryTest {

  private FrequencyRepository frequencyRepository;

  @MockBean
  private FrequencyJpaRepository frequencyJpaRepository;
  @Autowired
  private FrequencyEntityMapper frequencyEntityMapper;
  @MockBean
  private EnumValueRepository enumValueRepository;

  @BeforeAll
  void setup() {
    frequencyRepository = new FrequencyRepositoryImpl(frequencyJpaRepository, frequencyEntityMapper,
        enumValueRepository);
  }

  @Test
  void testFrequencyEntityIsMappedProperly() {
    whenFrequencyEntityExists();
    whenExactTimesIsSearched();

    Frequency frequency = frequencyRepository.getFrequency("1");
    EnumValue exactTimes = buildEnumValueExactTimes();

    assertThat(frequency).isNotNull();
    assertThat(frequency.getTripId()).isEqualTo("1");
    assertThat(frequency.getStartTime()).isAfter(LocalTime.now().minusMinutes(1));
//        assertThat(frequency.getEndTime()).isAfter(LocalTime.now());
    assertThat(frequency.getHeadwaySecs().get(ChronoUnit.SECONDS)).isEqualTo(300);
    assertThat(frequency.getExactTimes()).isEqualTo(exactTimes);
  }

    /*@Test //TODO: fix
    void testWhenFrequencyNotFound(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Frequency not found."));

        whenFrequencyEntityNotFound();

        frequencyRepository.getFrequency("1");
    }*/

  private void whenFrequencyEntityNotFound() {
    when(frequencyJpaRepository.findByTripId(anyString())).thenReturn(Optional.empty());
  }

  private void whenFrequencyEntityExists() {
    when(frequencyJpaRepository.findByTripId(anyString()))
        .thenReturn(Optional.of(buildFrequencyEntity()));
  }

  private void whenExactTimesIsSearched() {
    when(enumValueRepository.findEnumValue(anyString(), anyString(), anyString()))
        .thenReturn(buildEnumValueExactTimes());
  }
}
