package com.morssscoding.transit.staticgtfs.dataproviders.shape;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.ShapeEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.ShapeJpaRepository;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShapeJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShapeJpaRepository shapeJpaRepository;

    @After
    public void cleanup(){
        entityManager.clear();
    }

    @Test
    public void testFindByShapeId(){
        ShapeEntity shapeEntity = TestDataProvider.buildShapeEntity();
        givenExistingShape(shapeEntity);

        List<ShapeEntity> shapeEntities = shapeJpaRepository.findByShapeId("1");

        assertThat(shapeEntities).hasSize(1);
        assertThat(shapeEntities.get(0)).isEqualTo(shapeEntity);
    }

    @Test
    public void testSave(){
        ShapeEntity shapeEntity = TestDataProvider.buildShapeEntity();

        shapeEntity = shapeJpaRepository.save(shapeEntity);

        assertThat(shapeEntity.getId()).isNotNull();
    }

    private void givenExistingShape(ShapeEntity shapeEntity){
        entityManager.persist(shapeEntity);
    }

}
