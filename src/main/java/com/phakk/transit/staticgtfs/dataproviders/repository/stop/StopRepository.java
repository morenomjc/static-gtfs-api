package com.phakk.transit.staticgtfs.dataproviders.repository.stop;

import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.dataproviders.repository.Repository;

public interface StopRepository extends Repository<Stop> {
    Stop getStop(String id);
}
