package com.morssscoding.transit.staticgtfs.core.shape;

import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ShapeServiceImpl implements ShapeService {

    private ShapeRepository shapeRepository;

    @Override
    public List<Shape> getShapePoints(String shapeId) {
        List<Shape> shapes = shapeRepository.getShapesById(shapeId);
        log.info("Found shapes with id: [{}]", shapeId);
        return shapes;
    }
}
