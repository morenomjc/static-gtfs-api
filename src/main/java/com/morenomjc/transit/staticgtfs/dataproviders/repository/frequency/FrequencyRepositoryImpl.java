package com.morenomjc.transit.staticgtfs.dataproviders.repository.frequency;

import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.core.frequency.Frequency;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.FrequencyEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.FrequencyJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@AllArgsConstructor
public class FrequencyRepositoryImpl implements FrequencyRepository {

    private FrequencyJpaRepository repository;
    private FrequencyEntityMapper mapper;
    private EnumValueRepository enumValueRepository;

    @Override
    @Cacheable(value = "frequencies")
    public Frequency getFrequency(String tripId) {
        Optional<FrequencyEntity> frequencyEntity = repository.findByTripId(tripId);
        if (!frequencyEntity.isPresent()){
            throw new DataNotFoundException("Frequency not found.");
        }
        Frequency frequency = mapper.fromEntity(frequencyEntity.get());
        EnumValue exactTimes = enumValueRepository.findEnumValue(Frequency.TYPE, Frequency.Fields.EXACT_TIMES.getValue(), String.valueOf(frequencyEntity.get().getExactTimes()));
        frequency.setExactTimes(exactTimes);
        return frequency;
    }

    @Override
    public void save(Frequency data) {
        FrequencyEntity frequencyEntity = mapper.toEntity(data);
        repository.save(frequencyEntity);
    }
}
