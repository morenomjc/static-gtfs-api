package com.morenomjc.transit.staticgtfs.dataproviders.stop;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.StopJpaRepository;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import com.morenomjc.transit.staticgtfs.integration.AbstractDatabaseIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StopJpaRepositoryIT extends AbstractDatabaseIntegrationTest {

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
    StopEntity expected = TestDataProvider.buildStopEntity();
    givenExistingStop(expected);

    StopEntity stopEntity = stopJpaRepository.findByStopId("1");

    assertThat(stopEntity).isEqualTo(expected);
  }

  private void givenExistingStop(StopEntity stopEntity) {
    entityManager.persist(stopEntity);
  }

}
