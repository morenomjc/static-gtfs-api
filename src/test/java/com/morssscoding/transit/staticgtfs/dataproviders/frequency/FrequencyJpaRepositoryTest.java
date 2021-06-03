package com.morssscoding.transit.staticgtfs.dataproviders.frequency;

import static org.assertj.core.api.Assertions.assertThat;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.FrequencyEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.FrequencyJpaRepository;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import java.util.Optional;
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
class FrequencyJpaRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private FrequencyJpaRepository frequencyJpaRepository;

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
