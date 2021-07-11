package com.morenomjc.transit.staticgtfs.dataproviders.enumvalue;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.EnumValueJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapperImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepositoryImpl;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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

import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EnumValueRepositoryCachingTest {

  @Autowired
  private EnumValueRepository enumValueRepository;

  @Autowired
  private EnumValueJpaRepository enumValueJpaRepository;

  @Autowired
  private CacheManager cacheManager;

  @BeforeAll
  void setUp() {
    Objects.requireNonNull(cacheManager.getCache("enumvalues")).clear();
  }

  @Configuration
  @EnableCaching
  @Import(value = { EnumValueEntityMapperImpl.class })
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
    EnumValueRepository enumValueRepository(EnumValueEntityMapperImpl enumValueEntityMapper){
      return new EnumValueRepositoryImpl(enumValueJpaRepository(), enumValueEntityMapper);
    }
  }

  @Test
  void testFindEnumValue_ShouldUseCache_WhenCalledTwice() {
    when(enumValueJpaRepository.findByFileAndFieldAndCode(anyString(), anyString(), anyString()))
        .thenReturn(TestDataProvider.buildEnumValueEntity());

    enumValueRepository.findEnumValue("stops", "location_type", "0");
    enumValueRepository.findEnumValue("stops", "location_type", "0");

    verify(enumValueJpaRepository, times(1))
        .findByFileAndFieldAndCode(eq("stops"), eq("location_type"), eq("0"));
  }

}
