package com.morssscoding.transit.staticgtfs.api.rest;

import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import com.morssscoding.transit.staticgtfs.api.rest.controller.CalendarController;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.CalendarDtoMapper;
import com.morssscoding.transit.staticgtfs.core.calendar.CalendarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@Import(CalendarControllerTest.TestConfig.class)
@RunWith(SpringRunner.class)
public class CalendarControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private CalendarService calendarService;

    @Autowired
    private CalendarDtoMapper calendarDtoMapper;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public CalendarDtoMapper calendarDtoMapper(){
            return Mappers.getMapper(CalendarDtoMapper.class);
        }
    }

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new CalendarController(calendarService, calendarDtoMapper))
                .build();
    }

    @Test
    public void testGetCalendarEndpoint() throws Exception{
        givenCalendar();

        this.mockMvc.perform(get("/calendars/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().json("{\n" +
                        "    \"meta\": {\n" +
                        "        \"api\": {\n" +
                        "            \"version\": \"v1\"\n" +
                        "        },\n" +
                        "        \"gtfs\": {\n" +
                        "            \"static\": \"v1.0\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"data\": {\n" +
                        "        \"type\": \"calendars\",\n" +
                        "        \"attributes\": {\n" +
                        "            \"service_id\": \"1\",\n" +
                        "            \"monday\": true,\n" +
                        "            \"tuesday\": true,\n" +
                        "            \"wednesday\": true,\n" +
                        "            \"thursday\": true,\n" +
                        "            \"friday\": true,\n" +
                        "            \"saturday\": false,\n" +
                        "            \"sunday\": false\n" +
                        "        }\n" +
                        "    }\n" +
                        "}")
        );
    }

    private void givenCalendar(){
        when(calendarService.getCalendar(anyString())).thenReturn(TestDataProvider.buildCalendar());
    }

}
