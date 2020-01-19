package com.phakk.transit.staticgtfs.core.agency;

import com.phakk.transit.staticgtfs.datastore.repository.agency.AgencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
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
    public void testGetAgencyBydId(){
        Agency expected = buildAgency();
        givenAnAgency(expected);

        Agency result = agencyService.getAgency("1");

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGetAgencies(){
        givenAgencies();

        List<Agency> result = agencyService.getAgencies();

        assertThat(result).hasSize(1);
    }

    private void givenAnAgency(Agency agency){
        when(agencyRepository.getAgency(anyString())).thenReturn(agency);
    }

    private void givenAgencies(){
        when(agencyRepository.getAgencies()).thenReturn(Collections.singletonList(buildAgency()));
    }

    private Agency buildAgency(){
        return Agency.builder()
                .id(UUID.randomUUID().toString())
                .name("agency")
                .url("http://gtfs.com")
                .timezone("Asia/Singapore")
                .lang("en")
                .phone("8888")
                .fareUrl("http://gtfs.com/fares")
                .email("support@gtfs.com")
                .build();
    }
}
