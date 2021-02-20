package com.morssscoding.transit.staticgtfs.api.rest;

import com.morssscoding.transit.staticgtfs.api.rest.controller.RouteController;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.RouteDtoMapper;
import com.morssscoding.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morssscoding.transit.staticgtfs.core.route.RouteService;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildRouteType;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@WebMvcTest(controllers = { RouteController.class })
@Import(RouteControllerTest.RouteTestConfiguration.class)
@RunWith(SpringRunner.class)
public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RouteDtoMapper routeDtoMapper;

    @MockBean
    private RouteService routeService;

    @TestConfiguration
    static class RouteTestConfiguration {
        @Bean
        public RouteDtoMapper routeDtoMapper(){
            return Mappers.getMapper(RouteDtoMapper.class);
        }
    }

    @Test
    public void testGetRouteEndpoint() throws Exception {
        when(routeService.getRoute(anyString())).thenReturn(TestDataProvider.buildRoute());

        this.mockMvc.perform(get("/routes/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
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
                                "    \"data\": {\n" +
                                "        \"type\": \"routes\",\n" +
                                "        \"attributes\": {\n" +
                                "            \"route_id\": \"1\",\n" +
                                "            \"agency_id\": \"agency\",\n" +
                                "            \"route_short_name\": \"short\",\n" +
                                "            \"route_long_name\": \"long\",\n" +
                                "            \"route_desc\": \"desc\",\n" +
                                "            \"route_type\": {\n" +
                                "                \"code\": \"700\",\n" +
                                "                \"desc\": \"Bus Service\"\n" +
                                "            },\n" +
                                "            \"route_url\": \"test.com\",\n" +
                                "            \"route_color\": \"blue\",\n" +
                                "            \"route_text_color\": \"white\",\n" +
                                "            \"route_sort_order\": 1\n" +
                                "        }\n" +
                                "    }\n" +
                                "}")
        );
    }

    @Test
    public void testWhenRouteNotFound() throws Exception{
        when(routeService.getRoute(anyString())).thenThrow(new DataNotFoundException("Route not found."));

        this.mockMvc.perform(get("/routes/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                matchAll(
                        status().isNotFound(),
                        jsonPath("$.error").exists(),
                        jsonPath("$.error.status").value(404),
                        jsonPath("$.error.title").value("Resource Not Found")
                )
        );
    }

    @Test
    public void testGetRoutesByAgencyEndpoint() throws Exception{
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
    public void testGetRoutesByRouteTypeEndpoint() throws Exception{
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
    public void testGetRoutesByParamsEndpointWithNoRequestParam() throws Exception{
        this.mockMvc.perform(get("/routes")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().is4xxClientError()
        );
    }

    @Test
    public void testGetRoutesTypesEndpoint() throws Exception{
        when(routeService.getRouteTypes()).thenReturn(Collections.singletonList(buildRouteType()));

        this.mockMvc.perform(get("/routes/list")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                matchAll(
                        status().isOk(),
                        jsonPath("$.data").isArray(),
                        jsonPath("$.data[0].type").value("route_types"),
                        jsonPath("$.data[0].attributes").exists(),
                        jsonPath("$.data[0].attributes.route_type.code").value("2"),
                        jsonPath("$.data[0].attributes.route_type.desc").value("Rail"),
                        jsonPath("$.data[0].attributes.count").value(1)
                )
        );
    }
}
