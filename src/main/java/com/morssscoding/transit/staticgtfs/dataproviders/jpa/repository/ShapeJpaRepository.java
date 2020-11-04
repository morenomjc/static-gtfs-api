package com.morssscoding.transit.staticgtfs.dataproviders.jpa.repository;

import com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity.ShapeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShapeJpaRepository extends JpaRepository<ShapeEntity, Long> {
    List<ShapeEntity> findByShapeId(String shapeId);
}
