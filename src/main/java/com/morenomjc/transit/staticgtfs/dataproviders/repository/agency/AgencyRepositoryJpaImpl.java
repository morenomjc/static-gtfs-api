package com.morenomjc.transit.staticgtfs.dataproviders.repository.agency;

import com.morenomjc.transit.staticgtfs.core.agency.Agency;
import com.morenomjc.transit.staticgtfs.core.exception.DataNotFoundException;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.AgencyEntity;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.AgencyJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class AgencyRepositoryJpaImpl implements AgencyRepository{

    private final AgencyJpaRepository agencyJpaRepository;
    private final AgencyEntityMapper agencyEntityMapper;

    @Override
    public List<Agency> getAgencies() {
        return agencyJpaRepository.findAll().stream()
                .map(agencyEntityMapper::fromEntity)
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

    @Override
    public void save(Agency data) {
        AgencyEntity agencyEntity = agencyEntityMapper.toEntity(data);
        agencyJpaRepository.save(agencyEntity);
    }
}
