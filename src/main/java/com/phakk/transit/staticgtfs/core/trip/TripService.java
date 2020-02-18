package com.phakk.transit.staticgtfs.core.trip;

import java.util.List;

public interface TripService {
    Trip getTrip(String tripId);
    List<StopTime> getStops(String tripId);
}
