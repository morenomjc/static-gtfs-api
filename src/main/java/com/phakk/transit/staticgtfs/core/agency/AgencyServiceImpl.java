package com.phakk.transit.staticgtfs.core.agency;

import com.phakk.transit.staticgtfs.datastore.repository.agency.AgencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AgencyServiceImpl implements AgencyService{

    private AgencyRepository agencyRepository;

    public AgencyServiceImpl(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public Agency getAgency(String id){
        try {
            return agencyRepository.getAgency(id);
        }catch (Exception e){
            log.error("Exception occurred: {}", e.getLocalizedMessage());
            throw e;
        }
    }
}
