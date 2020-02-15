package com.phakk.transit.staticgtfs.utils;

import com.phakk.transit.staticgtfs.core.calendar.Calendar;
import com.phakk.transit.staticgtfs.core.constants.BikesAllowedEnum;
import com.phakk.transit.staticgtfs.core.constants.RouteTypeEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibilityEnum;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;

public class TestDataProvider {

    public static Route buildRoute(){
        return new Route(
                "1",
                "agency",
                "short",
                "long",
                "desc",
                RouteTypeEnum.ROUTE_700,
                "test.com",
                "blue",
                "white",
                1
        );
    }

    public static Trip buildTrip(){
        return Trip.builder()
                .routeId("r1")
                .serviceId("s1")
                .tripId("t1")
                .headsign("headsign")
                .shortName("shortName")
                .directionId("directionId")
                .blockId("blockId")
                .shapeId("shapeId")
                .wheelchairAccessible(WheelchairAccessibilityEnum.WA_1)
                .bikesAllowed(BikesAllowedEnum.BIKES_ALLOWED_1)
                .build();
    }

    public static Calendar buildCalendar() {
        return Calendar.builder()
                .serviceId("1")
                .monday(true)
                .tuesday(true)
                .wednesday(true)
                .thursday(true)
                .friday(true)
                .saturday(false)
                .sunday(false)
                .build();
    }

    public static CalendarEntity buildCalendarEntity(){
        CalendarEntity calendarEntity = new CalendarEntity();
        calendarEntity.setServiceId("1");
        calendarEntity.setMonday(1);
        calendarEntity.setTuesday(1);
        calendarEntity.setWednesday(1);
        calendarEntity.setThursday(1);
        calendarEntity.setFriday(1);
        calendarEntity.setSaturday(0);
        calendarEntity.setSunday(0);
        return calendarEntity;
    }
}
