package com.phakk.transit.staticgtfs.api.rest;

import com.phakk.transit.staticgtfs.api.rest.controller.RouteController;
import com.phakk.transit.staticgtfs.api.rest.mapper.RouteDtoMapper;
import com.phakk.transit.staticgtfs.configuration.MapperConfiguration;
import com.phakk.transit.staticgtfs.core.constants.RouteTypeEnum;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.core.route.RouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { RouteController.class })
@Import(MapperConfiguration.class)
@RunWith(SpringRunner.class)
public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RouteDtoMapper routeDtoMapper;

    @MockBean
    private RouteService routeService;

    @Test
    public void testGetRouteEndpoint() throws Exception {
        when(routeService.getRoute(anyString())).thenReturn(buildRoute());

        this.mockMvc.perform(get("/routes/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content()
                        .json("{\n" +
                                "    \"meta\": {\n" +
                                "        \"apiVersion\": \"v1\",\n" +
                                "        \"staticGtfsVersion\": \"v1.0\"\n" +
                                "    },\n" +
                                "    \"data\": {\n" +
                                "        \"route_id\": \"1\",\n" +
                                "        \"agency_id\": \"Test\",\n" +
                                "        \"route_short_name\": \"short name\",\n" +
                                "        \"route_long_name\": \"long name\",\n" +
                                "        \"route_desc\": \"desc\",\n" +
                                "        \"route_type\": {\n" +
                                "            \"id\": \"700\",\n" +
                                "            \"desc\": \"Bus Service\"\n" +
                                "        },\n" +
                                "        \"route_url\": \"test.com\",\n" +
                                "        \"route_color\": \"blue\",\n" +
                                "        \"route_text_color\": \"white\",\n" +
                                "        \"route_sort_order\": 1\n" +
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
                                "        \"detail\": \"Route not found.\"\n" +
                                "    }\n" +
                                "}")
        );
    }

    private Route buildRoute(){
        return new Route(
                "1",
                "Test",
                "short name",
                "long name",
                "desc",
                RouteTypeEnum.ROUTE_700,
                "test.com",
                "blue",
                "white",
                1
        );
    }
}
