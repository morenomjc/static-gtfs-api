package com.morssscoding.transit.staticgtfs.dataproviders.trip;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.StopTimeJpaRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildStopTimeEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class StopTimeJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StopTimeJpaRepository stopTimeJpaRepository;

    @After
    public void cleanup(){
        entityManager.clear();
    }

    @Test
    public void testFindById(){
        StopTimeEntity expected = buildStopTimeEntity();
        givenExistingTrip(expected);

        List<StopTimeEntity> stopTimes = stopTimeJpaRepository.findAllByTripId("1");

        assertThat(stopTimes).hasSize(1);
        assertThat(stopTimes).contains(expected);
    }

    private void givenExistingTrip(StopTimeEntity stopTimeEntity){
        entityManager.persist(stopTimeEntity);
    }

}
