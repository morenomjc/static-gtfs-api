package com.morenomjc.transit.staticgtfs.core.stop;

import com.morenomjc.transit.staticgtfs.dataproviders.repository.stop.StopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StopServiceImpl implements StopService{

    private StopRepository stopRepository;

    public StopServiceImpl(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    @Override
    public Stop getStop(String id) {
        Stop stop = stopRepository.getStop(id);
        log.info("Found stop with id: [{}]", stop.getId());
        return stop;
    }
}
