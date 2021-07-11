package com.morenomjc.transit.staticgtfs.dataproviders.stop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.StopJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.stop.StopEntityMapper;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import com.morenomjc.transit.staticgtfs.core.stop.Stop;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.stop.StopRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.stop.StopRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(StopRepositoryTest.StopTestConfiguration.class)
public class StopRepositoryTest {

  private StopRepository stopRepository;

  @Mock
  private StopJpaRepository stopJpaRepository;
  @Mock
  private EnumValueRepository enumValueRepository;

  @Autowired
  private StopEntityMapper stopEntityMapper;

  @BeforeAll
  public void setup() {
    stopRepository = new StopRepositoryImpl(stopJpaRepository, stopEntityMapper,
        enumValueRepository);
  }

  @TestConfiguration
  static class StopTestConfiguration {

    @Bean
    public StopEntityMapper stopEntityMapper() {
      return Mappers.getMapper(StopEntityMapper.class);
    }
  }

  @Test
  public void testStopIsConvertedProperly() {
    whenStopExists();
    whenStopTypeIsSearched();
    whenWheelchairBoardingIsSearched();

    Stop stop = stopRepository.getStop("1");
    EnumValue stopType = TestDataProvider.buildEnumValueStopType();
    EnumValue wheelchairBoarding = TestDataProvider.buildEnumValueWheelchairBoarding();

    assertThat(stop).isNotNull();
    assertThat(stop.getId()).isEqualTo("1");
    assertThat(stop.getCode()).isEqualTo("TEST");
    assertThat(stop.getName()).isEqualTo("Test Station");
    assertThat(stop.getDesc()).isEqualTo("Test Station");
    assertThat(stop.getLat()).isEqualTo(15.5737673);
    assertThat(stop.getLon()).isEqualTo(122.0481448);
    assertThat(stop.getZoneId()).isEqualTo("1");
    assertThat(stop.getUrl()).isEqualTo("test.com/stops/TEST");
    assertThat(stop.getType()).isEqualTo(stopType);
    assertThat(stop.getParentStation()).isEqualTo("0");
    assertThat(stop.getTimezone()).isEqualTo("Asia/Singapore");
    assertThat(stop.getWheelchairBoarding()).isEqualTo(wheelchairBoarding);
    assertThat(stop.getLevelId()).isEqualTo("0");
    assertThat(stop.getPlatformCode()).isEqualTo("0");
  }

    /*@Test //TODO: fix
    public void testIfStopTypeIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenStopExistsWithNullStopType();
        whenInvalidEnumValueIsSearched();

        stopRepository.getStop("1");
    }*/

    /*@Test //TODO: fix
    public void testIfWheelchairBoardingIsNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenStopExistsWithNullWB();
        whenInvalidEnumValueIsSearched();

        stopRepository.getStop("1");
    }*/

    /*@Test //TODO: fix
    public void testWhenStopNotFound(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Stop not found."));

        whenStopNotFound();

        stopRepository.getStop("1");
    }*/

  private void whenStopNotFound() {
    when(stopJpaRepository.findByStopId(anyString())).thenReturn(null);
  }

  private void whenStopExists() {
    when(stopJpaRepository.findByStopId(anyString())).thenReturn(TestDataProvider.buildStopEntity());
  }

  private void whenStopExistsWithNullStopType() {
    when(stopJpaRepository.findByStopId(anyString())).thenReturn(buildStopEntityNullStopType());
  }

  private void whenStopExistsWithNullWB() {
    when(stopJpaRepository.findByStopId(anyString())).thenReturn(buildStopEntityNullWB());
  }

  private StopEntity buildStopEntityNullStopType() {
    StopEntity stopEntity = TestDataProvider.buildStopEntity();
    stopEntity.setStopType(null);

    return stopEntity;
  }

  private StopEntity buildStopEntityNullWB() {
    StopEntity stopEntity = TestDataProvider.buildStopEntity();
    stopEntity.setWheelchairBoarding(null);

    return stopEntity;
  }

  private void whenStopTypeIsSearched() {
    when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("1")))
        .thenReturn(TestDataProvider.buildEnumValueStopType());
  }

  private void whenWheelchairBoardingIsSearched() {
    when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("0")))
        .thenReturn(TestDataProvider.buildEnumValueWheelchairBoarding());
  }

  private void whenInvalidEnumValueIsSearched() {
    when(enumValueRepository.findEnumValue(anyString(), anyString(), eq(null)))
        .thenThrow(new DataNotFoundException("Enum value not found."));
  }
}
