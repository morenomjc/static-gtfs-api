package com.morenomjc.transit.staticgtfs.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.morenomjc.transit.staticgtfs.core.route.Route;
import com.morenomjc.transit.staticgtfs.core.route.RouteService;
import com.morenomjc.transit.staticgtfs.core.route.RouteType;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.route.RouteRepository;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import com.morenomjc.transit.staticgtfs.core.route.RouteServiceImpl;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RouteServiceTest {

  private RouteService routeService;

  @Mock
  private RouteRepository routeRepository;

  @BeforeAll
  void setup() {
    routeService = new RouteServiceImpl(routeRepository);
  }

  @Test
  void testGetRouteById() {
    Route expected = TestDataProvider.buildRoute();
    givenARoute(expected);

    Route result = routeService.getRoute("1");

    assertThat(result).isEqualTo(expected);
  }

  @Test
  void testGetRoutesByAgency() {
    Route expected = TestDataProvider.buildRoute();
    when(routeRepository.getRoutesByAgency(anyString()))
        .thenReturn(Collections.singletonList(expected));

    List<Route> actual = routeService.getByAgency("test");

    assertThat(actual).isNotEmpty();
    assertThat(actual.get(0)).isEqualTo(expected);
  }

  @Test
  void testGetRoutesByType() {
    Route expected = TestDataProvider.buildRoute();
    when(routeRepository.getRoutesByType(anyString()))
        .thenReturn(Collections.singletonList(expected));

    List<Route> actual = routeService.getByRouteType("2");

    assertThat(actual).isNotEmpty();
    assertThat(actual.get(0)).isEqualTo(expected);
  }

  @Test
  void testGetRouteTypes() {
    when(routeRepository.getRouteTypes())
        .thenReturn(Collections.singletonList(TestDataProvider.buildRouteType()));

    List<RouteType> result = routeService.getRouteTypes();

    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isNotNull();
    assertThat(result.get(0).getType()).isNotNull();
    assertThat(result.get(0).getCount()).isEqualTo(1);
    assertThat(result.get(0).getType().getCode()).isEqualTo("2");
    assertThat(result.get(0).getType().getName()).isEqualTo("Rail");
    assertThat(result.get(0).getType().getFile()).isEqualTo("routes");
    assertThat(result.get(0).getType().getField()).isEqualTo("route_type");
  }

  private void givenARoute(Route route) {
    when(routeRepository.getRouteById(anyString())).thenReturn(route);
  }

}
