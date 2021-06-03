package com.morssscoding.transit.staticgtfs.dataproviders.shape;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.morssscoding.transit.staticgtfs.core.shape.Shape;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.ShapeEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.ShapeJpaRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeRepositoryImpl;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(ShapeRepositoryTest.TestConfig.class)
class ShapeRepositoryTest {

  private ShapeRepository shapeRepository;

  @Mock
  private ShapeJpaRepository shapeJpaRepository;

  @Autowired
  private ShapeEntityMapper shapeEntityMapper;


  @BeforeAll
  void setup() {
    shapeRepository = new ShapeRepositoryImpl(shapeJpaRepository, shapeEntityMapper);
  }

  @TestConfiguration
  static class TestConfig {

    @Bean
    ShapeEntityMapper shapeEntityMapper() {
      return Mappers.getMapper(ShapeEntityMapper.class);
    }
  }

  @Test
  void testIfShapeIsConvertedProperly() {
    when(shapeJpaRepository.findByShapeId(anyString()))
        .thenReturn(Collections.singletonList(TestDataProvider.buildShapeEntity()));

    List<Shape> shapes = shapeRepository.getShapesById("1");

    assertThat(shapes).hasSize(1);
    assertThat(shapes.get(0).getId()).isEqualTo("1");
    assertThat(shapes.get(0).getLat()).isEqualTo(12.4);
    assertThat(shapes.get(0).getLon()).isEqualTo(12.5);
    assertThat(shapes.get(0).getSequence()).isEqualTo(1);
    assertThat(shapes.get(0).getDistanceTraveled()).isEqualTo(0.1);
  }

  @Test
  void testIfShapeIsConvertedProperlyToEntity() {
    Shape shape = TestDataProvider.buildShape();
    ShapeEntity shapeEntity = shapeEntityMapper.toEntity(shape);

    assertThat(shapeEntity).isNotNull();
    assertThat(shapeEntity.getShapeId()).isNotNull();
    assertThat(shapeEntity.getLat()).isNotNull();
    assertThat(shapeEntity.getLon()).isNotNull();
    assertThat(shapeEntity.getSequence()).isNotNull();
    assertThat(shapeEntity.getDistanceTraveled()).isNotNull();
  }

}
