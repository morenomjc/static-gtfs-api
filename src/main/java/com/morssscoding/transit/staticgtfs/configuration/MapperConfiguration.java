package com.morssscoding.transit.staticgtfs.configuration;

import com.morssscoding.transit.staticgtfs.api.rest.mapper.AgencyDtoMapper;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.CalendarDtoMapper;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.FrequencyDtoMapper;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.RouteDtoMapper;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.ShapePointDtoMapper;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.StopDtoMapper;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.StopTimeDtoMapper;
import com.morssscoding.transit.staticgtfs.api.rest.mapper.TripDtoMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.agency.AgencyEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.calendar.CalendarEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.frequency.FrequencyEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.route.RouteEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.stop.StopEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.stoptime.StopTimeEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.trip.TripEntityMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public AgencyDtoMapper agencyDtoMapper(){
        return Mappers.getMapper(AgencyDtoMapper.class);
    }

    @Bean
    public AgencyEntityMapper agencyEntityMapper(){
        return Mappers.getMapper(AgencyEntityMapper.class);
    }

    @Bean
    public StopDtoMapper stopDtoMapper(){
        return Mappers.getMapper(StopDtoMapper.class);
    }

    @Bean
    public StopEntityMapper stopEntityMapper(){
        return Mappers.getMapper(StopEntityMapper.class);
    }

    @Bean
    public RouteDtoMapper routeDtoMapper(){
        return Mappers.getMapper(RouteDtoMapper.class);
    }

    @Bean
    public RouteEntityMapper routeEntityMapper(){
        return Mappers.getMapper(RouteEntityMapper.class);
    }

    @Bean
    public TripDtoMapper tripDtoMapper(){
        return Mappers.getMapper(TripDtoMapper.class);
    }

    @Bean
    public TripEntityMapper tripEntityMapper(){
        return Mappers.getMapper(TripEntityMapper.class);
    }

    @Bean
    public CalendarDtoMapper calendarDtoMapper(){
        return Mappers.getMapper(CalendarDtoMapper.class);
    }

    @Bean
    public CalendarEntityMapper calendarEntityMapper(){
        return Mappers.getMapper(CalendarEntityMapper.class);
    }

    @Bean
    public ShapePointDtoMapper shapeDtoMapper(){
        return Mappers.getMapper(ShapePointDtoMapper.class);
    }

    @Bean
    public ShapeEntityMapper shapeEntityMapper(){
        return Mappers.getMapper(ShapeEntityMapper.class);
    }

    @Bean
    public StopTimeDtoMapper stopTimeDtoMapper(){
        return Mappers.getMapper(StopTimeDtoMapper.class);
    }

    @Bean
    public StopTimeEntityMapper stopTimeEntityMapper(){
        return Mappers.getMapper(StopTimeEntityMapper.class);
    }

    @Bean
    public FrequencyEntityMapper frequencyEntityMapper(){
        return Mappers.getMapper(FrequencyEntityMapper.class);
    }

    @Bean
    public FrequencyDtoMapper frequencyDtoMapper(){
        return Mappers.getMapper(FrequencyDtoMapper.class);
    }

    @Bean
    public EnumValueEntityMapper enumValueEntityMapper(){
        return Mappers.getMapper(EnumValueEntityMapper.class);
    }


}
