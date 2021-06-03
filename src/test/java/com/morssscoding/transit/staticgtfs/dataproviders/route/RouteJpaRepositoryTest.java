package com.morssscoding.transit.staticgtfs.dataproviders.route;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildRouteEntity;
import static org.assertj.core.api.Assertions.assertThat;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.RouteJpaRepository;
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
class RouteJpaRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private RouteJpaRepository routeJpaRepository;

  @AfterEach
  void cleanup() {
    entityManager.clear();
  }

  @Test
  void testFindByIdWhenExists() {
    RouteEntity expected = buildRouteEntity();
    givenExistingRoute(expected);

    RouteEntity routeEntity = routeJpaRepository.findByRouteId("1");

    assertThat(routeEntity).isEqualTo(expected);
  }

  @Test
  void testFindRoutesByAgencyWhenExists() {
    RouteEntity expected = buildRouteEntity();
    givenExistingRoute(expected);

    List<RouteEntity> routeEntities = routeJpaRepository.findByAgency("agency");

    assertThat(routeEntities).isNotEmpty();
    assertThat(routeEntities.get(0)).isEqualTo(expected);
  }

  @Test
  void testFindRoutesByTypeWhenExists() {
    RouteEntity expected = buildRouteEntity();
    givenExistingRoute(expected);

    List<RouteEntity> routeEntities = routeJpaRepository.findByType("700");

    assertThat(routeEntities).isNotEmpty();
    assertThat(routeEntities.get(0)).isEqualTo(expected);
  }

  @Test
  void testFindRouteTypeCounts() {
    RouteEntity route1 = buildRouteEntity();
    RouteEntity route2 = buildRouteEntity();
    route2.setRouteId("2");

    entityManager.persistAndFlush(route1);
    entityManager.persistAndFlush(route2);

    List<RouteJpaRepository.RouteTypeStatistics> result = routeJpaRepository.findRouteTypeCounts();

    assertThat(result).isNotEmpty().hasSize(1);
    assertThat(result.get(0).getType()).isEqualTo("700");
    assertThat(result.get(0).getCount()).isEqualTo(2);
  }

  private void givenExistingRoute(RouteEntity entity) {
    entityManager.persist(entity);
  }

}
