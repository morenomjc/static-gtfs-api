package com.phakk.transit.staticgtfs.dataproviders.route;

import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.RouteJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.route.RouteEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.route.RouteRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.route.RouteRepositoryJpaImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildEnumValueRouteType;
import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildRouteEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@Import(RouteRepositoryTest.RouteTestConfiguration.class)
@RunWith(SpringRunner.class)
public class RouteRepositoryTest {

    private RouteRepository routeRepository;

    @Mock
    private RouteJpaRepository routeJpaRepository;
    @Mock
    private EnumValueRepository enumValueRepository;

    @Autowired
    private RouteEntityMapper routeEntityMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        routeRepository = new RouteRepositoryJpaImpl(routeJpaRepository, routeEntityMapper, enumValueRepository);
    }

    @TestConfiguration
    static class RouteTestConfiguration {
        @Bean
        public RouteEntityMapper routeEntityMapper(){
            return Mappers.getMapper(RouteEntityMapper.class);
        }

    }

    @Test
    public void testRouteEntityIsConvertedProperly(){
        whenRouteExists();
        whenRouteTypeIsSearched();

        Route route = routeRepository.getRouteById("1");
        EnumValue routeType = buildEnumValueRouteType();

        assertThat(route).isNotNull();
        assertThat(route.getId()).isEqualTo("1");
        assertThat(route.getAgency()).isEqualTo("agency");
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
    public void testWhenRouteNotFound(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Route not found."));

        whenRouteNotFound();

        routeRepository.getRouteById("1");
    }

    @Test
    public void testWhenRouteTypeMappingFailed(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value not found."));

        whenRouteWithInvalidTypeFound();
        whenInvalidRouteTypeIsSearched();

        routeRepository.getRouteById("1");
    }

    private void whenRouteNotFound(){
        when(routeJpaRepository.findByRouteId(anyString())).thenReturn(null);
    }

    private void whenRouteWithInvalidTypeFound(){
        when(routeJpaRepository.findByRouteId(anyString())).thenReturn(buildRouteEntityWithInvalidRouteType());
    }

    private void whenRouteExists(){
        when(routeJpaRepository.findByRouteId(anyString())).thenReturn(buildRouteEntity());
    }

    private void whenRoutesExists(){
        when(routeJpaRepository.findByAgency(anyString())).thenReturn(Collections.singletonList(buildRouteEntity()));
    }

    private void whenRouteTypeIsSearched(){
        when(enumValueRepository.findEnumValue(anyString(), anyString(), anyString()))
                .thenReturn(buildEnumValueRouteType());
    }

    private RouteEntity buildRouteEntityWithInvalidRouteType(){
        RouteEntity routeEntity = buildRouteEntity();
        routeEntity.setType("-1");
        return routeEntity;
    }

    private void whenInvalidRouteTypeIsSearched(){
        when(enumValueRepository.findEnumValue(anyString(), anyString(), eq("-1")))
                .thenThrow(new DataNotFoundException("Enum value not found."));
    }

}
