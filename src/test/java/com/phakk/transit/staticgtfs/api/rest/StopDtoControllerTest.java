package com.phakk.transit.staticgtfs.api.rest;

import com.phakk.transit.staticgtfs.api.rest.controller.StopController;
import com.phakk.transit.staticgtfs.core.constants.StopTypeEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairBoardingEnum;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.core.stop.StopService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class StopDtoControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private StopService stopService;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new StopController(stopService)).build();
    }

    @Test
    public void testGetStopEndpoint() throws Exception{
        //arrange
        givenStop(buildStop());

        //act and assert
        this.mockMvc.perform(get("/stops/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.stop_id", is("1"))
        ).andExpect(
                jsonPath("$.location_type.id", is("1"))
        ).andExpect(
                jsonPath("$.wheelchair_boarding.desc", is("Accessible"))
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
                .zoneId(null)
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
