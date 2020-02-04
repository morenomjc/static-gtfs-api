package com.phakk.transit.staticgtfs.datastore.stop;

import com.phakk.transit.staticgtfs.datastore.jpa.entity.StopEntity;
import com.phakk.transit.staticgtfs.datastore.jpa.repository.StopJpaRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class StopJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StopJpaRepository stopJpaRepository;

    @After
    public void cleanup(){
        entityManager.clear();
    }

    @Test
    public void testFindById(){
        StopEntity expected = buildStopEntity();
        givenExistingStop(expected);

        StopEntity stopEntity = stopJpaRepository.findByStopId("1");

        assertThat(stopEntity).isEqualTo(expected);
    }

    private void givenExistingStop(StopEntity stopEntity){
        entityManager.persist(stopEntity);
    }

    private StopEntity buildStopEntity(){
        StopEntity stop1 = new StopEntity();
        stop1.setStopId("1");
        stop1.setStopCode("TEST");
        stop1.setName("Test Station");
        stop1.setDesc("Test Station");
        stop1.setLat(15.5737673);
        stop1.setLon(122.0481448);
        stop1.setZoneId("1");
        stop1.setUrl("test.com/stops/TEST");
        stop1.setStopType("1");
        stop1.setParentStation(null);
        stop1.setTimezone("Asia/Singapore");
        stop1.setWheelchairBoarding("1");
        stop1.setLevelId(null);
        stop1.setPlatformCode(null);
        return stop1;
    }
}
