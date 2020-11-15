package com.morssscoding.transit.staticgtfs.core;

import com.morssscoding.transit.staticgtfs.core.route.RouteType;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import com.morssscoding.transit.staticgtfs.core.route.Route;
import com.morssscoding.transit.staticgtfs.core.route.RouteService;
import com.morssscoding.transit.staticgtfs.core.route.RouteServiceImpl;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.route.RouteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RouteServiceTest {

    private RouteService routeService;

    @Mock
    private RouteRepository routeRepository;

    @Before
    public void setup(){
        routeService = new RouteServiceImpl(routeRepository);
    }

    @Test
    public void testGetRouteById(){
        Route expected = TestDataProvider.buildRoute();
        givenARoute(expected);

        Route result = routeService.getRoute("1");

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGetRoutesByAgency(){
        Route expected = TestDataProvider.buildRoute();
        when(routeRepository.getRoutesByAgency(anyString())).thenReturn(Collections.singletonList(expected));

        List<Route> actual = routeService.getRoutesByAgency("test");

        assertThat(actual).isNotEmpty();
        assertThat(actual.get(0)).isEqualTo(expected);
    }

    @Test
    public void testGetRouteTypes(){
        when(routeRepository.getRouteTypes()).thenReturn(Collections.singletonList(TestDataProvider.buildRouteType()));

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

    private void givenARoute(Route route){
        when(routeRepository.getRouteById(anyString())).thenReturn(route);
    }

}
