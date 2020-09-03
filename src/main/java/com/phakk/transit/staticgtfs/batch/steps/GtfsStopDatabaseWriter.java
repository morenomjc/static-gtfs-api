package com.phakk.transit.staticgtfs.batch.steps;

import com.phakk.transit.staticgtfs.batch.model.GtfsStop;
import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import com.phakk.transit.staticgtfs.core.stop.Stop;
import com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.stop.StopEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.stop.StopRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class GtfsStopDatabaseWriter implements ItemWriter<GtfsStop> {

    private StopRepository repository;
    private StopEntityMapper mapper;
    private EnumValueRepository enumValueRepository;

    @Override
    public void write(List<? extends GtfsStop> items) throws Exception {
        log.info("[GtfsStopDatabaseWriter].write={}", items.size());
        items.forEach(item -> {
            log.info("location_type={}", item.getLocation_type());
            Stop stop = mapper.convert(item);
            EnumValue stopType = enumValueRepository.findEnumValue(Stop.TYPE, Stop.Fields.STOP_TYPE.getValue(), String.valueOf(item.getLocation_type()));
            EnumValue wheelchairBoarding = enumValueRepository.findEnumValue(Stop.TYPE, Stop.Fields.WHEELCHAIR_BOARDING.getValue(), String.valueOf(item.getWheelchair_boarding()));
            stop.setType(stopType);
            stop.setWheelchairBoarding(wheelchairBoarding);
            repository.save(stop);
        });
    }

}
