package com.morenomjc.transit.staticgtfs.dataproviders.calendar;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.CalendarJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarEntityMapperImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepositoryImpl;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.BeforeEach;
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
    Objects.requireNonNull(cacheManager.getCache("calendars")).clear();
  }

  @Configuration
  @EnableCaching
  @Import(CalendarEntityMapperImpl.class)
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
    CalendarRepository calendarRepository(CalendarEntityMapperImpl calendarEntityMapper) {
      return new CalendarRepositoryImpl(calendarJpaRepository(), calendarEntityMapper);
    }
  }

  @Test
  void testGetCalendar_ShouldUseCache_WhenCalledTwice() {
    when(calendarJpaRepository.findByServiceId(anyString()))
        .thenReturn(TestDataProvider.buildCalendarEntity());

    calendarRepository.getCalendar("1");
    calendarRepository.getCalendar("1");

    verify(calendarJpaRepository, times(1)).findByServiceId(eq("1"));
  }

}
