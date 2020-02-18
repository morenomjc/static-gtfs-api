package com.phakk.transit.staticgtfs.core.trip;

import com.phakk.transit.staticgtfs.dataproviders.repository.trip.TripRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService{

    private TripRepository tripRepository;

    @Override
    public Trip getTrip(String tripId) {
        Trip trip = tripRepository.getTrip(tripId);
        log.info("Found trip with id: [{}]", trip.getTripId());
        return trip;
    }

    @Override
    public List<StopTime> getStops(String tripId) {
        List<StopTime> stops = tripRepository.getStops(tripId);
        log.info("Found trip with id: [{}] has [{}] stops", tripId, stops.size());
        return stops;
    }
}
