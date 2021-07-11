package com.morenomjc.transit.staticgtfs.api.rest;

import com.morenomjc.transit.staticgtfs.api.rest.controller.CalendarController;
import com.morenomjc.transit.staticgtfs.api.rest.dto.CalendarDto;
import com.morenomjc.transit.staticgtfs.api.rest.mapper.CalendarDtoMapperImpl;
import com.morenomjc.transit.staticgtfs.core.calendar.CalendarService;
import com.morenomjc.transit.staticgtfs.utils.JsonAssertionUtil;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@WebMvcTest(controllers = {CalendarController.class})
@Import(CalendarDtoMapperImpl.class)
class CalendarControllerTest extends JsonAssertionUtil {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CalendarService calendarService;

  @Test
  void testGetCalendarEndpoint() throws Exception {
    givenCalendar();

    this.mockMvc.perform(get("/calendars/1")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().isOk(),
            jsonPath("$.data").isNotEmpty(),
            jsonPath("$.data.type", is("calendars")),
            jsonPath("$.data.attributes").isNotEmpty(),
            assertHasJsonFields("$.data.attributes", CalendarDto.class)
        )
    );
  }

  private void givenCalendar() {
    when(calendarService.getCalendar(anyString())).thenReturn(TestDataProvider.buildCalendar());
  }

}
