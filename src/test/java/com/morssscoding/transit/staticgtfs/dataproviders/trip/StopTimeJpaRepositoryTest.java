package com.morssscoding.transit.staticgtfs.dataproviders.trip;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildStopTimeEntity;
import static org.assertj.core.api.Assertions.assertThat;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.StopTimeJpaRepository;
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
class StopTimeJpaRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private StopTimeJpaRepository stopTimeJpaRepository;

  @AfterEach
  void cleanup() {
    entityManager.clear();
  }

  @Test
  void testFindById() {
    StopTimeEntity expected = buildStopTimeEntity();
    givenExistingTrip(expected);

    List<StopTimeEntity> stopTimes = stopTimeJpaRepository.findAllByTripId("1");

    assertThat(stopTimes).hasSize(1);
    assertThat(stopTimes).contains(expected);
  }

  private void givenExistingTrip(StopTimeEntity stopTimeEntity) {
    entityManager.persist(stopTimeEntity);
  }

}
