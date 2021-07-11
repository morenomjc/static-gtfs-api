package com.morenomjc.transit.staticgtfs.dataproviders.repository.shape;

import com.morenomjc.transit.staticgtfs.batch.model.GtfsShape;
import com.morenomjc.transit.staticgtfs.core.shape.Shape;
import com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity.ShapeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShapeEntityMapper {

    @Mapping(target = "id", source = "shapeId")
    Shape fromEntity(ShapeEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shapeId", source = "id")
    ShapeEntity toEntity(Shape shape);

    @Mapping(target = "id", source = "shape_id")
    @Mapping(target = "lat", source = "shape_pt_lat")
    @Mapping(target = "lon", source = "shape_pt_lon")
    @Mapping(target = "sequence", source = "shape_pt_sequence")
    @Mapping(target = "distanceTraveled", source = "shape_dist_traveled")
    Shape convert(GtfsShape shape);


}
