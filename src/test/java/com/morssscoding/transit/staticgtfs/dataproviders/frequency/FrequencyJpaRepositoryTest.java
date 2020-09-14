package com.morssscoding.transit.staticgtfs.dataproviders.frequency;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.FrequencyEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.FrequencyJpaRepository;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class FrequencyJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FrequencyJpaRepository frequencyJpaRepository;

    @After
    public void cleanup(){
        entityManager.clear();
    }

    @Test
    public void testFindById(){
        givenExistingFrequencyEntity();
        Optional<FrequencyEntity> frequencyEntity = frequencyJpaRepository.findByTripId("1");

        assertThat(frequencyEntity.isPresent()).isTrue();
    }

    @Test
    public void testFindByIdEmpty(){
        Optional<FrequencyEntity> frequencyEntity = frequencyJpaRepository.findByTripId("-1");

        assertThat(frequencyEntity.isPresent()).isFalse();
    }

    private void givenExistingFrequencyEntity(){
        entityManager.persist(TestDataProvider.buildFrequencyEntity());
    }
}
