package com.morenomjc.transit.staticgtfs.dataproviders.frequency;

import com.morenomjc.transit.staticgtfs.core.mapper.CommonCoreDataMapperImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.FrequencyJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapperImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.frequency.FrequencyEntityMapper;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.frequency.FrequencyEntityMapperImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.frequency.FrequencyRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.frequency.FrequencyRepositoryImpl;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
  private CacheManager cacheManager;

  @BeforeEach
  void setUp() {
    cacheManager.getCache("frequencies").clear();
  }

  @Configuration
  @EnableCaching
  @Import(value = { CommonCoreDataMapperImpl.class, EnumValueEntityMapperImpl.class, FrequencyEntityMapperImpl.class })
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
    EnumValueRepository enumValueRepository() {
      return Mockito.mock(EnumValueRepository.class);
    }

    @Bean
    FrequencyRepository frequencyRepository(FrequencyEntityMapper frequencyEntityMapper) {
      return new FrequencyRepositoryImpl(frequencyJpaRepository(), frequencyEntityMapper,
          enumValueRepository());
    }
  }

  @Test
  void testGetFrequency_ShouldUseCache_WhenCalledTwice() {
    when(frequencyJpaRepository.findByTripId(anyString()))
        .thenReturn(Optional.of(TestDataProvider.buildFrequencyEntity()));
    when(enumValueRepository.findEnumValue(anyString(), anyString(), anyString()))
        .thenReturn(TestDataProvider.buildEnumValueExactTimes());

    frequencyRepository.getFrequency("1");
    frequencyRepository.getFrequency("1");

    verify(frequencyJpaRepository, times(1)).findByTripId(eq("1"));
  }

}
