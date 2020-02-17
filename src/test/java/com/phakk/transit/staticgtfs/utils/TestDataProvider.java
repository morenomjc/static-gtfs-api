package com.phakk.transit.staticgtfs.utils;

import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.core.calendar.Calendar;
import com.phakk.transit.staticgtfs.core.constants.BikesAllowedEnum;
import com.phakk.transit.staticgtfs.core.constants.RouteTypeEnum;
import com.phakk.transit.staticgtfs.core.constants.StopTypeEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibilityEnum;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;

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
                RouteTypeEnum.ROUTE_700,
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
                .type(StopTypeEnum.STOP_1)
                .parentStation(null)
                .timezone("Asia/Singapore")
                .wheelchairBoarding(WheelchairAccessibilityEnum.WA_1)
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
}
