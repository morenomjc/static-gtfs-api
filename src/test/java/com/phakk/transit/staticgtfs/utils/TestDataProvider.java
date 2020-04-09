package com.phakk.transit.staticgtfs.utils;

import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.core.calendar.Calendar;
import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import com.phakk.transit.staticgtfs.core.route.Route;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.core.trip.StopTime;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.EnumValueEntity;
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
                buildEnumValueRouteType(),
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
                .type(buildEnumValueStopType())
                .parentStation(null)
                .timezone("Asia/Singapore")
                .wheelchairBoarding(buildEnumValueWheelchairBoarding())
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
        stop1.setWheelchairBoarding("0");
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
                .directionId(buildEnumValueDirectionId())
                .blockId("blockId")
                .shapeId("shapeId")
                .wheelchairAccessible(buildEnumValueWheelchairAccessible())
                .bikesAllowed(buildEnumValueBikesAllowed())
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
        stopTime.setPickupType(buildEnumValuePickupType());
        stopTime.setDropOffType(buildEnumValueDropOffType());
        stopTime.setDistanceTraveled(1.5);
        stopTime.setTimepoint(buildEnumValueTimepoint());
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
        stopTimeEntity.setPickupType("0");
        stopTimeEntity.setDropOffType("1");
        stopTimeEntity.setDistanceTraveled(1.5);
        stopTimeEntity.setTimepoint("2");
        return stopTimeEntity;
    }

    public static EnumValueEntity buildEnumValueEntity(){
        EnumValueEntity enumValueEntity = new EnumValueEntity();
        enumValueEntity.setFile("stops");
        enumValueEntity.setField("location_type");
        enumValueEntity.setCode("0");
        enumValueEntity.setName("Station");
        enumValueEntity.setDescription("A station");
        return enumValueEntity;
    }

    public static EnumValue buildEnumValueRouteType(){
        EnumValue enumValue = new EnumValue();
        enumValue.setFile("routes");
        enumValue.setField("route_type");
        enumValue.setCode("700");
        enumValue.setName("Bus Service");
        enumValue.setDescription("A bus service");
        return enumValue;
    }

    public static EnumValue buildEnumValueStopType(){
        EnumValue enumValue = new EnumValue();
        enumValue.setFile("stops");
        enumValue.setField("location_type");
        enumValue.setCode("1");
        enumValue.setName("Station");
        enumValue.setDescription("A station");
        return enumValue;
    }

    public static EnumValue buildEnumValueWheelchairBoarding(){
        EnumValue enumValue = new EnumValue();
        enumValue.setFile("stops");
        enumValue.setField("wheelchair_boarding");
        enumValue.setCode("1");
        enumValue.setName("Possible But Not Guaranteed");
        enumValue.setDescription("Possible But Not Guaranteed");
        return enumValue;
    }

    public static EnumValue buildEnumValueDirectionId(){
        EnumValue enumValue = new EnumValue();
        enumValue.setFile("trips");
        enumValue.setField("direction_id");
        enumValue.setCode("0");
        enumValue.setName("Inbound");
        enumValue.setDescription("Inbound");
        return enumValue;
    }

    public static EnumValue buildEnumValueWheelchairAccessible(){
        EnumValue enumValue = new EnumValue();
        enumValue.setFile("trips");
        enumValue.setField("wheelchair_accessible");
        enumValue.setCode("1");
        enumValue.setName("Possible For Only One");
        enumValue.setDescription("Possible For Only One");
        return enumValue;
    }

    public static EnumValue buildEnumValueBikesAllowed(){
        EnumValue enumValue = new EnumValue();
        enumValue.setFile("trips");
        enumValue.setField("bikes_allowed");
        enumValue.setCode("2");
        enumValue.setName("Possible For Only One");
        enumValue.setDescription("Possible For Only One");
        return enumValue;
    }

    public static EnumValue buildEnumValuePickupType(){
        EnumValue enumValue = new EnumValue();
        enumValue.setFile("stop_times");
        enumValue.setField("pickup_type");
        enumValue.setCode("0");
        enumValue.setName("Regular");
        enumValue.setDescription("Regular");
        return enumValue;
    }

    public static EnumValue buildEnumValueDropOffType(){
        EnumValue enumValue = new EnumValue();
        enumValue.setFile("stop_times");
        enumValue.setField("drop_off_type");
        enumValue.setCode("1");
        enumValue.setName("None");
        enumValue.setDescription("None");
        return enumValue;
    }

    public static EnumValue buildEnumValueTimepoint(){
        EnumValue enumValue = new EnumValue();
        enumValue.setFile("stop_times");
        enumValue.setField("timepoint");
        enumValue.setCode("2");
        enumValue.setName("Exact");
        enumValue.setDescription("Exact");
        return enumValue;
    }
}
