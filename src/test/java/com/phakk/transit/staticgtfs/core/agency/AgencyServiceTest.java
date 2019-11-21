package com.phakk.transit.staticgtfs.core.agency;

import com.phakk.transit.staticgtfs.datastore.repository.agency.AgencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AgencyServiceTest {

    private AgencyService agencyService;

    @Mock
    private AgencyRepository agencyRepository;

    @Before
    public void setup(){
        agencyService = new AgencyServiceImpl(agencyRepository);
    }

    @Test
    public void getAgency_whenExists_returnDetails(){
        givenAgencyDetails();

        Agency agency = agencyService.getAgency("1");

        assertThat(agency).isNotNull();
    }

    private void givenAgencyDetails(){
        when(agencyRepository.getAgency(anyString())).thenReturn(
                Agency.builder()
                        .id(UUID.randomUUID().toString())
                        .name("agency")
                        .url("http://gtfs.com")
                        .timezone("Asia/Manila")
                        .lang("en")
                        .phone("8888")
                        .fareUrl("http://gtfs.com/fares")
                        .email("support@gtfs.com")
                        .build()
        );
    }
}
