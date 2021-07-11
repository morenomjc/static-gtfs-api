package com.morenomjc.transit.staticgtfs.core.calendar;

import com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService{

    private final CalendarRepository calendarRepository;

    @Override
    public Calendar getCalendar(String serviceId) {
        Calendar calendar = calendarRepository.getCalendar(serviceId);
        log.info("Found calendar with id: [{}]", calendar.getServiceId());
        return calendar;
    }
}
