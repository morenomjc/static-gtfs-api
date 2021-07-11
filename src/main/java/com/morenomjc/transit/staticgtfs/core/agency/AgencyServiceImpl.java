package com.morenomjc.transit.staticgtfs.core.agency;

import com.morenomjc.transit.staticgtfs.dataproviders.repository.agency.AgencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService{

    private final AgencyRepository agencyRepository;

    @Override
    public List<Agency> getAgencies() {
        List<Agency> agencies = agencyRepository.getAgencies();
        log.info("Found agencies: [{}]", agencies.size());
        return agencies;
    }

    @Override
    public Agency getAgency(String id){
        Agency agency = agencyRepository.getAgency(id);
        log.info("Found agency with id: [{}]", agency.getId());
        return agency;
    }
}
