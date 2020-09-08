package com.morssscoding.transit.staticgtfs.dataproviders.enumvalue;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.EnumValueEntity;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.EnumValueJpaRepository;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildEnumValueEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class EnumValueJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EnumValueJpaRepository enumValueJpaRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @After
    public void cleanup(){
        entityManager.clear();
    }

    @Test
    public void testFindByFileFieldAndCode(){
        EnumValueEntity expected = buildEnumValueEntity();
        givenExistingEnumValue(expected);

        EnumValueEntity enumValueEntity = enumValueJpaRepository.findByFileAndFieldAndCode("stops", "location_type", "0");

        assertThat(enumValueEntity).isEqualTo(expected);
    }

    private void givenExistingEnumValue(EnumValueEntity enumValueEntity){
        entityManager.persist(enumValueEntity);
    }

}
