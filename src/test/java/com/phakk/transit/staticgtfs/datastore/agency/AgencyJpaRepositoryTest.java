package com.phakk.transit.staticgtfs.datastore.agency;

import com.phakk.transit.staticgtfs.datastore.entity.jpa.AgencyEntity;
import com.phakk.transit.staticgtfs.datastore.repository.jpa.AgencyJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

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

    private void givenExistingAgency(AgencyEntity agencyEntity){
        entityManager.persist(agencyEntity);
    }

    private AgencyEntity buildAgencyEntity(){
        AgencyEntity agencyEntity = new AgencyEntity();
        agencyEntity.setAgencyId("1");
        agencyEntity.setName("name");
        agencyEntity.setUrl("http://gtfs.com");
        agencyEntity.setTimezone("Asia/Manila");
        agencyEntity.setEmail("email@test.com");
        agencyEntity.setFareUrl("http://gtfs.com/fares");
        agencyEntity.setLang("en");
        agencyEntity.setPhone("8888");
        return agencyEntity;
    }
}
