package com.morssscoding.transit.staticgtfs.api.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.morssscoding.transit.staticgtfs.api.rest.controller.AgencyController;
import com.morssscoding.transit.staticgtfs.api.rest.dto.AgencyDto;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.AgencyDtoMapper;
import com.morssscoding.transit.staticgtfs.core.agency.Agency;
import com.morssscoding.transit.staticgtfs.core.agency.AgencyService;
import com.morssscoding.transit.staticgtfs.utils.JsonAssertionUtil;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WithMockUser
@WebMvcTest(controllers = {AgencyController.class})
@Import(AgencyControllerTest.AgencyTestConfiguration.class)
class AgencyControllerTest extends JsonAssertionUtil {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  AgencyService agencyService;

  @TestConfiguration
  static class AgencyTestConfiguration {

    @Bean
    public AgencyDtoMapper agencyDtoMapper() {
      return Mappers.getMapper(AgencyDtoMapper.class);
    }
  }

  @Test
  void testGetAgencyEndpoint() throws Exception {
    //arrange
    givenAnAgency(TestDataProvider.buildAgency());

    //act and assert
    this.mockMvc.perform(get("/agencies/1")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().isOk(),
            jsonPath("$.data").isNotEmpty(),
            jsonPath("$.data.type", is("agencies")),
            jsonPath("$.data.attributes").isNotEmpty(),
            assertHasJsonFields("$.data.attributes", AgencyDto.class)
        )
    );
  }

  @Test
  void testGetAgenciesEndpoint() throws Exception {
    //arrange
    givenAgencies(TestDataProvider.buildAgency());

    //act and assert
    this.mockMvc.perform(get("/agencies")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().isOk(),
            jsonPath("$.data", hasSize(1)),
            jsonPath("$.count", is(1)),
            jsonPath("$.data[0].type", is("agencies")),
            jsonPath("$.data[0].attributes").isNotEmpty(),
            assertHasJsonFields("$.data[0].attributes", AgencyDto.class)
        )
    );
  }

  void givenAnAgency(Agency agency) {
    when(agencyService.getAgency(anyString())).thenReturn(agency);
  }

  void givenAgencies(Agency agency) {
    when(agencyService.getAgencies()).thenReturn(Collections.singletonList(agency));
  }

}
