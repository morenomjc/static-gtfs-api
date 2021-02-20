package com.morssscoding.transit.staticgtfs.api.rest;

import com.morssscoding.transit.staticgtfs.api.rest.controller.ShapeController;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.ShapePointDtoMapper;
import com.morssscoding.transit.staticgtfs.core.shape.ShapeService;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@WebMvcTest(controllers = ShapeController.class)
@RunWith(SpringRunner.class)
@Import(ShapeControllerTest.TestConfig.class)
public class ShapeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShapeService shapeService;

    @Autowired
    private ShapePointDtoMapper shapepointDtoMapper;

    @Test
    public void testGetShapeEndpoint() throws Exception{
        when(shapeService.getShapePoints(anyString())).thenReturn(Collections.singletonList(TestDataProvider.buildShape()));

        this.mockMvc.perform(get("/shapes/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                matchAll(
                        status().isOk(),
                        jsonPath("$.data.attributes.shape_id").isString(),
                        jsonPath("$.data.attributes.points").isArray(),
                        jsonPath("$.data.attributes.points[0].shape_pt_lat").isNumber(),
                        jsonPath("$.data.attributes.points[0].shape_pt_lon").isNumber(),
                        jsonPath("$.data.attributes.points[0].shape_pt_sequence").isNumber(),
                        jsonPath("$.data.attributes.points[0].shape_dist_traveled").isNumber(),
                        jsonPath("$.data.links[0].rel").value("self")
                )
        );
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public ShapePointDtoMapper shapeDtoMapper(){
            return Mappers.getMapper(ShapePointDtoMapper.class);
        }
    }
}
