package com.morssscoding.transit.staticgtfs.dataproviders.repository.frequency;

import com.morssscoding.transit.staticgtfs.core.frequency.Frequency;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.Repository;

public interface FrequencyRepository extends Repository<Frequency> {
    Frequency getFrequency(String tripId);
}
