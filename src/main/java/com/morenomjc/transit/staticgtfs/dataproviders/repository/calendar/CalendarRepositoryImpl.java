package com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar;

import com.morenomjc.transit.staticgtfs.core.calendar.Calendar;
import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.CalendarJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class CalendarRepositoryImpl implements CalendarRepository{

    private final CalendarJpaRepository calendarJpaRepository;
    private final CalendarEntityMapper calendarEntityMapper;

    @Override
    @Cacheable(value = "calendars")
    public Calendar getCalendar(String serviceId) {
        CalendarEntity calendarEntity = calendarJpaRepository.findByServiceId(serviceId);
        if (Objects.isNull(calendarEntity)){
            throw new DataNotFoundException("Calendar not found.");
        }
        return calendarEntityMapper.fromEntity(calendarEntity);
    }

    @Override
    public void save(Calendar data) {
        calendarJpaRepository.save(calendarEntityMapper.toEntity(data));
    }
}
