package com.phakk.transit.staticgtfs.dataproviders.repository.agency;

import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AgencyEntityMapper {

    @Mapping(source = "agencyId", target = "id")
    Agency fromEntity(AgencyEntity agencyEntity);

}
