package com.morenomjc.transit.staticgtfs.dataproviders.repository.shape;

import com.morenomjc.transit.staticgtfs.core.shape.Shape;
import com.morenomjc.transit.staticgtfs.dataproviders.repository.Repository;

import java.util.List;

public interface ShapeRepository extends Repository<Shape> {
    List<Shape> getShapesById(String shapeId);
}
