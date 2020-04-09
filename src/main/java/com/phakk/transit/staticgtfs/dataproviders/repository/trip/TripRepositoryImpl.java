package com.phakk.transit.staticgtfs.dataproviders.repository.trip;

import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.trip.StopTime;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.StopTimeJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Repository
@AllArgsConstructor
public class TripRepositoryImpl implements TripRepository {

    private TripJpaRepository tripJpaRepository;
    private TripEntityMapper tripEntityMapper;
    private StopTimeJpaRepository stopTimeJpaRepository;
    private StopTimeEntityMapper stopTimeEntityMapper;
    private EnumValueRepository enumValueRepository;

    @Override
    public Trip getTrip(String id) {
        TripEntity tripEntity = tripJpaRepository.findByTripId(id);
        if (Objects.isNull(tripEntity)){
            throw new DataNotFoundException("Trip not found.");
        }

        EnumValue direction = enumValueRepository.findEnumValue(Trip.TYPE, Trip.Fields.DIRECTION_ID.getValue(), tripEntity.getDirectionId());
        EnumValue wheelchairAccessible = enumValueRepository.findEnumValue(Trip.TYPE, Trip.Fields.WHEELCHAIR_ACCESSIBLE.getValue(), tripEntity.getWheelchairAccessible());
        EnumValue bikesAllowed = enumValueRepository.findEnumValue(Trip.TYPE, Trip.Fields.BIKES_ALLOWED.getValue(), tripEntity.getBikesAllowed());

        Trip trip = tripEntityMapper.fromEntity(tripEntity);
        trip.setDirectionId(direction);
        trip.setWheelchairAccessible(wheelchairAccessible);
        trip.setBikesAllowed(bikesAllowed);

        return trip;
    }

    @Override
    public List<StopTime> getStops(String tripId) {
        List<StopTimeEntity> stopTimeEntities = stopTimeJpaRepository.findAllByTripId(tripId);
        return stopTimeEntities.stream()
                .map(this::buildStopTime)
                .collect(Collectors.toList());
    }

    private StopTime buildStopTime(StopTimeEntity stopTimeEntity){
        StopTime stopTime = stopTimeEntityMapper.fromEntity(stopTimeEntity);

        EnumValue pickupType = enumValueRepository.findEnumValue(StopTime.TYPE, StopTime.Fields.PICKUP_TYPE.getValue(), stopTimeEntity.getPickupType());
        EnumValue dropOffType = enumValueRepository.findEnumValue(StopTime.TYPE, StopTime.Fields.DROP_OFF_TYPE.getValue(), stopTimeEntity.getDropOffType());
        EnumValue timepoint = enumValueRepository.findEnumValue(StopTime.TYPE, StopTime.Fields.TIMEPOINT.getValue(), stopTimeEntity.getTimepoint());

        stopTime.setPickupType(pickupType);
        stopTime.setDropOffType(dropOffType);
        stopTime.setTimepoint(timepoint);

        return stopTime;
    }
}
