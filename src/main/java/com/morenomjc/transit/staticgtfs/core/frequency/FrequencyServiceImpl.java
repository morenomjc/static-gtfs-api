package com.morenomjc.transit.staticgtfs.core.frequency;


import com.morenomjc.transit.staticgtfs.dataproviders.repository.frequency.FrequencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FrequencyServiceImpl implements FrequencyService {

    private final FrequencyRepository frequencyRepository;

    @Override
    public Frequency getFrequency(String tripId) {
        return frequencyRepository.getFrequency(tripId);
    }
}
