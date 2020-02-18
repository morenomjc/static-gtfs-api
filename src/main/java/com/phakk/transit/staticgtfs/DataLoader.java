package com.phakk.transit.staticgtfs;

import com.phakk.transit.staticgtfs.core.constants.DropOffType;
import com.phakk.transit.staticgtfs.core.constants.PickupType;
import com.phakk.transit.staticgtfs.core.constants.Timepoint;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.CalendarEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.AgencyJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.CalendarJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.RouteJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.StopJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.StopTimeJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Slf4j
@Component
@Profile("local")
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AgencyJpaRepository agencyRepository;
    @Autowired
    private StopJpaRepository stopJpaRepository;
    @Autowired
    private RouteJpaRepository routeJpaRepository;
    @Autowired
    private TripJpaRepository tripJpaRepository;
    @Autowired
    private CalendarJpaRepository calendarJpaRepository;
    @Autowired
    private StopTimeJpaRepository stopTimeJpaRepository;

    @Override
    public void run(String... args) throws Exception {
        loadAgency();
        loadStop();
        loadRoute();
        loadTrip();
        loadCalendar();
        loadStopTime();
    }

    private void loadAgency(){
        AgencyEntity agency1 = new AgencyEntity();
        agency1.setAgencyId("agency1");
        agency1.setEmail("test@email.com");
        agency1.setFareUrl("test.com/fares");
        agency1.setLang("en");
        agency1.setName("Test Agency");
        agency1.setPhone("12345-677974");
        agency1.setTimezone("Asia/Singapore");
        agency1.setUrl("test.com/agency");

        AgencyEntity saved = agencyRepository.save(agency1);
        log.info("Agency saved with id [{}]", saved.getId());

        AgencyEntity agency2 = new AgencyEntity();
        agency2.setAgencyId("agency2");

        AgencyEntity saved2 = agencyRepository.save(agency2);
        log.info("Agency saved with id [{}]", saved2.getId());
    }

    private void loadStop(){
        StopEntity stopEntity = new StopEntity();
        stopEntity.setStopId("1");
        stopEntity.setStopCode("TEST");
        stopEntity.setName("Test Station");
        stopEntity.setDesc("Test Station");
        stopEntity.setLat(15.5737673);
        stopEntity.setLon(122.0481448);
        stopEntity.setZoneId("1");
        stopEntity.setUrl("test.com/stops/TEST");
        stopEntity.setStopType("1");
        stopEntity.setParentStation(null);
        stopEntity.setTimezone("Asia/Singapore");
        stopEntity.setWheelchairBoarding("1");
        stopEntity.setLevelId(null);
        stopEntity.setPlatformCode(null);

        stopEntity = stopJpaRepository.save(stopEntity);
        log.info("Stop saved with id [{}]", stopEntity.getId());
    }

    private void loadRoute(){
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

        routeEntity = routeJpaRepository.save(routeEntity);
        log.info("Route saved with id [{}]", routeEntity.getId());
    }

    private void loadTrip(){
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

        tripEntity = tripJpaRepository.save(tripEntity);
        log.info("Trip saved with id [{}]", tripEntity.getId());
    }

    public void loadCalendar(){
        CalendarEntity calendarEntity = new CalendarEntity();
        calendarEntity.setServiceId("1");
        calendarEntity.setMonday(1);
        calendarEntity.setTuesday(1);
        calendarEntity.setWednesday(1);
        calendarEntity.setThursday(1);
        calendarEntity.setFriday(1);
        calendarEntity.setSaturday(0);
        calendarEntity.setSunday(0);

        calendarEntity = calendarJpaRepository.save(calendarEntity);
        log.info("Calendar saved with id [{}]", calendarEntity.getId());
    }

    public void loadStopTime(){
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

        stopTimeEntity = stopTimeJpaRepository.save(stopTimeEntity);
        log.info("StopTime saved with id [{}]", stopTimeEntity.getId());
    }
}
