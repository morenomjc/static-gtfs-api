package com.phakk.transit.staticgtfs.datastore.repository.agency;

import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.core.agency.AgencyNotFoundException;
import com.phakk.transit.staticgtfs.datastore.entity.jpa.AgencyEntity;
import com.phakk.transit.staticgtfs.datastore.repository.jpa.AgencyJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class AgencyRepositoryImpl implements AgencyRepository{

    private AgencyJpaRepository agencyJpaRepository;

    public AgencyRepositoryImpl(AgencyJpaRepository agencyJpaRepository) {
        this.agencyJpaRepository = agencyJpaRepository;
    }

    @Override
    public Agency getAgency(String id) {
        AgencyEntity agencyEntity = agencyJpaRepository.findByAgencyId(id);

        if (Objects.isNull(agencyEntity)){
            throw new AgencyNotFoundException("Agency not found.");
        }

        return fromEntity(agencyEntity);
    }

    private Agency fromEntity(AgencyEntity agencyEntity){
        return Agency.builder()
                .id(agencyEntity.getAgencyId())
                .name(agencyEntity.getName())
                .url(agencyEntity.getUrl())
                .timezone(agencyEntity.getTimezone())
                .lang(agencyEntity.getLang())
                .phone(agencyEntity.getPhone())
                .email(agencyEntity.getEmail())
                .fareUrl(agencyEntity.getFareUrl())
                .build();
    }
}
