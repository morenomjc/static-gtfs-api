package com.morssscoding.transit.staticgtfs.dataproviders.route;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.RouteJpaRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildRouteEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RouteJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RouteJpaRepository routeJpaRepository;

    @After
    public void cleanup(){
        entityManager.clear();
    }

    @Test
    public void testFindByIdWhenExists(){
        RouteEntity expected = buildRouteEntity();
        givenExistingRoute(expected);

        RouteEntity routeEntity = routeJpaRepository.findByRouteId("1");

        assertThat(routeEntity).isEqualTo(expected);
    }

    @Test
    public void testFindRoutesByAgencyWhenExists(){
        RouteEntity expected = buildRouteEntity();
        givenExistingRoute(expected);

        List<RouteEntity> routeEntities = routeJpaRepository.findByAgency("agency");

        assertThat(routeEntities).isNotEmpty();
        assertThat(routeEntities.get(0)).isEqualTo(expected);
    }

    private void givenExistingRoute(RouteEntity entity){
        entityManager.persist(entity);
    }

}
