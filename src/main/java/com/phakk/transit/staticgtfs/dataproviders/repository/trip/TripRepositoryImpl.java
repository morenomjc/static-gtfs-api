package com.phakk.transit.staticgtfs.dataproviders.repository.trip;

import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.trip.Trip;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.TripEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.TripJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Slf4j
@Repository
@AllArgsConstructor
public class TripRepositoryImpl implements TripRepository {

    private TripJpaRepository tripJpaRepository;
    private TripEntityMapper tripEntityMapper;

    @Override
    public Trip getTrip(String id) {
        TripEntity tripEntity = tripJpaRepository.findByTripId(id);

        if (Objects.isNull(tripEntity)){
            throw new DataNotFoundException("Trip not found.");
        }

        return tripEntityMapper.fromEntity(tripEntity);
    }
}
