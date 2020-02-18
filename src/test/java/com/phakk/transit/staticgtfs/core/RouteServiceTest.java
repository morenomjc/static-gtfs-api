package com.phakk.transit.staticgtfs.core;

import com.phakk.transit.staticgtfs.core.constants.RouteType;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.core.route.RouteService;
import com.phakk.transit.staticgtfs.core.route.RouteServiceImpl;
import com.phakk.transit.staticgtfs.dataproviders.repository.route.RouteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

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
        Route expected = buildRoute();
        givenARoute(expected);

        Route result = routeService.getRoute("1");

        assertThat(result).isEqualTo(expected);
    }

    private void givenARoute(Route route){
        when(routeRepository.getRouteById(anyString())).thenReturn(route);
    }

    private Route buildRoute(){
        return new Route(
                "1",
                "Test",
                "short name",
                "long name",
                "desc",
                RouteType.ROUTE_700_BUS,
                "test.com",
                "blue",
                "white",
                1
        );
    }
}
