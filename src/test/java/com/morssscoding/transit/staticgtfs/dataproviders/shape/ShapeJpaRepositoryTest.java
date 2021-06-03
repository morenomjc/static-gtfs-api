package com.morssscoding.transit.staticgtfs.dataproviders.shape;

import static org.assertj.core.api.Assertions.assertThat;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.ShapeEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.ShapeJpaRepository;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ShapeJpaRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private ShapeJpaRepository shapeJpaRepository;

  @AfterEach
  void cleanup() {
    entityManager.clear();
  }

  @Test
  void testFindByShapeId() {
    ShapeEntity shapeEntity = TestDataProvider.buildShapeEntity();
    givenExistingShape(shapeEntity);

    List<ShapeEntity> shapeEntities = shapeJpaRepository.findByShapeId("1");

    assertThat(shapeEntities).hasSize(1);
    assertThat(shapeEntities.get(0)).isEqualTo(shapeEntity);
  }

  @Test
  void testSave() {
    ShapeEntity shapeEntity = TestDataProvider.buildShapeEntity();

    shapeEntity = shapeJpaRepository.save(shapeEntity);

    assertThat(shapeEntity.getId()).isNotNull();
  }

  private void givenExistingShape(ShapeEntity shapeEntity) {
    entityManager.persist(shapeEntity);
  }

}
