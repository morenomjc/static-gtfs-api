package com.morssscoding.transit.staticgtfs.core;

import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import com.morssscoding.transit.staticgtfs.core.calendar.Calendar;
import com.morssscoding.transit.staticgtfs.core.calendar.CalendarService;
import com.morssscoding.transit.staticgtfs.core.calendar.CalendarServiceImpl;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CalendarServiceTest {

    private CalendarService calendarService;

    @MockBean
    private CalendarRepository calendarRepository;

    @Before
    public void setup(){
        this.calendarService = new CalendarServiceImpl(calendarRepository);
    }

    @Test
    public void testGetCalendar(){
        Calendar expected = TestDataProvider.buildCalendar();
        givenCalendar(expected);

        Calendar result = calendarService.getCalendar("1");
        assertThat(result).isEqualTo(expected);
    }

    public void givenCalendar(Calendar calendar){
        when(calendarRepository.getCalendar(anyString())).thenReturn(calendar);
    }

}
