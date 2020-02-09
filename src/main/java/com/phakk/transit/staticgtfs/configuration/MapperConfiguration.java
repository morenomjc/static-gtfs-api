package com.phakk.transit.staticgtfs.configuration;

import com.phakk.transit.staticgtfs.api.rest.mapper.AgencyDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.mapper.RouteDtoMapper;
import com.phakk.transit.staticgtfs.api.rest.mapper.StopDtoMapper;
import com.phakk.transit.staticgtfs.datastore.repository.agency.AgencyEntityMapper;
import com.phakk.transit.staticgtfs.datastore.repository.route.RouteEntityMapper;
import com.phakk.transit.staticgtfs.datastore.repository.stop.StopEntityMapper;
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
}
