package com.morssscoding.transit.staticgtfs.dataproviders.repository.stop;

import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapper;
import com.morssscoding.transit.staticgtfs.batch.model.GtfsStop;
import com.morssscoding.transit.staticgtfs.core.stop.Stop;
import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = { EnumValueEntityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StopEntityMapper {

    @Mapping(target = "id", source = "stopId")
    @Mapping(target = "code", source = "stopCode")
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "wheelchairBoarding", ignore = true)
    Stop fromEntity(StopEntity stopEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stopId", source = "id")
    @Mapping(target = "stopCode", source = "code")
    @Mapping(target = "stopType", source = "type")
    StopEntity toEntity(Stop stop);

    @Mapping(target = "id", source = "stop_id")
    @Mapping(target = "code", source = "stop_code")
    @Mapping(target = "name", source = "stop_name")
    @Mapping(target = "desc", source = "stop_desc")
    @Mapping(target = "lat", source = "stop_lat")
    @Mapping(target = "lon", source = "stop_lon")
    @Mapping(target = "zoneId", source = "zone_id")
    @Mapping(target = "url", source = "stop_url")
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "parentStation", source = "parent_station")
    @Mapping(target = "timezone", source = "stop_timezone")
    @Mapping(target = "wheelchairBoarding", ignore = true)
    Stop convert(GtfsStop gtfsStop);
}
