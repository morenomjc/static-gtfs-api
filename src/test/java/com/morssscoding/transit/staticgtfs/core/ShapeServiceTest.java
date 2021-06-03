package com.morssscoding.transit.staticgtfs.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.morssscoding.transit.staticgtfs.core.shape.Shape;
import com.morssscoding.transit.staticgtfs.core.shape.ShapeService;
import com.morssscoding.transit.staticgtfs.core.shape.ShapeServiceImpl;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeRepository;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShapeServiceTest {

  private ShapeService shapeService;

  @Mock
  private ShapeRepository shapeRepository;

  @BeforeAll
  void setup() {
    shapeService = new ShapeServiceImpl(shapeRepository);
  }

  @Test
  void testGetShapeById() {
    when(shapeRepository.getShapesById(anyString()))
        .thenReturn(Collections.singletonList(TestDataProvider.buildShape()));

    List<Shape> points = shapeService.getShapePoints("1");

    assertThat(points).hasSize(1);
  }
}
