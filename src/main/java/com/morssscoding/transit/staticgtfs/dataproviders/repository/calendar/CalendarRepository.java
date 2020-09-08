package com.morssscoding.transit.staticgtfs.dataproviders.repository.calendar;

import com.morssscoding.transit.staticgtfs.core.calendar.Calendar;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.Repository;

public interface CalendarRepository extends Repository<Calendar> {
    Calendar getCalendar(String serviceId);
}
