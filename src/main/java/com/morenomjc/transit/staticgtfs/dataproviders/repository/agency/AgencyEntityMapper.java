package com.morenomjc.transit.staticgtfs.dataproviders.repository.agency;

import com.morenomjc.transit.staticgtfs.batch.model.GtfsAgency;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import com.morenomjc.transit.staticgtfs.core.agency.Agency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AgencyEntityMapper {

    @Mapping(source = "agencyId", target = "id")
    Agency fromEntity(AgencyEntity agencyEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "agencyId")
    AgencyEntity toEntity(Agency agency);

    @Mapping(source = "agency_id", target = "id")
    @Mapping(source = "agency_name", target = "name")
    @Mapping(source = "agency_url", target = "url")
    @Mapping(source = "agency_timezone", target = "timezone")
    @Mapping(source = "agency_lang", target = "lang")
    @Mapping(source = "agency_phone", target = "phone")
    @Mapping(source = "agency_fare_url", target = "fareUrl")
    @Mapping(source = "agency_email", target = "email")
    Agency convert(GtfsAgency gtfsAgency);
}
