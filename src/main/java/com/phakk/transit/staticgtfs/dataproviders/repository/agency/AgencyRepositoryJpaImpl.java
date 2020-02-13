package com.phakk.transit.staticgtfs.dataproviders.repository.agency;

import com.phakk.transit.staticgtfs.core.agency.Agency;
import com.phakk.transit.staticgtfs.core.exception.DataNotFoundException;
import com.phakk.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import com.phakk.transit.staticgtfs.dataproviders.jpa.repository.AgencyJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class AgencyRepositoryJpaImpl implements AgencyRepository{

    private AgencyJpaRepository agencyJpaRepository;
    private AgencyEntityMapper agencyEntityMapper;

    public AgencyRepositoryJpaImpl(AgencyJpaRepository agencyJpaRepository, AgencyEntityMapper agencyEntityMapper) {
        this.agencyJpaRepository = agencyJpaRepository;
        this.agencyEntityMapper = agencyEntityMapper;
    }

    @Override
    public List<Agency> getAgencies() {
        return agencyJpaRepository.findAll().stream()
                .map(agencyEntity -> agencyEntityMapper.fromEntity(agencyEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Agency getAgency(String id) {
        AgencyEntity agencyEntity = agencyJpaRepository.findByAgencyId(id);

        if (Objects.isNull(agencyEntity)){
            throw new DataNotFoundException("Agency not found.");
        }

        return agencyEntityMapper.fromEntity(agencyEntity);
    }

}
