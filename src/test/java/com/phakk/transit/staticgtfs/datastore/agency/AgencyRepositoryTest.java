package com.phakk.transit.staticgtfs.datastore.agency;

import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.core.agency.AgencyNotFoundException;
import com.phakk.transit.staticgtfs.datastore.entity.jpa.AgencyEntity;
import com.phakk.transit.staticgtfs.datastore.repository.agency.AgencyRepository;
import com.phakk.transit.staticgtfs.datastore.repository.agency.AgencyRepositoryImpl;
import com.phakk.transit.staticgtfs.datastore.repository.jpa.AgencyJpaRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

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
        agencyRepository = new AgencyRepositoryImpl(agencyJpaRepository);
    }

    @Test
    public void testAgencyIsConvertedProperly(){
        givenAgencyDetails();

        Agency agency = agencyRepository.getAgency("1");

        assertThat(agency).isNotNull();
        assertThat(agency.getId()).isEqualTo("1");
        assertThat(agency.getName()).isEqualTo("name");
    }

    @Test
    public void testWhenAgencyNotFound(){
        expectedException.expect(AgencyNotFoundException.class);
        expectedException.expectMessage(equalTo("Agency not found."));

        whenAgencyNotFound();

        agencyRepository.getAgency("1");
    }

    private void whenAgencyNotFound(){
        when(agencyJpaRepository.findByAgencyId(anyString())).thenReturn(null);
    }

    private void givenAgencyDetails(){
        AgencyEntity agencyEntity = new AgencyEntity();
        agencyEntity.setAgencyId("1");
        agencyEntity.setName("name");
        agencyEntity.setUrl("http://gtfs.com");
        agencyEntity.setTimezone("Asia/Manila");
        agencyEntity.setEmail("email@test.com");
        agencyEntity.setFareUrl("http://gtfs.com/fares");
        agencyEntity.setLang("en");
        agencyEntity.setPhone("8888");

        when(agencyJpaRepository.findByAgencyId(anyString())).thenReturn(agencyEntity);
    }
}
