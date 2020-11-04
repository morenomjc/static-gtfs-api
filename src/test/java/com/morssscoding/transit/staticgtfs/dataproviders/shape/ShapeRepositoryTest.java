package com.morssscoding.transit.staticgtfs.dataproviders.shape;

import com.morssscoding.transit.staticgtfs.core.shape.Shape;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.ShapeEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.ShapeJpaRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeRepositoryImpl;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Import(ShapeRepositoryTest.TestConfig.class)
@RunWith(SpringRunner.class)
public class ShapeRepositoryTest {

    private ShapeRepository shapeRepository;

    @Mock
    private ShapeJpaRepository shapeJpaRepository;

    @Autowired
    private ShapeEntityMapper shapeEntityMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        shapeRepository = new ShapeRepositoryImpl(shapeJpaRepository, shapeEntityMapper);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ShapeEntityMapper shapeEntityMapper(){
            return Mappers.getMapper(ShapeEntityMapper.class);
        }
    }

    @Test
    public void testIfShapeIsConvertedProperly(){
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
    public void testIfShapeIsConvertedProperlyToEntity(){
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
