package com.morenomjc.transit.staticgtfs.dataproviders.enumvalue;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.EnumValueJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapper;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepositoryImpl;
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
class EnumValueRepositoryCachingTest {

  @Autowired
  private EnumValueRepository enumValueRepository;

  @Autowired
  private EnumValueJpaRepository enumValueJpaRepository;

  @Autowired
  private CacheManager cacheManager;

  @BeforeEach
  void setUp() {
    cacheManager.getCache("enumvalues").clear();
  }

  @Configuration
  @EnableCaching
  static class TestConfig {

    @Bean
    CacheManager cacheManager() {
      return new ConcurrentMapCacheManager("enumvalues");
    }

    @Bean
    EnumValueJpaRepository enumValueJpaRepository() {
      return Mockito.mock(EnumValueJpaRepository.class);
    }

    @Bean
    EnumValueEntityMapper enumValueEntityMapper() {
      return Mappers.getMapper(EnumValueEntityMapper.class);
    }

    @Bean
    EnumValueRepository enumValueRepository() {
      return new EnumValueRepositoryImpl(enumValueJpaRepository(), enumValueEntityMapper());
    }
  }

  @Test
    //TODO: fix
  void testFindEnumValue_ShouldUseCache_WhenCalledTwice() {
    when(enumValueJpaRepository.findByFileAndFieldAndCode(anyString(), anyString(), anyString()))
        .thenReturn(TestDataProvider.buildEnumValueEntity());

    enumValueRepository.findEnumValue("stops", "location_type", "0");
    enumValueRepository.findEnumValue("stops", "location_type", "0");

    verify(enumValueJpaRepository, times(1))
        .findByFileAndFieldAndCode(anyString(), anyString(), anyString());
  }

}
