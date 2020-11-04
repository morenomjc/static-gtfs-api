package com.morssscoding.transit.staticgtfs.core;

import com.morssscoding.transit.staticgtfs.core.shape.Shape;
import com.morssscoding.transit.staticgtfs.core.shape.ShapeService;
import com.morssscoding.transit.staticgtfs.core.shape.ShapeServiceImpl;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeRepository;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ShapeServiceTest {

    private ShapeService shapeService;

    @Mock
    private ShapeRepository shapeRepository;

    @Before
    public void setup(){
        shapeService = new ShapeServiceImpl(shapeRepository);
    }

    @Test
    public void testGetShapeById(){
        when(shapeRepository.getShapesById(anyString()))
                .thenReturn(Collections.singletonList(TestDataProvider.buildShape()));

        List<Shape> points = shapeService.getShapePoints("1");

        assertThat(points).hasSize(1);
    }
}
