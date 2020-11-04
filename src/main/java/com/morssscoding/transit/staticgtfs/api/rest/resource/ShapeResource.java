package com.morssscoding.transit.staticgtfs.api.rest.resource;


import com.morssscoding.transit.staticgtfs.api.spec.ApiDocument;
import com.morssscoding.transit.staticgtfs.core.constants.DataTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/shapes")
public interface ShapeResource extends TypedResource{

    @GetMapping(value = "/{shapeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiDocument> getShape(@PathVariable(name = "shapeId") String shapeId);

    @Override
    default String getResourceType(){
        return DataTypes.SHAPE.getValue();
    }
}
