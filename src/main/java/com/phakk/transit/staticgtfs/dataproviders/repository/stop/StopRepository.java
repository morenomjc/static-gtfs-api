package com.phakk.transit.staticgtfs.dataproviders.repository.stop;

import com.phakk.transit.staticgtfs.core.stop.Stop;

public interface StopRepository {
    Stop getStop(String id);
}
