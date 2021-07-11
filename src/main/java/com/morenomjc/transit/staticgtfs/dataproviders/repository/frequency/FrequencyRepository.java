package com.morenomjc.transit.staticgtfs.dataproviders.repository.frequency;

import com.morenomjc.transit.staticgtfs.core.frequency.Frequency;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.Repository;

public interface FrequencyRepository extends Repository<Frequency> {
    Frequency getFrequency(String tripId);
}
