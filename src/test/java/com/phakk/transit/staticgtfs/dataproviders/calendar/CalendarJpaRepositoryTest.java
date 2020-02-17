package com.phakk.transit.staticgtfs.dataproviders.calendar;

import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.CalendarJpaRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildCalendarEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CalendarJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CalendarJpaRepository calendarJpaRepository;

    @After
    public void cleanup(){
        entityManager.clear();
    }

    @Test
    public void testFindByIdWhenExists(){
        CalendarEntity expected = buildCalendarEntity();
        givenCalendarExists(expected);

        CalendarEntity calendarEntity = calendarJpaRepository.findByServiceId("1");

        assertThat(calendarEntity).isEqualTo(expected);
    }

    public void givenCalendarExists(CalendarEntity calendarEntity){
        entityManager.persist(calendarEntity);
    }
}
