package com.morenomjc.transit.staticgtfs.dataproviders.calendar;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepository;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.CalendarJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarEntityMapper;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalendarRepositoryCachingTest {

  @Autowired
  private CalendarRepository calendarRepository;

  @Autowired
  private CalendarJpaRepository calendarJpaRepository;

  @Autowired
  private CacheManager cacheManager;

  @BeforeEach
  void setUp() {
    cacheManager.getCache("calendars").clear();
  }

  @Configuration
  @EnableCaching
  static class TestConfig {

    @Bean
    CacheManager cacheManager() {
      return new ConcurrentMapCacheManager("calendars");
    }

    @Bean
    CalendarJpaRepository calendarJpaRepository() {
      return Mockito.mock(CalendarJpaRepository.class);
    }

    @Bean
    CalendarEntityMapper calendarEntityMapper() {
      return Mappers.getMapper(CalendarEntityMapper.class);
    }

    @Bean
    CalendarRepository calendarRepository() {
      return new CalendarRepositoryImpl(calendarJpaRepository(), calendarEntityMapper());
    }
  }

  @Test
    //TODO: fix
  void testGetCalendar_ShouldUseCache_WhenCalledTwice() {
    Mockito.clearInvocations(calendarJpaRepository);

    when(calendarJpaRepository.findByServiceId(anyString()))
        .thenReturn(TestDataProvider.buildCalendarEntity());

    calendarRepository.getCalendar("1");
    calendarRepository.getCalendar("1");

    verify(calendarJpaRepository, times(1)).findByServiceId(anyString());
  }

}
