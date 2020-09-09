package com.morssscoding.transit.staticgtfs.core.frequency;


import com.morssscoding.transit.staticgtfs.dataproviders.repository.frequency.FrequencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FrequencyServiceImpl implements FrequencyService {

    private FrequencyRepository frequencyRepository;

    @Override
    public Frequency getFrequency(String tripId) {
        return frequencyRepository.getFrequency(tripId);
    }
}
