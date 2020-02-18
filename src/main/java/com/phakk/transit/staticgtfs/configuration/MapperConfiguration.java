package com.phakk.transit.staticgtfs.configuration;

import com.phakk.transit.staticgtfs.api.rest.mapper.AgencyDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.mapper.CalendarDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.mapper.RouteDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.mapper.StopDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.mapper.StopTimeDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.mapper.TripDtoMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.agency.AgencyEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.calendar.CalendarEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.route.RouteEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.stop.StopEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.trip.StopTimeEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.trip.TripEntityMapper;
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
    public StopTimeDtoMapper stopTimeDtoMapper(){
        return Mappers.getMapper(StopTimeDtoMapper.class);
    }


    @Bean
    public StopTimeEntityMapper stopTimeEntityMapper(){
        return Mappers.getMapper(StopTimeEntityMapper.class);
    }
}
