package com.morssscoding.transit.staticgtfs.dataproviders.agency;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.AgencyJpaRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildAgencyEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AgencyJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AgencyJpaRepository agencyJpaRepository;

    @After
    public void cleanup(){
        entityManager.clear();
    }

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
}
