package com.phakk.transit.staticgtfs.datastore.repository.stop;

import com.phakk.transit.staticgtfs.core.constants.StopTypeEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairBoardingEnum;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.datastore.jpa.entity.StopEntity;
import com.phakk.transit.staticgtfs.datastore.jpa.repository.StopJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Repository
public class StopRepositoryImpl implements StopRepository{

    private StopJpaRepository stopJpaRepository;

    public StopRepositoryImpl(StopJpaRepository stopJpaRepository) {
        this.stopJpaRepository = stopJpaRepository;
    }

    @Override
    public Stop getStop(String id) {
        StopEntity stopEntity = stopJpaRepository.findByStopId(id);

        if (Objects.isNull(stopEntity)){
            throw new DataNotFoundException("Stop not found.");
        }

        return fromEntity(stopEntity);
    }

    private Stop fromEntity(StopEntity stopEntity){
        return Stop.builder()
                .id(stopEntity.getStopId())
                .code(stopEntity.getStopCode())
                .name(stopEntity.getName())
                .desc(stopEntity.getDesc())
                .lat(stopEntity.getLat())
                .lon(stopEntity.getLon())
                .zoneId(stopEntity.getZoneId())
                .url(stopEntity.getUrl())
                .type(convertToStopType(stopEntity.getStopType()))
                .parentStation(stopEntity.getParentStation())
                .timezone(stopEntity.getTimezone())
                .wheelchairBoarding(convertToWheelchairBoarding(stopEntity.getWheelchairBoarding()))
                .levelId(stopEntity.getLevelId())
                .platformCode(stopEntity.getPlatformCode())
                .build();
    }

    private StopTypeEnum convertToStopType(String id){
        Optional<StopTypeEnum> stopTypeEnum = Arrays.stream(StopTypeEnum.values())
                .filter(s -> s.getId().equalsIgnoreCase(id))
                .findFirst();
        return stopTypeEnum.orElse(null);
    }

    private WheelchairBoardingEnum convertToWheelchairBoarding(String id){
        Optional<WheelchairBoardingEnum> wbEnum = Arrays.stream(WheelchairBoardingEnum.values())
                .filter(wb -> wb.getId().equalsIgnoreCase(id))
                .findFirst();
        return wbEnum.orElse(null);
    }
}
