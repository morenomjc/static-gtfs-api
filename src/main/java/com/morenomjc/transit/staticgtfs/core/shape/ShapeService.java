package com.morenomjc.transit.staticgtfs.core.shape;

import java.util.List;

public interface ShapeService {
    List<Shape> getShapePoints(String shapeId);
}
