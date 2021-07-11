package com.morenomjc.transit.staticgtfs.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.morenomjc.transit.staticgtfs.core.agency.AgencyService;
import com.morenomjc.transit.staticgtfs.utils.TestDataProvider;
import com.morenomjc.transit.staticgtfs.core.agency.Agency;
import com.morenomjc.transit.staticgtfs.core.agency.AgencyServiceImpl;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.agency.AgencyRepository;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AgencyServiceTest {

  private AgencyService agencyService;

  @Mock
  private AgencyRepository agencyRepository;

  @BeforeAll
  void setup() {
    agencyService = new AgencyServiceImpl(agencyRepository);
  }

  @Test
  void testGetAgencyBydId() {
    Agency expected = TestDataProvider.buildAgency();
    givenAnAgency(expected);

    Agency result = agencyService.getAgency("1");

    assertThat(result).isEqualTo(expected);
  }

  @Test
  void testGetAgencies() {
    givenAgencies();

    List<Agency> result = agencyService.getAgencies();

    assertThat(result).hasSize(1);
  }

  private void givenAnAgency(Agency agency) {
    when(agencyRepository.getAgency(anyString())).thenReturn(agency);
  }

  private void givenAgencies() {
    when(agencyRepository.getAgencies())
        .thenReturn(Collections.singletonList(TestDataProvider.buildAgency()));
  }

}
