package com.morenomjc.transit.staticgtfs.dataproviders.repository.calendar;

import com.morenomjc.transit.staticgtfs.core.calendar.Calendar;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.Repository;

public interface CalendarRepository extends Repository<Calendar> {
    Calendar getCalendar(String serviceId);
}
