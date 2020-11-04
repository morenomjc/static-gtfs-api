package com.morssscoding.transit.staticgtfs.api.rest.mapper;

import com.morssscoding.transit.staticgtfs.api.rest.dto.ShapeDto;
import com.morssscoding.transit.staticgtfs.core.shape.Shape;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShapePointDtoMapper {
    ShapeDto.PointDto toDto(Shape shape);
}
