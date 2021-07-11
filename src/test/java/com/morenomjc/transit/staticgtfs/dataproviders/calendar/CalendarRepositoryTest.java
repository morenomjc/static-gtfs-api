package com.morenomjc.transit.staticgtfs.dataproviders.calendar;

import com.morenomjc.transit.staticgtfs.core.calendar.Calendar;
import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.CalendarJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarEntityMapper;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarEntityMapperImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepositoryImpl;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(CalendarEntityMapperImpl.class)
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

  @Test
  void testIfCalendarNotFound(){
    whenCalendarNotFound();

    DataNotFoundException exception = assertThrows(
            DataNotFoundException.class,
            () -> calendarRepository.getCalendar("1")
    );

    assertThat(exception.getMessage()).isEqualTo("Calendar not found.");
  }

  void givenCalendar() {
    when(calendarJpaRepository.findByServiceId(anyString()))
        .thenReturn(TestDataProvider.buildCalendarEntity());
  }

  void whenCalendarNotFound() {
    when(calendarJpaRepository.findByServiceId(anyString())).thenReturn(null);
  }
}
