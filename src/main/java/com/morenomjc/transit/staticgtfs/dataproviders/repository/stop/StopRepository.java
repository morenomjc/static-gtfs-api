package com.morenomjc.transit.staticgtfs.dataproviders.repository.stop;

import com.morenomjc.transit.staticgtfs.core.stop.Stop;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.Repository;

public interface StopRepository extends Repository<Stop> {
    Stop getStop(String id);
}
