package com.morssscoding.transit.staticgtfs.api.rest;

import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import com.morssscoding.transit.staticgtfs.api.rest.controller.StopController;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.StopDtoMapper;
import com.morssscoding.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morssscoding.transit.staticgtfs.core.stop.Stop;
import com.morssscoding.transit.staticgtfs.core.stop.StopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { StopController.class })
@Import(StopControllerTest.StopTestConfiguration.class)
@RunWith(SpringRunner.class)
public class StopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StopDtoMapper stopDtoMapper;

    @MockBean
    private StopService stopService;

    @TestConfiguration
    static class StopTestConfiguration {
        @Bean
        public StopDtoMapper stopDtoMapper(){
            return Mappers.getMapper(StopDtoMapper.class);
        }
    }

    @Test
    public void testGetStopEndpoint() throws Exception{
        givenStop(TestDataProvider.buildStop());

        this.mockMvc.perform(get("/stops/1")
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
                        "        \"type\": \"stops\",\n" +
                        "        \"attributes\": {\n" +
                        "            \"stop_id\": \"1\",\n" +
                        "            \"stop_code\": \"TEST\",\n" +
                        "            \"stop_name\": \"Test Station\",\n" +
                        "            \"stop_desc\": \"Test Station\",\n" +
                        "            \"stop_lat\": 15.5737673,\n" +
                        "            \"stop_lon\": 122.0481448,\n" +
                        "            \"zone_id\": \"1\",\n" +
                        "            \"stop_url\": \"test.com/stops/TEST\",\n" +
                        "            \"location_type\": {\n" +
                        "                \"code\": \"1\",\n" +
                        "                \"desc\": \"Station\"\n" +
                        "            },\n" +
                        "            \"parent_station\": null,\n" +
                        "            \"stop_timezone\": \"Asia/Singapore\",\n" +
                        "            \"wheelchair_boarding\": {\n" +
                        "                \"code\": \"1\",\n" +
                        "                \"desc\": \"Possible But Not Guaranteed\"\n" +
                        "            },\n" +
                        "            \"level_id\": null,\n" +
                        "            \"platform_code\": null\n" +
                        "        }\n" +
                        "    }\n" +
                        "}")
        );

    }

    @Test
    public void testWhenStopNotFound() throws Exception{
        when(stopService.getStop(anyString())).thenThrow(new DataNotFoundException("Stop not found."));

        this.mockMvc.perform(get("/stops/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isNotFound()
        ).andExpect(
                content()
                        .json("{\n" +
                                "    \"meta\": {\n" +
                                "        \"api\": {\n" +
                                "            \"version\": \"v1\"\n" +
                                "        },\n" +
                                "        \"gtfs\": {\n" +
                                "            \"static\": \"v1.0\"\n" +
                                "        }\n" +
                                "    },\n" +
                                "    \"error\": {\n" +
                                "        \"status\": 404,\n" +
                                "        \"code\": \"404.0\",\n" +
                                "        \"title\": \"Resource Not Found\",\n" +
                                "        \"detail\": \"Stop not found.\"\n" +
                                "    }\n" +
                                "}")
        );
    }

    private void givenStop(Stop stop){
        when(stopService.getStop(anyString())).thenReturn(stop);
    }

}
