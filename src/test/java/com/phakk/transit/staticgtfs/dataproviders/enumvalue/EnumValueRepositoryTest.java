package com.phakk.transit.staticgtfs.dataproviders.enumvalue;

import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.EnumValueJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepositoryImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildEnumValueEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Import(EnumValueRepositoryTest.EnumValueTestConfiguration.class)
@RunWith(SpringRunner.class)
public class EnumValueRepositoryTest {

    private EnumValueRepository enumValueRepository;

    @Mock
    private EnumValueJpaRepository enumValueJpaRepository;

    @Autowired
    private EnumValueEntityMapper enumValueEntityMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        enumValueRepository = new EnumValueRepositoryImpl(enumValueJpaRepository, enumValueEntityMapper);
    }

    @TestConfiguration
    static class EnumValueTestConfiguration {
        @Bean
        public EnumValueEntityMapper enumValueEntityMapper(){
            return Mappers.getMapper(EnumValueEntityMapper.class);
        }
    }

    @Test
    public void testEnumValueIsConvertedProperly(){
        whenAnEnumValueExists();

        EnumValue enumValue = enumValueRepository.findEnumValue("stops", "location_type", "0");

        assertThat(enumValue).isNotNull();
        assertThat(enumValue.getFile()).isEqualTo("stops");
        assertThat(enumValue.getField()).isEqualTo("location_type");
        assertThat(enumValue.getCode()).isEqualTo("0");
        assertThat(enumValue.getName()).isEqualTo("Station");
        assertThat(enumValue.getDescription()).isEqualTo("A station");
    }

    @Test
    public void testWhenEnumNotFound(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Enum value [0, 0, 0] not found."));

        whenAnEnumValueNotFound();

        enumValueRepository.findEnumValue("0", "0", "0");
    }

    private void whenAnEnumValueNotFound(){
        when(enumValueJpaRepository.findByFileAndFieldAndCode(anyString(), anyString(), anyString()))
                .thenReturn(null);
    }

    private void whenAnEnumValueExists(){
        when(enumValueJpaRepository.findByFileAndFieldAndCode(anyString(), anyString(), anyString()))
                .thenReturn(buildEnumValueEntity());
    }

}
