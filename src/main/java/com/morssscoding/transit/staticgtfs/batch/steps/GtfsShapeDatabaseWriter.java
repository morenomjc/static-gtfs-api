package com.morssscoding.transit.staticgtfs.batch.steps;

import com.morssscoding.transit.staticgtfs.batch.model.GtfsShape;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class GtfsShapeDatabaseWriter implements ItemWriter<GtfsShape> {

    private ShapeRepository repository;
    private ShapeEntityMapper mapper;

    @Override
    public void write(List<? extends GtfsShape> items) throws Exception {
        log.info("[GtfsShapeDatabaseWriter].write={}", items.size());
        items.forEach(shape -> repository.save(mapper.convert(shape)));
    }

}
