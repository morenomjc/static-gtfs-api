package com.phakk.transit.staticgtfs.dataproviders.repository.stop;

import com.phakk.transit.staticgtfs.core.constants.StopTypeEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibilityEnum;
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
    default StopTypeEnum convertToStopType(String stopType){
        Optional<StopTypeEnum> stopTypeEnum = Arrays.stream(StopTypeEnum.values())
                .filter(s -> s.getId().equalsIgnoreCase(stopType))
                .findFirst();
        return stopTypeEnum.orElseThrow(() -> new ConstantsMappingException("Failed to map stop type"));
    }

    @Named("wheelchairBoarding")
    default WheelchairAccessibilityEnum convertToWheelchairBoarding(String wheelchairBoarding){
        Optional<WheelchairAccessibilityEnum> wbEnum = Arrays.stream(WheelchairAccessibilityEnum.values())
                .filter(wb -> wb.getId().equalsIgnoreCase(wheelchairBoarding))
                .findFirst();
        return wbEnum.orElseThrow(() -> new ConstantsMappingException("Failed to map wheelchair boarding details"));
    }
}
