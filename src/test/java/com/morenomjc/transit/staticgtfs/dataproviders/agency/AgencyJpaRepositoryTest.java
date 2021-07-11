package com.morenomjc.transit.staticgtfs.dataproviders.agency;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.AgencyJpaRepository;
import com.morenomjc.transit.staticgtfs.integration.AbstractDatabaseIntegrationTest;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AgencyJpaRepositoryTest extends AbstractDatabaseIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private AgencyJpaRepository agencyJpaRepository;

  @AfterEach
  public void cleanup() {
    entityManager.clear();
  }

  @Test
  public void testFindById() {
    AgencyEntity expected = TestDataProvider.buildAgencyEntity();
    givenExistingAgency(expected);

    AgencyEntity agencyEntity = agencyJpaRepository.findByAgencyId("1");

    assertThat(agencyEntity).isEqualTo(expected);
  }

  @Test
  public void testFindAll() {
    AgencyEntity expected = TestDataProvider.buildAgencyEntity();
    givenExistingAgency(expected);

    List<AgencyEntity> agencyEntities = agencyJpaRepository.findAll();

    assertThat(agencyEntities).hasSize(1);
    assertThat(agencyEntities).contains(expected);
  }

  private void givenExistingAgency(AgencyEntity agencyEntity) {
    entityManager.persist(agencyEntity);
  }
}
