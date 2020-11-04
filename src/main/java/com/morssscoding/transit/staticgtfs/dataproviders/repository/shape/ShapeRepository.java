package com.morssscoding.transit.staticgtfs.dataproviders.repository.shape;

import com.morssscoding.transit.staticgtfs.core.shape.Shape;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.Repository;

import java.util.List;

public interface ShapeRepository extends Repository<Shape> {
    List<Shape> getShapesById(String shapeId);
}
