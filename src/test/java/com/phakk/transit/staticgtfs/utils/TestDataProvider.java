package com.phakk.transit.staticgtfs.utils;

import com.phakk.transit.staticgtfs.core.calendar.Calendar;
import com.phakk.transit.staticgtfs.core.constants.BikesAllowed;
import com.phakk.transit.staticgtfs.core.constants.DropOffType;
import com.phakk.transit.staticgtfs.core.constants.PickupType;
import com.phakk.transit.staticgtfs.core.constants.RouteType;
import com.phakk.transit.staticgtfs.core.constants.Timepoint;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibility;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.core.trip.StopTime;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;

import java.time.LocalTime;

public class TestDataProvider {

    public static Route buildRoute(){
        return new Route(
                "1",
                "agency",
                "short",
                "long",
                "desc",
                RouteType.ROUTE_700_BUS,
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
                .wheelchairAccessible(WheelchairAccessibility.WA_1_ACCESSIBLE)
                .bikesAllowed(BikesAllowed.BIKES_ALLOWED_1)
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

    public static StopTime buildStopTime(){
        StopTime stopTime = new StopTime();
        stopTime.setTripId("1");
        stopTime.setArrivalTime(LocalTime.of(8, 0, 0));
        stopTime.setDepartureTime(LocalTime.of(8, 30, 0));
        stopTime.setStopId("1");
        stopTime.setStopSequence(1);
        stopTime.setStopHeadsign("headsign");
        stopTime.setPickupType(PickupType.PT_0_REGULAR);
        stopTime.setDropOffType(DropOffType.DOT_0_REGULAR);
        stopTime.setDistanceTraveled(1.5);
        stopTime.setTimepoint(Timepoint.TP_0_APPROXIMATE);
        return stopTime;
    }

    public static StopTimeEntity buildStopTimeEntity(){
        StopTimeEntity stopTimeEntity = new StopTimeEntity();
        stopTimeEntity.setTripId("1");
        stopTimeEntity.setArrivalTime(LocalTime.of(8, 0, 0));
        stopTimeEntity.setDepartureTime(LocalTime.of(8, 30, 0));
        stopTimeEntity.setStopId("1");
        stopTimeEntity.setStopSequence(1);
        stopTimeEntity.setStopHeadsign("headsign");
        stopTimeEntity.setPickupType(PickupType.PT_0_REGULAR.getCode());
        stopTimeEntity.setDropOffType(DropOffType.DOT_0_REGULAR.getCode());
        stopTimeEntity.setDistanceTraveled(1.5);
        stopTimeEntity.setTimepoint(Timepoint.TP_0_APPROXIMATE.getCode());
        return stopTimeEntity;
    }
}
