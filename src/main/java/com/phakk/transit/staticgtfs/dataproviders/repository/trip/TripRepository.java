package com.phakk.transit.staticgtfs.dataproviders.repository.trip;

import com.phakk.transit.staticgtfs.core.trip.StopTime;
import com.phakk.transit.staticgtfs.core.trip.Trip;

import java.util.List;

public interface TripRepository {
    Trip getTrip(String id);
    List<StopTime> getStops(String tripId);
}
