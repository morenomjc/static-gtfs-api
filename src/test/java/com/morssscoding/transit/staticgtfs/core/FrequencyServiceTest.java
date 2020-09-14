package com.morssscoding.transit.staticgtfs.core;

import com.morssscoding.transit.staticgtfs.core.frequency.Frequency;
import com.morssscoding.transit.staticgtfs.core.frequency.FrequencyService;
import com.morssscoding.transit.staticgtfs.core.frequency.FrequencyServiceImpl;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.frequency.FrequencyRepository;
import com.morssscoding.transit.staticgtfs.utils.TestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FrequencyServiceTest {

    private FrequencyService frequencyService;

    @MockBean
    private FrequencyRepository frequencyRepository;

    @Before
    public void setup(){
        frequencyService = new FrequencyServiceImpl(frequencyRepository);
    }

    @Test
    public void testGetFrequency(){
        when(frequencyRepository.getFrequency(anyString())).thenReturn(TestDataProvider.buildFrequency());

        Frequency frequency = frequencyService.getFrequency("1");

        assertThat(frequency).isNotNull();
    }
}
