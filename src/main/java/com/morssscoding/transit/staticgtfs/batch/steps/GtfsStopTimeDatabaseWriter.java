package com.morssscoding.transit.staticgtfs.batch.steps;

import com.morssscoding.transit.staticgtfs.batch.model.GtfsStopTime;
import com.morssscoding.transit.staticgtfs.core.constants.EnumValue;
import com.morssscoding.transit.staticgtfs.core.trip.StopTime;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.stoptime.StopTimeEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.stoptime.StopTimeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class GtfsStopTimeDatabaseWriter implements ItemWriter<GtfsStopTime> {

    private StopTimeRepository repository;
    private StopTimeEntityMapper mapper;
    private EnumValueRepository enumValueRepository;

    @Override
    public void write(List<? extends GtfsStopTime> items) throws Exception {
        log.info("[GtfsStopTimeDatabaseWriter].write={}", items.size());
        items.forEach(item -> {
            StopTime stopTime = mapper.convert(item);

            EnumValue pickupType = enumValueRepository.findEnumValue(
                    StopTime.TYPE, StopTime.Fields.PICKUP_TYPE.getValue(), String.valueOf(item.getPickup_type()));
            EnumValue dropOffType = enumValueRepository.findEnumValue(
                    StopTime.TYPE, StopTime.Fields.DROP_OFF_TYPE.getValue(), String.valueOf(item.getDrop_off_type()));
            EnumValue timepoint = enumValueRepository.findEnumValue(
                    StopTime.TYPE, StopTime.Fields.TIMEPOINT.getValue(), String.valueOf(item.getTimepoint()));

            stopTime.setPickupType(pickupType);
            stopTime.setDropOffType(dropOffType);
            stopTime.setTimepoint(timepoint);

            repository.save(stopTime);
        });
    }

}
