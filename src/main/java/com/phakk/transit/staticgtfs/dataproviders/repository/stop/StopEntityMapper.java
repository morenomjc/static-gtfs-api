package com.phakk.transit.staticgtfs.dataproviders.repository.stop;

import com.phakk.transit.staticgtfs.core.constants.StopType;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibility;
import com.phakk.transit.staticgtfs.core.exception.ConstantsMappingException;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.StopEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.Optional;

@Mapper
public interface StopEntityMapper {

    @Mapping(target = "id", source = "stopId")
    @Mapping(target = "code", source = "stopCode")
    @Mapping(target = "type", source = "stopType", qualifiedByName = "stopType")
    @Mapping(target = "wheelchairBoarding", source = "wheelchairBoarding", qualifiedByName = "wheelchairBoarding")
    Stop fromEntity(StopEntity stopEntity);

    @Named("stopType")
    default StopType convertToStopType(String stopType){
        Optional<StopType> stopTypeEnum = Arrays.stream(StopType.values())
                .filter(s -> s.getCode().equalsIgnoreCase(stopType))
                .findFirst();
        return stopTypeEnum.orElseThrow(() -> new ConstantsMappingException("Failed to map stop type"));
    }

    @Named("wheelchairBoarding")
    default WheelchairAccessibility convertToWheelchairBoarding(String wheelchairBoarding){
        Optional<WheelchairAccessibility> wbEnum = Arrays.stream(WheelchairAccessibility.values())
                .filter(wb -> wb.getCode().equalsIgnoreCase(wheelchairBoarding))
                .findFirst();
        return wbEnum.orElseThrow(() -> new ConstantsMappingException("Failed to map wheelchair boarding details"));
    }
}
