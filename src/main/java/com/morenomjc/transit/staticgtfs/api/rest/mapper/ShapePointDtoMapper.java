package com.morenomjc.transit.staticgtfs.api.rest.mapper;

import com.morenomjc.transit.staticgtfs.api.rest.dto.ShapeDto;
import com.morenomjc.transit.staticgtfs.core.shape.Shape;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShapePointDtoMapper {
    ShapeDto.PointDto toDto(Shape shape);
}
