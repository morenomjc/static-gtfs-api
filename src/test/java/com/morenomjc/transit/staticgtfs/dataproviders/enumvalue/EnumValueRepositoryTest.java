package com.morenomjc.transit.staticgtfs.dataproviders.enumvalue;

import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.EnumValueJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapper;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapperImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepositoryImpl;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(EnumValueEntityMapperImpl.class)
class EnumValueRepositoryTest {

  private EnumValueRepository enumValueRepository;

  @Mock
  private EnumValueJpaRepository enumValueJpaRepository;

  @Autowired
  private EnumValueEntityMapper enumValueEntityMapper;

  @BeforeAll
  void setup() {
    enumValueRepository = new EnumValueRepositoryImpl(enumValueJpaRepository, enumValueEntityMapper);
  }

  @Test
  void testEnumValueIsConvertedProperly() {
    whenAnEnumValueExists();

    EnumValue enumValue = enumValueRepository.findEnumValue("stops", "location_type", "0");

    assertThat(enumValue).isNotNull();
    assertThat(enumValue.getFile()).isEqualTo("stops");
    assertThat(enumValue.getField()).isEqualTo("location_type");
    assertThat(enumValue.getCode()).isEqualTo("0");
    assertThat(enumValue.getName()).isEqualTo("Station");
    assertThat(enumValue.getDescription()).isEqualTo("A station");
  }

  private void whenAnEnumValueExists() {
    when(enumValueJpaRepository.findByFileAndFieldAndCode(anyString(), anyString(), anyString()))
        .thenReturn(TestDataProvider.buildEnumValueEntity());
  }

}
