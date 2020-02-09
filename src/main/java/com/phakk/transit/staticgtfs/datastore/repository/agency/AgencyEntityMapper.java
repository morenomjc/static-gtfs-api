package com.phakk.transit.staticgtfs.datastore.repository.agency;

import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.datastore.jpa.entity.AgencyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AgencyEntityMapper {

    @Mapping(source = "agencyId", target = "id")
    Agency fromEntity(AgencyEntity agencyEntity);

    @Mapping(source = "id", target = "agencyId")
    AgencyEntity toEntity(Agency agency);
}
