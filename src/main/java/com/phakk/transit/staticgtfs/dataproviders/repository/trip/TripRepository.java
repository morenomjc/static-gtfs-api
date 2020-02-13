package com.phakk.transit.staticgtfs.dataproviders.repository.trip;

import com.phakk.transit.staticgtfs.core.trip.Trip;

public interface TripRepository {
    Trip getTrip(String id);
}
