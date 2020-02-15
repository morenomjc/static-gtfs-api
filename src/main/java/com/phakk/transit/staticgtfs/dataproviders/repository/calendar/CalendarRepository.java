package com.phakk.transit.staticgtfs.dataproviders.repository.calendar;

import com.phakk.transit.staticgtfs.core.calendar.Calendar;

public interface CalendarRepository {
    Calendar getCalendar(String serviceId);
}
