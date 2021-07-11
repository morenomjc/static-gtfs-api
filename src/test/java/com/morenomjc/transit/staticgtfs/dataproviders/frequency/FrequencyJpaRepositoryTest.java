package com.morenomjc.transit.staticgtfs.dataproviders.frequency;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.FrequencyEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.FrequencyJpaRepository;
import com.morenomjc.transit.staticgtfs.integration.AbstractDatabaseIntegrationTest;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FrequencyJpaRepositoryTest extends AbstractDatabaseIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private FrequencyJpaRepository frequencyJpaRepository;

  @BeforeEach
  void setup(){
    entityManager.clear();
    entityManager.persistAndFlush(TestDataProvider.buildAgencyEntity());
    entityManager.persistAndFlush(TestDataProvider.buildRouteEntity());
    entityManager.persistAndFlush(TestDataProvider.buildCalendarEntity());
    entityManager.persistAndFlush(TestDataProvider.buildTripEntity());
  }

  @AfterEach
  void cleanup() {
    entityManager.clear();
  }

  @Test
  void testFindById() {
    givenExistingFrequencyEntity();
    Optional<FrequencyEntity> frequencyEntity = frequencyJpaRepository.findByTripId("1");

    assertThat(frequencyEntity.isPresent()).isTrue();
  }

  @Test
  void testFindByIdEmpty() {
    Optional<FrequencyEntity> frequencyEntity = frequencyJpaRepository.findByTripId("-1");

    assertThat(frequencyEntity.isPresent()).isFalse();
  }

  private void givenExistingFrequencyEntity() {
    entityManager.persist(TestDataProvider.buildFrequencyEntity());
  }
}
