package com.morenomjc.transit.staticgtfs.api.rest;

import com.morenomjc.transit.staticgtfs.api.rest.controller.StopController;
import com.morenomjc.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.morenomjc.transit.staticgtfs.api.rest.dto.StopDto;
import com.morenomjc.transit.staticgtfs.api.rest.mapper.CommonDtoMapperImpl;
import com.morenomjc.transit.staticgtfs.api.rest.mapper.StopDtoMapperImpl;
import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.core.stop.Stop;
import com.morenomjc.transit.staticgtfs.core.stop.StopService;
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
@WebMvcTest(controllers = {StopController.class})
@Import(value = { CommonDtoMapperImpl.class, StopDtoMapperImpl.class })
class StopControllerTest extends JsonAssertionUtil {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  StopService stopService;

  @Test
  void testGetStopEndpoint() throws Exception {
    givenStop(TestDataProvider.buildStop());

    this.mockMvc.perform(get("/stops/1")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().isOk(),
            jsonPath("$.data.type", is("stops")),
            assertHasJsonFields("$.data.attributes", StopDto.class),
            assertHasJsonFields("$.data.attributes.location_type", DataTypeDto.class),
            assertHasJsonFields("$.data.attributes.wheelchair_boarding", DataTypeDto.class)
        )
    );

  }

  @Test
  void testWhenStopNotFound() throws Exception {
    when(stopService.getStop(anyString())).thenThrow(new DataNotFoundException("Stop not found."));

    this.mockMvc.perform(get("/stops/1")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().isNotFound(),
            jsonPath("$.error").exists(),
            jsonPath("$.error.status").value(404),
            jsonPath("$.error.detail").value("Stop not found.")
        )
    );
  }

  private void givenStop(Stop stop) {
    when(stopService.getStop(anyString())).thenReturn(stop);
  }

}
