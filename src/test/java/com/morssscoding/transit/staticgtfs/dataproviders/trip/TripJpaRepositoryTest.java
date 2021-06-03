package com.morssscoding.transit.staticgtfs.dataproviders.trip;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildTripEntity;
import static org.assertj.core.api.Assertions.assertThat;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
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
public class TripJpaRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private TripJpaRepository tripJpaRepository;

  @AfterEach
  public void cleanup() {
    entityManager.clear();
  }

  @Test
  public void testFindById() {
    TripEntity expected = buildTripEntity();
    givenExistingTrip(expected);

    TripEntity tripEntity = tripJpaRepository.findByTripId("1");

    assertThat(tripEntity).isEqualTo(expected);
  }

  @Test
  public void testFindAllByRouteId() {
    givenExistingTrip(buildTripEntity());

    List<TripEntity> tripEntities = tripJpaRepository.findAllByRouteId("101");

    assertThat(tripEntities.size()).isEqualTo(1);
  }

  private void givenExistingTrip(TripEntity tripEntity) {
    entityManager.persist(tripEntity);
  }

}
