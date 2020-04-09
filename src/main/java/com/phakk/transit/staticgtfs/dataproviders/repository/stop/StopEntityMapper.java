package com.phakk.transit.staticgtfs.dataproviders.repository.stop;

import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StopEntityMapper {

    @Mapping(target = "id", source = "stopId")
    @Mapping(target = "code", source = "stopCode")
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "wheelchairBoarding", ignore = true)
    Stop fromEntity(StopEntity stopEntity);
}
