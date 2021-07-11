package com.morenomjc.transit.staticgtfs.dataproviders.repository.trip;

import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.core.trip.StopTime;
import com.morenomjc.transit.staticgtfs.core.trip.Trip;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.StopTimeJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.stoptime.StopTimeEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TripRepositoryImpl implements TripRepository {

    private final TripJpaRepository tripJpaRepository;
    private final StopTimeJpaRepository stopTimeJpaRepository;
    private final EnumValueRepository enumValueRepository;
    private final TripEntityMapper tripEntityMapper;
    private final StopTimeEntityMapper stopTimeEntityMapper;

    @Override
    public Trip getTrip(String id) {
        TripEntity tripEntity = tripJpaRepository.findByTripId(id);
        if (Objects.isNull(tripEntity)){
            throw new DataNotFoundException("Trip not found.");
        }
        return buildTrip(tripEntity);
    }

    @Override
    public List<Trip> getTripsByRouteId(String routeId) {
        List<TripEntity> tripEntities = tripJpaRepository.findAllByRouteId(routeId);
        return tripEntities.stream().map(this::buildTrip).collect(Collectors.toList());
    }

    @Override
    public List<StopTime> getStops(String tripId) {
        List<StopTimeEntity> stopTimeEntities = stopTimeJpaRepository.findAllByTripId(tripId);
        return stopTimeEntities.stream()
                .map(this::buildStopTime)
                .sorted(Comparator.comparing(StopTime::getStopSequence))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Trip data) {
        tripJpaRepository.save(tripEntityMapper.toEntity(data));
    }

    private Trip buildTrip(TripEntity tripEntity){
        EnumValue direction = enumValueRepository.findEnumValue(Trip.TYPE, Trip.Fields.DIRECTION_ID.getValue(), tripEntity.getDirectionId());
        EnumValue wheelchairAccessible = enumValueRepository.findEnumValue(Trip.TYPE, Trip.Fields.WHEELCHAIR_ACCESSIBLE.getValue(), tripEntity.getWheelchairAccessible());
        EnumValue bikesAllowed = enumValueRepository.findEnumValue(Trip.TYPE, Trip.Fields.BIKES_ALLOWED.getValue(), tripEntity.getBikesAllowed());

        Trip trip = tripEntityMapper.fromEntity(tripEntity);
        trip.setDirectionId(direction);
        trip.setWheelchairAccessible(wheelchairAccessible);
        trip.setBikesAllowed(bikesAllowed);

        return trip;
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
