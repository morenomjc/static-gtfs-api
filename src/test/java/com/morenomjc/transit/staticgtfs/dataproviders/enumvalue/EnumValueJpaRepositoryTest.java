package com.morenomjc.transit.staticgtfs.dataproviders.enumvalue;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.EnumValueEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.EnumValueJpaRepository;
import com.morenomjc.transit.staticgtfs.integration.AbstractDatabaseIntegrationTest;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EnumValueJpaRepositoryTest extends AbstractDatabaseIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private EnumValueJpaRepository enumValueJpaRepository;

  @AfterEach
  void cleanup() {
    entityManager.clear();
  }

  @Test
  void testFindByFileFieldAndCode() {
    EnumValueEntity expected = TestDataProvider.buildEnumValueEntity();
    givenExistingEnumValue(expected);

    EnumValueEntity enumValueEntity = enumValueJpaRepository
        .findByFileAndFieldAndCode("stops", "location_type", "0");

    assertThat(enumValueEntity).isEqualTo(expected);
  }

  private void givenExistingEnumValue(EnumValueEntity enumValueEntity) {
    entityManager.persist(enumValueEntity);
  }

}
