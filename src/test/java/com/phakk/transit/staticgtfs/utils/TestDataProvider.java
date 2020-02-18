package com.phakk.transit.staticgtfs.utils;

import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.core.calendar.Calendar;
import com.phakk.transit.staticgtfs.core.constants.BikesAllowed;
import com.phakk.transit.staticgtfs.core.constants.Direction;
import com.phakk.transit.staticgtfs.core.constants.DropOffType;
import com.phakk.transit.staticgtfs.core.constants.PickupType;
import com.phakk.transit.staticgtfs.core.constants.RouteType;
import com.phakk.transit.staticgtfs.core.constants.StopType;
import com.phakk.transit.staticgtfs.core.constants.Timepoint;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibility;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.core.trip.StopTime;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;

import java.time.LocalTime;

public class TestDataProvider {

    public static Agency buildAgency(){
        return Agency.builder()
                .id("agency1")
                .name("Test Agency")
                .url("test.com/agency")
                .timezone("Asia/Singapore")
                .lang("en")
                .phone("12345-677974")
                .fareUrl("test.com/fares")
                .email("test@email.com")
                .build();
    }

    public static AgencyEntity buildAgencyEntity(){
        AgencyEntity agencyEntity = new AgencyEntity();
        agencyEntity.setAgencyId("1");
        agencyEntity.setName("name");
        agencyEntity.setUrl("http://gtfs.com");
        agencyEntity.setTimezone("Asia/Singapore");
        agencyEntity.setEmail("email@test.com");
        agencyEntity.setFareUrl("http://gtfs.com/fares");
        agencyEntity.setLang("en");
        agencyEntity.setPhone("8888");
        return agencyEntity;
    }

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

    public static RouteEntity buildRouteEntity(){
        RouteEntity routeEntity = new RouteEntity();
        routeEntity.setRouteId("1");
        routeEntity.setAgency("agency");
        routeEntity.setShortName("short");
        routeEntity.setLongName("long");
        routeEntity.setDesc("desc");
        routeEntity.setType("700");
        routeEntity.setUrl("test.com");
        routeEntity.setColor("blue");
        routeEntity.setTextColor("white");
        routeEntity.setSortOrder(1);

        return routeEntity;
    }

    public static Stop buildStop(){
        return Stop.builder()
                .id("1")
                .code("TEST")
                .name("Test Station")
                .desc("Test Station")
                .lat(15.5737673)
                .lon(122.0481448)
                .zoneId("1")
                .url("test.com/stops/TEST")
                .type(StopType.STOP_1_STATION)
                .parentStation(null)
                .timezone("Asia/Singapore")
                .wheelchairBoarding(WheelchairAccessibility.WA_1_ACCESSIBLE)
                .levelId(null)
                .platformCode(null)
                .build();
    }

    public static StopEntity buildStopEntity(){
        StopEntity stop1 = new StopEntity();
        stop1.setStopId("1");
        stop1.setStopCode("TEST");
        stop1.setName("Test Station");
        stop1.setDesc("Test Station");
        stop1.setLat(15.5737673);
        stop1.setLon(122.0481448);
        stop1.setZoneId("1");
        stop1.setUrl("test.com/stops/TEST");
        stop1.setStopType("1");
        stop1.setParentStation("0");
        stop1.setTimezone("Asia/Singapore");
        stop1.setWheelchairBoarding("1");
        stop1.setLevelId("0");
        stop1.setPlatformCode("0");
        return stop1;
    }

    public static Trip buildTrip(){
        return Trip.builder()
                .routeId("1")
                .serviceId("1")
                .tripId("t1")
                .headsign("headsign")
                .shortName("shortName")
                .directionId(Direction.INBOUND)
                .blockId("blockId")
                .shapeId("shapeId")
                .wheelchairAccessible(WheelchairAccessibility.WA_1_ACCESSIBLE)
                .bikesAllowed(BikesAllowed.BIKES_ALLOWED_1)
                .build();
    }

    public static TripEntity buildTripEntity(){
        TripEntity tripEntity = new TripEntity();
        tripEntity.setRouteId("1");
        tripEntity.setServiceId("1");
        tripEntity.setTripId("1");
        tripEntity.setHeadsign("headsign");
        tripEntity.setShortName("shortname");
        tripEntity.setDirectionId("1");
        tripEntity.setBlockId("1");
        tripEntity.setShapeId("1");
        tripEntity.setWheelchairAccessible("1");
        tripEntity.setBikesAllowed("1");

        return tripEntity;
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
