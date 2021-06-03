package com.morssscoding.transit.staticgtfs.dataproviders.enumvalue;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildEnumValueEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.morssscoding.transit.staticgtfs.core.constants.EnumValue;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.EnumValueJpaRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(EnumValueRepositoryTest.EnumValueTestConfiguration.class)
class EnumValueRepositoryTest {

  private EnumValueRepository enumValueRepository;

  @Mock
  private EnumValueJpaRepository enumValueJpaRepository;

  @Autowired
  private EnumValueEntityMapper enumValueEntityMapper;

  @BeforeAll
  void setup() {
    enumValueRepository = new EnumValueRepositoryImpl(enumValueJpaRepository,
        enumValueEntityMapper);
  }

  @TestConfiguration
  static class EnumValueTestConfiguration {

    @Bean
    EnumValueEntityMapper enumValueEntityMapper() {
      return Mappers.getMapper(EnumValueEntityMapper.class);
    }
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
        .thenReturn(buildEnumValueEntity());
  }

}
