package com.morenomjc.transit.staticgtfs.core.trip;

import java.util.List;

public interface TripService {
    Trip getTrip(String tripId);
    List<Trip> getTripsByRouteId(String routeId);
    List<StopTime> getStops(String tripId);
}
