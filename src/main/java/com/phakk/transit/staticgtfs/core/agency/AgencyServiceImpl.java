package com.phakk.transit.staticgtfs.core.agency;

import com.phakk.transit.staticgtfs.datastore.repository.agency.AgencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AgencyServiceImpl implements AgencyService{

    private AgencyRepository agencyRepository;

    public AgencyServiceImpl(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public List<Agency> getAgencies() {
        List<Agency> agencies = agencyRepository.getAgencies();
        log.info("Result: {}", agencies.size());
        return agencies;
    }

    @Override
    public Agency getAgency(String id){
        return agencyRepository.getAgency(id);
    }
}
