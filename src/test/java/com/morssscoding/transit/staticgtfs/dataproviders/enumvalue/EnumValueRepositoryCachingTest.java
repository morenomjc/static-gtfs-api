package com.morssscoding.transit.staticgtfs.dataproviders.enumvalue;

import com.morssscoding.transit.staticgtfs.configuration.CacheConfiguration;
import com.morssscoding.transit.staticgtfs.configuration.MapperConfiguration;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.EnumValueJpaRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static com.morssscoding.transit.staticgtfs.utils.TestDataProvider.buildEnumValueEntity;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@RunWith(SpringRunner.class)
public class EnumValueRepositoryCachingTest {

    @Autowired
    private EnumValueRepository enumValueRepository;

    @Autowired
    private EnumValueJpaRepository enumValueJpaRepository;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() {
        cacheManager.getCache("enumvalues").clear();
    }

    @Configuration
    @EnableCaching
    static class TestConfig {

        @Bean
        CacheManager cacheManager() {
            return new ConcurrentMapCacheManager("enumvalues");
        }

        @Bean
        EnumValueJpaRepository enumValueJpaRepository() {
            return Mockito.mock(EnumValueJpaRepository.class);
        }

        @Bean
        EnumValueEntityMapper enumValueEntityMapper(){
            return Mappers.getMapper(EnumValueEntityMapper.class);
        }

        @Bean
        EnumValueRepository enumValueRepository(){
            return new EnumValueRepositoryImpl(enumValueJpaRepository(), enumValueEntityMapper());
        }
    }

    @Test
    public void testFindEnumValue_ShouldUseCache_WhenCalledTwice(){
        when(enumValueJpaRepository.findByFileAndFieldAndCode(anyString(), anyString(), anyString()))
                .thenReturn(buildEnumValueEntity());

        enumValueRepository.findEnumValue("stops", "location_type", "0");
        enumValueRepository.findEnumValue("stops", "location_type", "0");

        verify(enumValueJpaRepository, times(1)).findByFileAndFieldAndCode(anyString(), anyString(), anyString());
    }

}
