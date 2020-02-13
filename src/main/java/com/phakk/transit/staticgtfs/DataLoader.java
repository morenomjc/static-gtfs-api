package com.phakk.transit.staticgtfs;

import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.RouteEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.AgencyJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.RouteJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.StopJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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

    @Override
    public void run(String... args) throws Exception {
        loadAgency();
        loadStop();
        loadRoute();
        loadTrip();
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
        stop1.setParentStation(null);
        stop1.setTimezone("Asia/Singapore");
        stop1.setWheelchairBoarding("1");
        stop1.setLevelId(null);
        stop1.setPlatformCode(null);

        StopEntity saved = stopJpaRepository.save(stop1);
        log.info("Stop saved with id [{}]", saved.getId());
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

        RouteEntity saved = routeJpaRepository.save(routeEntity);
        log.info("Route saved with id [{}]", saved.getId());
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

        TripEntity saved = tripJpaRepository.save(tripEntity);
        log.info("Trip saved with id [{}]", tripEntity.getId());
    }
}
