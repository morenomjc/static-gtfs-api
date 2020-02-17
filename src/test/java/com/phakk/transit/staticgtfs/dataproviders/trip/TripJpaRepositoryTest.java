package com.phakk.transit.staticgtfs.dataproviders.trip;

import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildTripEntity;
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

    private void givenExistingTrip(TripEntity tripEntity){
        entityManager.persist(tripEntity);
    }

}
