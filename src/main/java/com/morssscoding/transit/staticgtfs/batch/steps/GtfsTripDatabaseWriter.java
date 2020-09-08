package com.morssscoding.transit.staticgtfs.batch.steps;

import com.morssscoding.transit.staticgtfs.core.constants.EnumValue;
import com.morssscoding.transit.staticgtfs.core.trip.Trip;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.trip.TripEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.trip.TripRepository;
import com.morssscoding.transit.staticgtfs.batch.model.GtfsTrip;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class GtfsTripDatabaseWriter implements ItemWriter<GtfsTrip> {

    private TripRepository repository;
    private TripEntityMapper mapper;
    private EnumValueRepository enumValueRepository;

    @Override
    public void write(List<? extends GtfsTrip> items) throws Exception {
        log.info("[GtfsTripDatabaseWriter].write={}", items.size());
        items.forEach(item -> {
            Trip trip = mapper.convert(item);

            EnumValue directionId = enumValueRepository.findEnumValue(Trip.TYPE, Trip.Fields.DIRECTION_ID.getValue(), String.valueOf(item.getDirection_id()));
            EnumValue wheelchairAccessible = enumValueRepository.findEnumValue(Trip.TYPE, Trip.Fields.WHEELCHAIR_ACCESSIBLE.getValue(), String.valueOf(item.getWheelchair_accessible()));
            EnumValue bikesAllowed = enumValueRepository.findEnumValue(Trip.TYPE, Trip.Fields.BIKES_ALLOWED.getValue(), String.valueOf(item.getBikes_allowed()));

            trip.setDirectionId(directionId);
            trip.setWheelchairAccessible(wheelchairAccessible);
            trip.setBikesAllowed(bikesAllowed);

            repository.save(trip);
        });
    }

}
