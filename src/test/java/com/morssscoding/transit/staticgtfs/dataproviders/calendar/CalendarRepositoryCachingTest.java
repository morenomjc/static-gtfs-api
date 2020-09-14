package com.morssscoding.transit.staticgtfs.dataproviders.calendar;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.CalendarJpaRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.calendar.CalendarEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepositoryImpl;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
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
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@RunWith(SpringRunner.class)
public class CalendarRepositoryCachingTest {

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private CalendarJpaRepository calendarJpaRepository;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() {
        cacheManager.getCache("enumvalues").clear();
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
        CalendarEntityMapper calendarEntityMapper(){
            return Mappers.getMapper(CalendarEntityMapper.class);
        }

        @Bean
        CalendarRepository calendarRepository(){
            return new CalendarRepositoryImpl(calendarJpaRepository(), calendarEntityMapper());
        }
    }

    @Test
    public void testGetCalendar_ShouldUseCache_WhenCalledTwice(){
        Mockito.clearInvocations(calendarJpaRepository);

        when(calendarJpaRepository.findByServiceId(anyString())).thenReturn(TestDataProvider.buildCalendarEntity());

        calendarRepository.getCalendar("1");
        calendarRepository.getCalendar("1");

        verify(calendarJpaRepository, times(1)).findByServiceId(anyString());
    }

    @Test
    public void testGetCalendar_ShouldUseCache_AfterSavingTheSameRecord(){
        Mockito.clearInvocations(calendarJpaRepository);

        CalendarEntity calendarEntity = TestDataProvider.buildCalendarEntity();
        when(calendarJpaRepository.save(any(CalendarEntity.class))).thenReturn(calendarEntity);

        calendarRepository.save(TestDataProvider.buildCalendar());
        calendarRepository.getCalendar("1");

        verify(calendarJpaRepository, times(0)).findByServiceId(anyString());
    }
}
