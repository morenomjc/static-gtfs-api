package com.morssscoding.transit.staticgtfs.dataproviders.repository.stop;

import com.morssscoding.transit.staticgtfs.core.stop.Stop;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.Repository;

public interface StopRepository extends Repository<Stop> {
    Stop getStop(String id);
}
