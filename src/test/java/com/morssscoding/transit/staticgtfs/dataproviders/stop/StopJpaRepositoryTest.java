package com.morssscoding.transit.staticgtfs.dataproviders.stop;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.StopJpaRepository;
import com.morssscoding.transit.staticgtfs.integration.AbstractDatabaseIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildStopEntity;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StopJpaRepositoryTest extends AbstractDatabaseIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private StopJpaRepository stopJpaRepository;

  @AfterEach
  void cleanup() {
    entityManager.clear();
  }

  @Test
  void testFindById() {
    StopEntity expected = buildStopEntity();
    givenExistingStop(expected);

    StopEntity stopEntity = stopJpaRepository.findByStopId("1");

    assertThat(stopEntity).isEqualTo(expected);
  }

  private void givenExistingStop(StopEntity stopEntity) {
    entityManager.persist(stopEntity);
  }

}
