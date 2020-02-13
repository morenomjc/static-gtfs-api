package com.phakk.transit.staticgtfs.dataproviders.repository.stop;

import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.StopJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class StopRepositoryImpl implements StopRepository{

    private StopJpaRepository stopJpaRepository;
    private StopEntityMapper stopEntityMapper;

    public StopRepositoryImpl(StopJpaRepository stopJpaRepository, StopEntityMapper stopEntityMapper) {
        this.stopJpaRepository = stopJpaRepository;
        this.stopEntityMapper = stopEntityMapper;
    }

    @Override
    public Stop getStop(String id) {
        StopEntity stopEntity = stopJpaRepository.findByStopId(id);

        if (Objects.isNull(stopEntity)){
            throw new DataNotFoundException("Stop not found.");
        }

        return stopEntityMapper.fromEntity(stopEntity);
    }


}
