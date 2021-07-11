package com.morenomjc.transit.staticgtfs.dataproviders.repository.shape;

import com.morenomjc.transit.staticgtfs.dataproviders.jpa.repository.ShapeJpaRepository;
import com.morenomjc.transit.staticgtfs.core.shape.Shape;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@Transactional
@AllArgsConstructor
public class ShapeRepositoryImpl implements ShapeRepository {

    private ShapeJpaRepository shapeJpaRepository;
    private ShapeEntityMapper shapeEntityMapper;

    @Override
    public List<Shape> getShapesById(String shapeId) {
        return shapeJpaRepository.findByShapeId(shapeId).stream()
                .map(shapeEntityMapper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void save(Shape data) {
        shapeJpaRepository.save(shapeEntityMapper.toEntity(data));
    }
}
