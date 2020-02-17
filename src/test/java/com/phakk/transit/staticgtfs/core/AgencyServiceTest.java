package com.phakk.transit.staticgtfs.core;

import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.core.agency.AgencyService;
import com.phakk.transit.staticgtfs.core.agency.AgencyServiceImpl;
import com.phakk.transit.staticgtfs.dataproviders.repository.agency.AgencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static com.phakk.transit.staticgtfs.utils.TestDataProvider.buildAgency;
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

}
