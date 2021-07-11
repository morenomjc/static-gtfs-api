package com.morenomjc.transit.staticgtfs.dataproviders.frequency;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.FrequencyJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.frequency.FrequencyEntityMapper;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.frequency.FrequencyRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.frequency.FrequencyRepositoryImpl;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
class FrequencyRepositoryCachingTest {

  @Autowired
  private FrequencyRepository frequencyRepository;

  @Autowired
  private FrequencyJpaRepository frequencyJpaRepository;

  @Autowired
  private EnumValueRepository enumValueRepository;

  @Autowired
  private FrequencyEntityMapper frequencyEntityMapper;

  @Autowired
  private CacheManager cacheManager;

  @BeforeEach
  void setUp() {
    cacheManager.getCache("frequencies").clear();
  }

  @Configuration
  @EnableCaching
  static class TestConfig {

    @Bean
    CacheManager cacheManager() {
      return new ConcurrentMapCacheManager("frequencies");
    }

    @Bean
    FrequencyJpaRepository frequencyJpaRepository() {
      return Mockito.mock(FrequencyJpaRepository.class);
    }

    @Bean
    FrequencyEntityMapper frequencyEntityMapper() {
      return Mappers.getMapper(FrequencyEntityMapper.class);
    }

    @Bean
    EnumValueRepository enumValueRepository() {
      return Mockito.mock(EnumValueRepository.class);
    }

    @Bean
    FrequencyRepository frequencyRepository() {
      return new FrequencyRepositoryImpl(frequencyJpaRepository(), frequencyEntityMapper(),
          enumValueRepository());
    }
  }

  @Test
    //TODO: fix
  void testGetFrequency_ShouldUseCache_WhenCalledTwice() {
    Mockito.clearInvocations(frequencyJpaRepository);

    when(frequencyJpaRepository.findByTripId(anyString()))
        .thenReturn(Optional.of(TestDataProvider.buildFrequencyEntity()));
    when(enumValueRepository.findEnumValue(anyString(), anyString(), anyString()))
        .thenReturn(TestDataProvider.buildEnumValueExactTimes());

    frequencyRepository.getFrequency("1");
    frequencyRepository.getFrequency("1");

    verify(frequencyJpaRepository, times(1)).findByTripId(anyString());
  }

}
