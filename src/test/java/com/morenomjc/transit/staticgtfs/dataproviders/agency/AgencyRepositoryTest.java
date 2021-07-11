package com.morenomjc.transit.staticgtfs.dataproviders.agency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.AgencyJpaRepository;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import com.morenomjc.transit.staticgtfs.core.agency.Agency;
import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.agency.AgencyEntityMapper;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.agency.AgencyRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.agency.AgencyRepositoryJpaImpl;
import java.util.Collections;
import java.util.List;
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
@Import(AgencyRepositoryTest.AgencyTestConfiguration.class)
class AgencyRepositoryTest {

  private AgencyRepository agencyRepository;

  @Mock
  private AgencyJpaRepository agencyJpaRepository;

  @Autowired
  private AgencyEntityMapper agencyEntityMapper;

  @BeforeAll
  void setup() {
    agencyRepository = new AgencyRepositoryJpaImpl(agencyJpaRepository, agencyEntityMapper);
  }

  @TestConfiguration
  static class AgencyTestConfiguration {

    @Bean
    AgencyEntityMapper agencyEntityMapper() {
      return Mappers.getMapper(AgencyEntityMapper.class);
    }
  }

  @Test
  void testAgencyIsConvertedProperly() {
    whenAnAgencyExists();

    Agency agency = agencyRepository.getAgency("1");

    assertThat(agency).isNotNull();
    assertThat(agency.getId()).isEqualTo("1");
    assertThat(agency.getName()).isEqualTo("name");
    assertThat(agency.getUrl()).isEqualTo("http://gtfs.com");
    assertThat(agency.getTimezone()).isEqualTo("Asia/Singapore");
    assertThat(agency.getEmail()).isEqualTo("email@test.com");
    assertThat(agency.getFareUrl()).isEqualTo("http://gtfs.com/fares");
    assertThat(agency.getLang()).isEqualTo("en");
    assertThat(agency.getPhone()).isEqualTo("8888");
  }

  @Test
  void testListOfAgenciesIsReturned() {
    whenAgenciesExists();

    List<Agency> agencies = agencyRepository.getAgencies();

    assertThat(agencies).hasSize(1);
  }

  @Test
  void testWhenAgencyNotFound() {
    whenAgencyNotFound();

    Exception expectedException = assertThrows(DataNotFoundException.class, () -> {
      agencyRepository.getAgency("1");
    });

    assertThat(expectedException).isNotNull();
    assertThat(expectedException.getMessage()).isEqualTo("Agency not found.");
  }

  @Test
  void testIfMappedProperly() {
    Agency agency = TestDataProvider.buildAgency();
    AgencyEntity agencyEntity = agencyEntityMapper.toEntity(agency);

    assertThat(agencyEntity.getAgencyId()).isEqualTo(agency.getId());
  }

  private void whenAgencyNotFound() {
    when(agencyJpaRepository.findByAgencyId(anyString())).thenReturn(null);
  }

  private void whenAnAgencyExists() {
    when(agencyJpaRepository.findByAgencyId(anyString())).thenReturn(TestDataProvider.buildAgencyEntity());
  }

  private void whenAgenciesExists() {
    when(agencyJpaRepository.findAll()).thenReturn(Collections.singletonList(TestDataProvider.buildAgencyEntity()));
  }

}
