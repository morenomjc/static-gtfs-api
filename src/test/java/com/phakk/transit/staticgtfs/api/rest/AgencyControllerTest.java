package com.phakk.transit.staticgtfs.api.rest;

import com.phakk.transit.staticgtfs.api.rest.controller.AgencyController;
import com.phakk.transit.staticgtfs.api.rest.mapper.AgencyDtoMapper;
import com.phakk.transit.staticgtfs.configuration.MapperConfiguration;
import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.core.agency.AgencyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { AgencyController.class })
@Import(MapperConfiguration.class)
@RunWith(SpringRunner.class)
public class AgencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AgencyDtoMapper agencyDtoMapper;

    @MockBean
    private AgencyService agencyService;

    @Test
    public void testGetAgencyEndpoint() throws Exception{
        //arrange
        givenAnAgency(buildAgency());

        //act and assert
        this.mockMvc.perform(get("/agencies/1")
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
                        "        \"type\": \"agencies\",\n" +
                        "        \"attributes\": {\n" +
                        "            \"agency_id\": \"agency1\",\n" +
                        "            \"agency_name\": \"Test Agency\",\n" +
                        "            \"agency_url\": \"test.com/agency\",\n" +
                        "            \"agency_timezone\": \"Asia/Singapore\",\n" +
                        "            \"agency_lang\": \"en\",\n" +
                        "            \"agency_phone\": \"12345-677974\",\n" +
                        "            \"agency_fare_url\": \"test.com/fares\",\n" +
                        "            \"agency_email\": \"test@email.com\"\n" +
                        "        }\n" +
                        "    }\n" +
                        "}")
        );
    }

    @Test
    public void testGetAgenciesEndpoint() throws Exception{
        //arrange
        givenAgencies(buildAgency());

        //act and assert
        this.mockMvc.perform(get("/agencies")
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
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"type\": \"agencies\",\n" +
                        "            \"attributes\": {\n" +
                        "                \"agency_id\": \"agency1\",\n" +
                        "                \"agency_name\": \"Test Agency\",\n" +
                        "                \"agency_url\": \"test.com/agency\",\n" +
                        "                \"agency_timezone\": \"Asia/Singapore\",\n" +
                        "                \"agency_lang\": \"en\",\n" +
                        "                \"agency_phone\": \"12345-677974\",\n" +
                        "                \"agency_fare_url\": \"test.com/fares\",\n" +
                        "                \"agency_email\": \"test@email.com\"\n" +
                        "            }\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}")
        );
    }

    private void givenAnAgency(Agency agency){
        when(agencyService.getAgency(anyString())).thenReturn(agency);
    }

    private void givenAgencies(Agency agency){
        when(agencyService.getAgencies()).thenReturn(Collections.singletonList(agency));
    }

    private Agency buildAgency(){
        return Agency.builder()
                .id("agency1")
                .name("Test Agency")
                .url("test.com/agency")
                .timezone("Asia/Singapore")
                .lang("en")
                .phone("12345-677974")
                .fareUrl("test.com/fares")
                .email("test@email.com")
                .build();
    }
}
