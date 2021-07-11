package com.morenomjc.transit.staticgtfs.dataproviders.calendar;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.CalendarJpaRepository;
import com.morenomjc.transit.staticgtfs.integration.AbstractDatabaseIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static com.morenomjc.transit.staticgtfs.utils.TestDataProvider.buildCalendarEntity;
import static org.assertj.core.api.Assertions.assertThat;

class CalendarJpaRepositoryTest extends AbstractDatabaseIntegrationTest {

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
