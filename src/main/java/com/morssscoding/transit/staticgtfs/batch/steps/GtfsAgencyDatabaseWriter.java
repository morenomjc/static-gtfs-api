package com.morssscoding.transit.staticgtfs.batch.steps;


import com.morssscoding.transit.staticgtfs.batch.model.GtfsAgency;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.agency.AgencyEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.agency.AgencyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;


@Slf4j
@AllArgsConstructor
public class GtfsAgencyDatabaseWriter implements ItemWriter<GtfsAgency> {

    private AgencyRepository repository;
    private AgencyEntityMapper mapper;

    @Override
    public void write(List<? extends GtfsAgency> items) throws Exception {
        log.info("[AgencyGtfsDatabaseWriter].write={}", items.size());
        items.forEach(agency -> {
            repository.save(mapper.convert(agency));
        });
    }

}
