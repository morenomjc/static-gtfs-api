package com.morenomjc.transit.staticgtfs.dataproviders.repository.trip;

import com.morenomjc.transit.staticgtfs.core.trip.StopTime;
import com.morenomjc.transit.staticgtfs.core.trip.Trip;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.Repository;

import java.util.List;

public interface TripRepository extends Repository<Trip> {
    Trip getTrip(String id);
    List<Trip> getTripsByRouteId(String routeId);
    List<StopTime> getStops(String tripId);
}
