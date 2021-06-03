package com.morssscoding.transit.staticgtfs.dataproviders.enumvalue;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildEnumValueEntity;
import static org.assertj.core.api.Assertions.assertThat;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.EnumValueEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.EnumValueJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EnumValueJpaRepositoryTest {

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
    EnumValueEntity expected = buildEnumValueEntity();
    givenExistingEnumValue(expected);

    EnumValueEntity enumValueEntity = enumValueJpaRepository
        .findByFileAndFieldAndCode("stops", "location_type", "0");

    assertThat(enumValueEntity).isEqualTo(expected);
  }

  private void givenExistingEnumValue(EnumValueEntity enumValueEntity) {
    entityManager.persist(enumValueEntity);
  }

}
