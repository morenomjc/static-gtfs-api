package com.phakk.transit.staticgtfs.api.rest;

import com.phakk.transit.staticgtfs.api.rest.controller.StopController;
import com.phakk.transit.staticgtfs.core.constants.StopTypeEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairBoardingEnum;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.core.stop.StopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { StopController.class })
@RunWith(SpringRunner.class)
public class StopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StopService stopService;

    @Test
    public void testGetStopEndpoint() throws Exception{
        givenStop(buildStop());

        this.mockMvc.perform(get("/stops/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().json("{\n" +
                        "    \"meta\": {\n" +
                        "        \"apiVersion\": \"v1\",\n" +
                        "        \"staticGtfsVersion\": \"v1.0\"\n" +
                        "    },\n" +
                        "    \"data\": {\n" +
                        "        \"stop_id\": \"1\",\n" +
                        "        \"stop_code\": \"TEST\",\n" +
                        "        \"stop_name\": \"Test Station\",\n" +
                        "        \"stop_desc\": \"Test Station\",\n" +
                        "        \"stop_lat\": 15.5737673,\n" +
                        "        \"stop_lon\": 122.0481448,\n" +
                        "        \"zone_id\": \"1\",\n" +
                        "        \"stop_url\": \"test.com/stops/TEST\",\n" +
                        "        \"location_type\": {\n" +
                        "            \"id\": \"1\",\n" +
                        "            \"desc\": \"Station\"\n" +
                        "        },\n" +
                        "        \"parent_station\": null,\n" +
                        "        \"stop_timezone\": \"Asia/Singapore\",\n" +
                        "        \"wheelchair_boarding\": {\n" +
                        "            \"id\": \"1\",\n" +
                        "            \"desc\": \"Accessible\"\n" +
                        "        },\n" +
                        "        \"level_id\": null,\n" +
                        "        \"platform_code\": null\n" +
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
                                "        \"apiVersion\": \"v1\",\n" +
                                "        \"staticGtfsVersion\": \"v1.0\"\n" +
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

    private Stop buildStop(){
        return Stop.builder()
                .id("1")
                .code("TEST")
                .name("Test Station")
                .desc("Test Station")
                .lat(15.5737673)
                .lon(122.0481448)
                .zoneId("1")
                .url("test.com/stops/TEST")
                .type(StopTypeEnum.STOP_1)
                .parentStation(null)
                .timezone("Asia/Singapore")
                .wheelchairBoarding(WheelchairBoardingEnum.WB_1)
                .levelId(null)
                .platformCode(null)
                .build();
    }
}
