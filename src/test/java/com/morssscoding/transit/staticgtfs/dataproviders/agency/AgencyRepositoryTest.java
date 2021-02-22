package com.morssscoding.transit.staticgtfs.dataproviders.agency;

import com.morssscoding.transit.staticgtfs.core.agency.Agency;
import com.morssscoding.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.AgencyJpaRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.agency.AgencyEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.agency.AgencyRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.agency.AgencyRepositoryJpaImpl;
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

import java.util.Collections;
import java.util.List;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildAgency;
import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildAgencyEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Import(AgencyRepositoryTest.AgencyTestConfiguration.class)
@RunWith(SpringRunner.class)
public class AgencyRepositoryTest {

    private AgencyRepository agencyRepository;

    @Mock
    private AgencyJpaRepository agencyJpaRepository;

    @Autowired
    private AgencyEntityMapper agencyEntityMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        agencyRepository = new AgencyRepositoryJpaImpl(agencyJpaRepository, agencyEntityMapper);
    }

    @TestConfiguration
    static class AgencyTestConfiguration {
        @Bean
        public AgencyEntityMapper agencyEntityMapper(){
            return Mappers.getMapper(AgencyEntityMapper.class);
        }
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

    @Test
    public void testIfMappedProperly(){
        Agency agency = buildAgency();
        AgencyEntity agencyEntity = agencyEntityMapper.toEntity(agency);

        assertThat(agencyEntity.getAgencyId()).isEqualTo(agency.getId());
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

}
