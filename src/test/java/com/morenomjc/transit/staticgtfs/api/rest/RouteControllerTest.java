package com.morenomjc.transit.staticgtfs.api.rest;

import com.morenomjc.transit.staticgtfs.api.rest.controller.RouteController;
import com.morenomjc.transit.staticgtfs.api.rest.dto.DataTypeDto;
import com.morenomjc.transit.staticgtfs.api.rest.dto.RouteDto;
import com.morenomjc.transit.staticgtfs.api.rest.dto.RouteTypeDto;
import com.morenomjc.transit.staticgtfs.api.rest.mapper.CommonDtoMapperImpl;
import com.morenomjc.transit.staticgtfs.api.rest.mapper.RouteDtoMapperImpl;
import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.core.route.RouteService;
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

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@WebMvcTest(controllers = {RouteController.class})
@Import(value = { CommonDtoMapperImpl.class, RouteDtoMapperImpl.class })
class RouteControllerTest extends JsonAssertionUtil {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  RouteService routeService;

  @Test
  void testGetRouteEndpoint() throws Exception {
    when(routeService.getRoute(anyString())).thenReturn(TestDataProvider.buildRoute());

    this.mockMvc.perform(get("/routes/1")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().isOk(),
            jsonPath("$.data.type", is("routes")),
            jsonPath("$.data.attributes").isNotEmpty(),
            assertHasJsonFields("$.data.attributes", RouteDto.class)
        )
    );
  }

  @Test
  void testWhenRouteNotFound() throws Exception {
    when(routeService.getRoute(anyString()))
        .thenThrow(new DataNotFoundException("Route not found."));

    this.mockMvc.perform(get("/routes/1")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().isNotFound(),
            jsonPath("$.error").exists(),
            jsonPath("$.error.status").value(404),
            jsonPath("$.error.detail").value("Route not found.")
        )
    );
  }

  @Test
  void testGetRoutesByAgencyEndpoint() throws Exception {
    this.mockMvc.perform(get("/routes")
        .param("agencyId", "testAgency")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().isOk(),
            jsonPath("$.data").isArray()
        )
    );
  }

  @Test
  void testGetRoutesByRouteTypeEndpoint() throws Exception {
    this.mockMvc.perform(get("/routes")
        .param("routeType", "2")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().isOk(),
            jsonPath("$.data").isArray()
        )
    );
  }

  @Test
  void testGetRoutesByParamsEndpointWithNoRequestParam() throws Exception {
    this.mockMvc.perform(get("/routes")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().is4xxClientError(),
            jsonPath("$.error").isNotEmpty(),
            jsonPath("$.error.status", is(400)),
            jsonPath("$.error.detail", is("At least 1 request param is required"))
        )
    );
  }

  @Test
  void testGetRoutesTypesEndpoint() throws Exception {
    when(routeService.getRouteTypes()).thenReturn(Collections.singletonList(TestDataProvider.buildRouteType()));

    this.mockMvc.perform(get("/routes/list")
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(
        matchAll(
            status().isOk(),
            jsonPath("$.data").isArray(),
            jsonPath("$.data[0].type").value("route_types"),
            jsonPath("$.data[0].attributes").exists(),
            assertHasJsonFields("$.data[0].attributes", RouteTypeDto.class),
            assertHasJsonFields("$.data[0].attributes.route_type", DataTypeDto.class),
            jsonPath("$.data[0].attributes.count").value(1)
        )
    );
  }
}
