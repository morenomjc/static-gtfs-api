package com.morenomjc.transit.staticgtfs.dataproviders.repository.stoptime;

import com.morenomjc.transit.staticgtfs.core.trip.StopTime;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.StopTimeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class StopTimeRepositoryImpl implements StopTimeRepository {

    private final StopTimeJpaRepository repository;
    private final StopTimeEntityMapper mapper;

    @Override
    public void save(StopTime data) {
        repository.save(mapper.toEntity(data));
    }
}
