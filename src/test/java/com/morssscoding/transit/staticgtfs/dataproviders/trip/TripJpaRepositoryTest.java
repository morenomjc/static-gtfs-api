package com.morssscoding.transit.staticgtfs.dataproviders.trip;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildTripEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class TripJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TripJpaRepository tripJpaRepository;

    @After
    public void cleanup(){
        entityManager.clear();
    }

    @Test
    public void testFindById(){
        TripEntity expected = buildTripEntity();
        givenExistingTrip(expected);

        TripEntity tripEntity = tripJpaRepository.findByTripId("1");

        assertThat(tripEntity).isEqualTo(expected);
    }

    @Test
    public void testFindAllByRouteId(){
        givenExistingTrip(buildTripEntity());

        List<TripEntity> tripEntities = tripJpaRepository.findAllByRouteId("101");

        assertThat(tripEntities.size()).isEqualTo(1);
    }

    private void givenExistingTrip(TripEntity tripEntity){
        entityManager.persist(tripEntity);
    }

}
