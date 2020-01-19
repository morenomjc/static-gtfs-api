package com.phakk.transit.staticgtfs.datastore.agency;

import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.datastore.jpa.entity.AgencyEntity;
import com.phakk.transit.staticgtfs.datastore.repository.agency.AgencyRepository;
import com.phakk.transit.staticgtfs.datastore.repository.agency.AgencyRepositoryJpaImpl;
import com.phakk.transit.staticgtfs.datastore.jpa.repository.AgencyJpaRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AgencyRepositoryTest {

    private AgencyRepository agencyRepository;

    @Mock
    private AgencyJpaRepository agencyJpaRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        agencyRepository = new AgencyRepositoryJpaImpl(agencyJpaRepository);
    }

    @Test
    public void testAgencyIsConvertedProperly(){
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
    public void testListOfAgenciesIsReturned(){
        whenAgenciesExists();

        List<Agency> agencies = agencyRepository.getAgencies();

        assertThat(agencies).hasSize(1);
    }

    @Test
    public void testWhenAgencyNotFound(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage(equalTo("Agency not found."));

        whenAgencyNotFound();

        agencyRepository.getAgency("1");
    }

    private void whenAgencyNotFound(){
        when(agencyJpaRepository.findByAgencyId(anyString())).thenReturn(null);
    }

    private void whenAnAgencyExists(){
        when(agencyJpaRepository.findByAgencyId(anyString())).thenReturn(buildAgencyEntity());
    }

    private void whenAgenciesExists(){
        when(agencyJpaRepository.findAll()).thenReturn(Collections.singletonList(buildAgencyEntity()));
    }

    private AgencyEntity buildAgencyEntity(){
        AgencyEntity agencyEntity = new AgencyEntity();
        agencyEntity.setAgencyId("1");
        agencyEntity.setName("name");
        agencyEntity.setUrl("http://gtfs.com");
        agencyEntity.setTimezone("Asia/Singapore");
        agencyEntity.setEmail("email@test.com");
        agencyEntity.setFareUrl("http://gtfs.com/fares");
        agencyEntity.setLang("en");
        agencyEntity.setPhone("8888");

        return agencyEntity;
    }
}
