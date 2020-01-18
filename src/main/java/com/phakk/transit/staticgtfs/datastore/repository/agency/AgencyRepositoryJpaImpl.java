package com.phakk.transit.staticgtfs.datastore.repository.agency;

import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.core.agency.AgencyNotFoundException;
import com.phakk.transit.staticgtfs.datastore.entity.jpa.AgencyEntity;
import com.phakk.transit.staticgtfs.datastore.repository.jpa.AgencyJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class AgencyRepositoryJpaImpl implements AgencyRepository{

    private AgencyJpaRepository agencyJpaRepository;

    public AgencyRepositoryJpaImpl(AgencyJpaRepository agencyJpaRepository) {
        this.agencyJpaRepository = agencyJpaRepository;
    }

    @Override
    public List<Agency> getAgencies() {
        return agencyJpaRepository.findAll().stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
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
