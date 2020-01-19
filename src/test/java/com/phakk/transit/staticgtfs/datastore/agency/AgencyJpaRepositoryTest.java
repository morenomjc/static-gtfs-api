package com.phakk.transit.staticgtfs.datastore.agency;

import com.phakk.transit.staticgtfs.datastore.jpa.entity.AgencyEntity;
import com.phakk.transit.staticgtfs.datastore.jpa.repository.AgencyJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class AgencyJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AgencyJpaRepository agencyJpaRepository;

    @Test
    public void testFindById(){
        AgencyEntity expected = buildAgencyEntity();
        givenExistingAgency(expected);

        AgencyEntity agencyEntity = agencyJpaRepository.findByAgencyId("1");

        assertThat(agencyEntity).isEqualTo(expected);
    }

    @Test
    public void testFindAll(){
        AgencyEntity expected = buildAgencyEntity();
        givenExistingAgency(expected);

        List<AgencyEntity> agencyEntities = agencyJpaRepository.findAll();

        assertThat(agencyEntities).hasSize(1);
        assertThat(agencyEntities).contains(expected);
    }

    private void givenExistingAgency(AgencyEntity agencyEntity){
        entityManager.persist(agencyEntity);
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
