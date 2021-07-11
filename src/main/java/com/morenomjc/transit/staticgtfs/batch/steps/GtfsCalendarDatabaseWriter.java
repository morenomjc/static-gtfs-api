package com.morenomjc.transit.staticgtfs.batch.steps;


import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarEntityMapper;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepository;
import com.morenomjc.transit.staticgtfs.batch.model.GtfsCalendar;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;


@Slf4j
@AllArgsConstructor
public class GtfsCalendarDatabaseWriter implements ItemWriter<GtfsCalendar> {

    private CalendarRepository repository;
    private CalendarEntityMapper mapper;

    @Override
    public void write(List<? extends GtfsCalendar> items) throws Exception {
        log.info("[GtfsCalendarDatabaseWriter].write={}", items.size());
        items.forEach(item -> repository.save(mapper.convert(item)));
    }

}
