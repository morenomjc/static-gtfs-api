package com.phakk.transit.staticgtfs.dataproviders.repository.trip;

import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.trip.StopTime;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopTimeEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.StopTimeJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
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

    @Override
    public Trip getTrip(String id) {
        TripEntity tripEntity = tripJpaRepository.findByTripId(id);

        if (Objects.isNull(tripEntity)){
            throw new DataNotFoundException("Trip not found.");
        }

        return tripEntityMapper.fromEntity(tripEntity);
    }

    @Override
    public List<StopTime> getStops(String tripId) {
        List<StopTimeEntity> stopTimeEntities = stopTimeJpaRepository.findAllByTripId(tripId);
        return stopTimeEntities.stream()
                .map(stopTimeEntity -> stopTimeEntityMapper.fromEntity(stopTimeEntity))
                .collect(Collectors.toList());
    }
}
