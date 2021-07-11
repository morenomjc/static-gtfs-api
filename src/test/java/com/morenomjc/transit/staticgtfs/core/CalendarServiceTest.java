package com.morenomjc.transit.staticgtfs.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.morenomjc.transit.staticgtfs.core.calendar.Calendar;
import com.morenomjc.transit.staticgtfs.core.calendar.CalendarService;
import com.morenomjc.transit.staticgtfs.core.calendar.CalendarServiceImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepository;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalendarServiceTest {

  private CalendarService calendarService;

  @MockBean
  private CalendarRepository calendarRepository;

  @BeforeAll
  void setup() {
    this.calendarService = new CalendarServiceImpl(calendarRepository);
  }

  @Test
  void testGetCalendar() {
    Calendar expected = TestDataProvider.buildCalendar();
    givenCalendar(expected);

    Calendar result = calendarService.getCalendar("1");
    assertThat(result).isEqualTo(expected);
  }

  void givenCalendar(Calendar calendar) {
    when(calendarRepository.getCalendar(anyString())).thenReturn(calendar);
  }

}
