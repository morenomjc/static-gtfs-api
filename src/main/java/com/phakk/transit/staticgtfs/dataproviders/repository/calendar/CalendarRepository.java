package com.phakk.transit.staticgtfs.dataproviders.repository.calendar;

import com.phakk.transit.staticgtfs.core.calendar.Calendar;
import com.phakk.transit.staticgtfs.dataproviders.repository.Repository;

public interface CalendarRepository extends Repository<Calendar> {
    Calendar getCalendar(String serviceId);
}
