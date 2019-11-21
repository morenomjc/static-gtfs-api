package com.phakk.transit.staticgtfs.api.rest;

import com.phakk.transit.staticgtfs.api.rest.controller.AgencyController;
import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.core.agency.AgencyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class AgencyControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private AgencyService agencyService;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new AgencyController(agencyService)).build();
    }

    @Test
    public void getAgency_ShouldReturnDetails() throws Exception{
        //arrange
        givenAgencyDetails();

        //act and assert
        this.mockMvc.perform(get("/agencies/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.agency_id", is(notNullValue()))
        );
    }

    private void givenAgencyDetails(){
        when(agencyService.getAgency(anyString())).thenReturn(
                Agency.builder()
                        .id(UUID.randomUUID().toString())
                        .name("agency")
                        .url("http://gtfs.com")
                        .timezone("Asia/Manila")
                        .lang("en")
                        .phone("8888")
                        .fareUrl("http://gtfs.com/fares")
                        .email("support@gtfs.com")
                        .build()
        );
    }
}
