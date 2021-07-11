package com.morenomjc.transit.staticgtfs.dataproviders.trip;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TripJpaRepositoryTest extends AbstractDatabaseIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private TripJpaRepository tripJpaRepository;

  @BeforeEach
  void setup(){
    entityManager.clear();
    entityManager.persistAndFlush(TestDataProvider.buildAgencyEntity());
    entityManager.persistAndFlush(TestDataProvider.buildRouteEntity());
    entityManager.persistAndFlush(TestDataProvider.buildCalendarEntity());
  }

  @AfterEach
  public void cleanup() {
    entityManager.clear();
  }

  @Test
  public void testFindById() {
    TripEntity expected = TestDataProvider.buildTripEntity();
    givenExistingTrip(expected);

    TripEntity tripEntity = tripJpaRepository.findByTripId("1");

    assertThat(tripEntity).isEqualTo(expected);
  }

  @Test
  public void testFindAllByRouteId() {
    givenExistingTrip(TestDataProvider.buildTripEntity());

    List<TripEntity> tripEntities = tripJpaRepository.findAllByRouteId("1");

    assertThat(tripEntities.size()).isEqualTo(1);
  }

  private void givenExistingTrip(TripEntity tripEntity) {
    entityManager.persist(tripEntity);
  }

}
