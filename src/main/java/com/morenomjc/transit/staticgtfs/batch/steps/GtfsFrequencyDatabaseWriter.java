package com.morenomjc.transit.staticgtfs.batch.steps;


import com.morenomjc.transit.staticgtfs.batch.model.GtfsFrequency;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.frequency.FrequencyRepository;
import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import com.morenomjc.transit.staticgtfs.core.frequency.Frequency;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.frequency.FrequencyEntityMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class GtfsFrequencyDatabaseWriter implements ItemWriter<GtfsFrequency> {

    private FrequencyRepository repository;
    private FrequencyEntityMapper mapper;
    private EnumValueRepository enumValueRepository;

    @Override
    public void write(List<? extends GtfsFrequency> items) throws Exception {
        log.info("{}", items.size());
        items.forEach(item -> {
            Frequency frequency = mapper.convert(item);
            EnumValue exactTimes = enumValueRepository.findEnumValue(Frequency.TYPE, Frequency.Fields.EXACT_TIMES.getValue(), String.valueOf(item.getExact_times()));
            frequency.setExactTimes(exactTimes);
            repository.save(frequency);
        });
    }

}
