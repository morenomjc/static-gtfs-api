package com.phakk.transit.staticgtfs.core.trip;

import com.phakk.transit.staticgtfs.dataproviders.repository.trip.TripRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService{

    private TripRepository tripRepository;

    @Override
    public Trip getTrip(String id) {
        Trip trip = tripRepository.getTrip(id);
        log.info("Found trip with id: [{}]", trip.getTripId());
        return trip;
    }
}
