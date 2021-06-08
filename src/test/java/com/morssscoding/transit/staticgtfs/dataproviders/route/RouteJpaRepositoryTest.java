package com.morssscoding.transit.staticgtfs.dataproviders.route;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.RouteJpaRepository;
import com.morssscoding.transit.staticgtfs.integration.AbstractDatabaseIntegrationTest;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildRouteEntity;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RouteJpaRepositoryTest extends AbstractDatabaseIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private RouteJpaRepository routeJpaRepository;

  @BeforeEach
  void setup(){
    entityManager.clear();
    entityManager.persistAndFlush(TestDataProvider.buildAgencyEntity());
  }

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

    List<RouteEntity> routeEntities = routeJpaRepository.findByAgency("1");

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
