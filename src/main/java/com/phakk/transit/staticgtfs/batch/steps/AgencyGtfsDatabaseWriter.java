package com.phakk.transit.staticgtfs.batch.steps;


import com.phakk.transit.staticgtfs.batch.model.Agency;
import com.phakk.transit.staticgtfs.dataproviders.repository.agency.AgencyEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.agency.AgencyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;


@Slf4j
@AllArgsConstructor
public class AgencyGtfsDatabaseWriter implements ItemWriter<Agency> {

    private AgencyRepository repository;
    private AgencyEntityMapper agencyEntityMapper;

    @Override
    public void write(List<? extends Agency> items) throws Exception {
        log.info("[AgencyGtfsDatabaseWriter].write={}", items.size());
        items.forEach(agency -> {
            repository.save(agencyEntityMapper.convert(agency));
        });
    }

}
