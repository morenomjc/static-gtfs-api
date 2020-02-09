package com.phakk.transit.staticgtfs.datastore.route;

import com.phakk.transit.staticgtfs.configuration.MapperConfiguration;
import com.phakk.transit.staticgtfs.core.constants.RouteTypeEnum;
import com.phakk.transit.staticgtfs.core.exception.ConstantsMappingException;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.datastore.jpa.entity.RouteEntity;
import com.phakk.transit.staticgtfs.datastore.jpa.repository.RouteJpaRepository;
import com.phakk.transit.staticgtfs.datastore.repository.route.RouteEntityMapper;
import com.phakk.transit.staticgtfs.datastore.repository.route.RouteRepository;
import com.phakk.transit.staticgtfs.datastore.repository.route.RouteRepositoryJpaImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Import(MapperConfiguration.class)
@RunWith(SpringRunner.class)
public class RouteRepositoryTest {

    private RouteRepository routeRepository;

    @Mock
    private RouteJpaRepository routeJpaRepository;

    @Autowired
    private RouteEntityMapper routeEntityMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        routeRepository = new RouteRepositoryJpaImpl(routeJpaRepository, routeEntityMapper);
    }

    @Test
    public void testRouteEntityIsConvertedProperly(){
        whenRouteExists();

        Route route = routeRepository.getRouteById("1");

        assertThat(route).isNotNull();
        assertThat(route.getId()).isEqualTo("1");
        assertThat(route.getAgency()).isEqualTo("agency");
        assertThat(route.getShortName()).isEqualTo("short");
        assertThat(route.getLongName()).isEqualTo("long");
        assertThat(route.getDesc()).isEqualTo("desc");
        assertThat(route.getType()).isEqualTo(RouteTypeEnum.ROUTE_700);
        assertThat(route.getUrl()).isEqualTo("test.com");
        assertThat(route.getColor()).isEqualTo("blue");
        assertThat(route.getTextColor()).isEqualTo("white");
        assertThat(route.getSortOrder()).isEqualTo(1);
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
        expectedException.expect(ConstantsMappingException.class);
        expectedException.expectMessage(equalTo("Failed to map route type"));

        whenRouteWithInvalidTypeFound();

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

    private RouteEntity buildRouteEntityWithInvalidRouteType(){
        RouteEntity routeEntity = buildRouteEntity();
        routeEntity.setType("-1");
        return routeEntity;
    }

    private RouteEntity buildRouteEntity(){
        RouteEntity routeEntity = new RouteEntity();
        routeEntity.setRouteId("1");
        routeEntity.setAgency("agency");
        routeEntity.setShortName("short");
        routeEntity.setLongName("long");
        routeEntity.setDesc("desc");
        routeEntity.setType("700");
        routeEntity.setUrl("test.com");
        routeEntity.setColor("blue");
        routeEntity.setTextColor("white");
        routeEntity.setSortOrder(1);

        return routeEntity;
    }
}
