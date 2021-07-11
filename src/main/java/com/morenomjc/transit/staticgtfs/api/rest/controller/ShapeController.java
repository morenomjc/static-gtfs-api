package com.morenomjc.transit.staticgtfs.api.rest.controller;

import com.morenomjc.transit.staticgtfs.api.rest.mapper.ShapePointDtoMapper;
import com.morenomjc.transit.staticgtfs.api.rest.resource.ShapeResource;
import com.morenomjc.transit.staticgtfs.api.spec.ApiDocument;
import com.morenomjc.transit.staticgtfs.api.rest.dto.ShapeDto;
import com.morenomjc.transit.staticgtfs.api.spec.ApiResource;
import com.morenomjc.transit.staticgtfs.core.shape.ShapeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
public class ShapeController implements ShapeResource {

    private ShapeService shapeService;
    private ShapePointDtoMapper shapepointDtoMapper;

    @Override
    public ResponseEntity<ApiDocument> getShape(String shapeId) {
        List<ShapeDto.PointDto> points = shapeService.getShapePoints(shapeId).stream()
                .map(shapepointDtoMapper::toDto)
                .collect(Collectors.toList());

        ShapeDto shape = ShapeDto.builder()
                .id(shapeId)
                .points(points)
                .build();

        return ResponseEntity.ok(
                new ApiResource<>(
                        getResourceType(),
                        shape,
                        selfLink(shapeId, ShapeController.class)
                )
        );
    }
}
