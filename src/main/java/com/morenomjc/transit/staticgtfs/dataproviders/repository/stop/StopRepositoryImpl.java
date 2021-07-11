package com.morenomjc.transit.staticgtfs.dataproviders.repository.stop;

import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.core.stop.Stop;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.StopJpaRepository;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class StopRepositoryImpl implements StopRepository{

    private final StopJpaRepository stopJpaRepository;
    private final EnumValueRepository enumValueRepository;
    private final StopEntityMapper stopEntityMapper;

    @Override
    public Stop getStop(String id) {
        StopEntity stopEntity = stopJpaRepository.findByStopId(id);
        if (Objects.isNull(stopEntity)){
            throw new DataNotFoundException("Stop not found.");
        }

        EnumValue stopType = enumValueRepository.findEnumValue(Stop.TYPE, Stop.Fields.STOP_TYPE.getValue(), stopEntity.getStopType());
        EnumValue wheelchairBoarding = enumValueRepository.findEnumValue(Stop.TYPE, Stop.Fields.WHEELCHAIR_BOARDING.getValue(), stopEntity.getWheelchairBoarding());

        Stop stop = stopEntityMapper.fromEntity(stopEntity);
        stop.setType(stopType);
        stop.setWheelchairBoarding(wheelchairBoarding);

        return stop;
    }

    @Override
    public void save(Stop data) {
        StopEntity stopEntity = stopEntityMapper.toEntity(data);
        stopJpaRepository.save(stopEntity);
    }
}
