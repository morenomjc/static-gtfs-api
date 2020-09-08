package com.morssscoding.transit.staticgtfs.dataproviders.repository.stoptime;

import com.morssscoding.transit.staticgtfs.core.trip.StopTime;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository.StopTimeJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@AllArgsConstructor
public class StopTimeRepositoryImpl implements StopTimeRepository {

    private StopTimeJpaRepository repository;
    private StopTimeEntityMapper mapper;

    @Override
    public void save(StopTime data) {
        repository.save(mapper.toEntity(data));
    }
}
