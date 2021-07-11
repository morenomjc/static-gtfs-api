package com.morenomjc.transit.staticgtfs.dataproviders.route;

import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.core.route.Route;
import com.morenomjc.transit.staticgtfs.core.route.RouteType;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.RouteJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapperImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.route.RouteEntityMapper;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.route.RouteEntityMapperImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.route.RouteRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.route.RouteRepositoryJpaImpl;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(value = {EnumValueEntityMapperImpl.class, RouteEntityMapperImpl.class })
public class RouteRepositoryTest {

  private RouteRepository routeRepository;

  @Mock
  private RouteJpaRepository routeJpaRepository;
  @Mock
  private EnumValueRepository enumValueRepository;

  @Autowired
  private RouteEntityMapper routeEntityMapper;

  @BeforeAll
  public void setup() {
    routeRepository = new RouteRepositoryJpaImpl(routeJpaRepository, routeEntityMapper,
        enumValueRepository);
  }

  @Test
  public void testRouteEntityIsConvertedProperly() {
    whenRouteExists();
    whenRouteTypeIsSearched();

    Route route = routeRepository.getRouteById("1");
    EnumValue routeType = TestDataProvider.buildEnumValueRouteType();

    assertThat(route).isNotNull();
    assertThat(route.getId()).isEqualTo("1");
    assertThat(route.getAgency()).isEqualTo("1");
    assertThat(route.getShortName()).isEqualTo("short");
    assertThat(route.getLongName()).isEqualTo("long");
    assertThat(route.getDesc()).isEqualTo("desc");
    assertThat(route.getType()).isEqualTo(routeType);
    assertThat(route.getUrl()).isEqualTo("test.com");
    assertThat(route.getColor()).isEqualTo("blue");
    assertThat(route.getTextColor()).isEqualTo("white");
    assertThat(route.getSortOrder()).isEqualTo(1);
  }

  @Test
  public void testGetRouteTypes() {
    when(routeJpaRepository.findRouteTypeCounts())
        .thenReturn(Collections.singletonList(new RouteJpaRepository.RouteTypeStatistics() {

          @Override
          public String getType() {
            return "2";
          }

          @Override
          public Integer getCount() {
            return 1;
          }
        }));

    when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("2")))
        .thenReturn(TestDataProvider.buildRouteType().getType());

    List<RouteType> result = routeRepository.getRouteTypes();

    assertThat(result).isNotEmpty();
    assertThat(result.get(0)).isNotNull();
    assertThat(result.get(0).getCount()).isEqualTo(1);
    assertThat(result.get(0).getType()).isNotNull();
    assertThat(result.get(0).getType().getCode()).isEqualTo("2");
    assertThat(result.get(0).getType().getName()).isEqualTo("Rail");
    assertThat(result.get(0).getType().getFile()).isEqualTo("routes");
    assertThat(result.get(0).getType().getField()).isEqualTo("route_type");
  }

  @Test
  public void testGetRouteByAgency() {
    whenRoutesExists();
    whenRouteTypeIsSearched();

    List<Route> routes = routeRepository.getRoutesByAgency("test");

    assertThat(routes).isNotEmpty();
    assertThat(routes.size()).isEqualTo(1);
    assertThat(routes.get(0)).isNotNull();
    assertThat(routes.get(0).getType()).isNotNull();
  }

  @Test
  public void testGetRouteByType() {
    whenRoutesExists();
    whenRouteTypeIsSearched();

    List<Route> routes = routeRepository.getRoutesByType("2");

    assertThat(routes).isNotEmpty();
    assertThat(routes.size()).isEqualTo(1);
    assertThat(routes.get(0)).isNotNull();
    assertThat(routes.get(0).getType()).isNotNull();
  }

  @Test
  public void testWhenRouteNotFound(){
    whenRouteNotFound();

    DataNotFoundException exception = assertThrows(
            DataNotFoundException.class,
            () -> routeRepository.getRouteById("1")
    );

    assertThat(exception.getMessage()).isEqualTo("Route not found.");
  }

  @Test
  public void testWhenRouteTypeMappingFailed(){
    whenRouteWithInvalidTypeFound();
    whenInvalidRouteTypeIsSearched();

    DataNotFoundException exception = assertThrows(
            DataNotFoundException.class,
            () -> routeRepository.getRouteById("1")
    );

    assertThat(exception.getMessage()).isEqualTo("Enum value not found.");
  }

  private void whenRouteNotFound() {
    when(routeJpaRepository.findByRouteId(anyString())).thenReturn(null);
  }

  private void whenRouteWithInvalidTypeFound() {
    when(routeJpaRepository.findByRouteId(anyString()))
        .thenReturn(buildRouteEntityWithInvalidRouteType());
  }

  private void whenRouteExists() {
    when(routeJpaRepository.findByRouteId(anyString())).thenReturn(TestDataProvider.buildRouteEntity());
  }

  private void whenRoutesExists() {
    when(routeJpaRepository.findByAgency(anyString()))
        .thenReturn(Collections.singletonList(TestDataProvider.buildRouteEntity()));
    when(routeJpaRepository.findByType(anyString()))
        .thenReturn(Collections.singletonList(TestDataProvider.buildRouteEntity()));
  }

  private void whenRouteTypeIsSearched() {
    when(enumValueRepository.findEnumValue(anyString(), anyString(), anyString()))
        .thenReturn(TestDataProvider.buildEnumValueRouteType());
  }

  private RouteEntity buildRouteEntityWithInvalidRouteType() {
    RouteEntity routeEntity = TestDataProvider.buildRouteEntity();
    routeEntity.setType("-1");
    return routeEntity;
  }

  private void whenInvalidRouteTypeIsSearched() {
    when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("-1")))
        .thenThrow(new DataNotFoundException("Enum value not found."));
  }

}
