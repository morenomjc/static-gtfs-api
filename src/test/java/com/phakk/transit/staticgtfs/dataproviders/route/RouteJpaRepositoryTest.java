package com.phakk.transit.staticgtfs.dataproviders.route;

import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.RouteJpaRepository;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RouteJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RouteJpaRepository routeJpaRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

    private void givenExistingRoute(RouteEntity entity){
        entityManager.persist(entity);
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
