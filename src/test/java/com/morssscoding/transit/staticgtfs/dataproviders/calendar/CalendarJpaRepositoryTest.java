package com.morssscoding.transit.staticgtfs.dataproviders.calendar;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildCalendarEntity;
import static org.assertj.core.api.Assertions.assertThat;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.CalendarJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CalendarJpaRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private CalendarJpaRepository calendarJpaRepository;

  @AfterEach
  void cleanup() {
    entityManager.clear();
  }

  @Test
  void testFindByIdWhenExists() {
    CalendarEntity expected = buildCalendarEntity();
    givenCalendarExists(expected);

    CalendarEntity calendarEntity = calendarJpaRepository.findByServiceId("1");

    assertThat(calendarEntity).isEqualTo(expected);
  }

  void givenCalendarExists(CalendarEntity calendarEntity) {
    entityManager.persist(calendarEntity);
  }
}
