package com.phakk.transit.staticgtfs.dataproviders.repository.stop;

import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.StopJpaRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@AllArgsConstructor
@Repository
public class StopRepositoryImpl implements StopRepository{

    private StopJpaRepository stopJpaRepository;
    private StopEntityMapper stopEntityMapper;
    private EnumValueRepository enumValueRepository;

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

}
