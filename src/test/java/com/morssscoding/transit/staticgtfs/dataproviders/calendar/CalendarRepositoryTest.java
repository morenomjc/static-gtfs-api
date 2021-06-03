package com.morssscoding.transit.staticgtfs.dataproviders.calendar;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildCalendarEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.morssscoding.transit.staticgtfs.core.calendar.Calendar;
import com.morssscoding.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.CalendarJpaRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.calendar.CalendarEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepositoryImpl;
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
@Import(CalendarRepositoryTest.TestConfig.class)
class CalendarRepositoryTest {

  private CalendarRepository calendarRepository;

  @Mock
  private CalendarJpaRepository calendarJpaRepository;

  @Autowired
  private CalendarEntityMapper calendarEntityMapper;

  @BeforeAll
  void setup() {
    calendarRepository = new CalendarRepositoryImpl(calendarJpaRepository, calendarEntityMapper);
  }

  @TestConfiguration
  static class TestConfig {

    @Bean
    CalendarEntityMapper calendarEntityMapper() {
      return Mappers.getMapper(CalendarEntityMapper.class);
    }
  }

  @Test
  void testIfCalendarEntityIsMappedProperly() {
    givenCalendar();

    Calendar calendar = calendarRepository.getCalendar("1");

    assertThat(calendar).isNotNull();
    assertThat(calendar.getServiceId()).isEqualTo("1");
    assertThat(calendar.getMonday()).isTrue();
    assertThat(calendar.getTuesday()).isTrue();
    assertThat(calendar.getWednesday()).isTrue();
    assertThat(calendar.getThursday()).isTrue();
    assertThat(calendar.getFriday()).isTrue();
    assertThat(calendar.getSaturday()).isFalse();
    assertThat(calendar.getSunday()).isFalse();
  }

    /*@Test //TODO: fix
    void testIfCalendarNotFound(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Route not found"));

        whenCalendarNotFound();

        calendarRepository.getCalendar("1");
    }*/

  void givenCalendar() {
    when(calendarJpaRepository.findByServiceId(anyString()))
        .thenReturn(buildCalendarEntity());
  }

  void whenCalendarNotFound() {
    when(calendarJpaRepository.findByServiceId(anyString()))
        .thenThrow(new DataNotFoundException("Route not found"));
  }
}
