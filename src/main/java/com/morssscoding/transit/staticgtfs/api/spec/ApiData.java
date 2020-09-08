package com.morssscoding.transit.staticgtfs.api.spec;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ApiData<T> extends RepresentationModel {
    private String type;
    private T attributes;
    private Map<String, Object> relationships;

    public ApiData(String type, T attributes, Link... links) {
        this.type = type;
        this.attributes = attributes;
        this.relationships = new HashMap<>();

        add(links);
    }

    public void addRelationship(String rel, Object data){
        this.relationships.put(rel, data);
    }

}
