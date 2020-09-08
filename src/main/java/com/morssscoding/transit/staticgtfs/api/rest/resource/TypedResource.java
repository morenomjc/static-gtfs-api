package com.morssscoding.transit.staticgtfs.api.rest.resource;

import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public interface TypedResource {
    String getResourceType();

    default Link selfLink(Class<?> controller){
        return linkTo(controller).withSelfRel();
    }

    default Link selfLink(String id, Class<?> controller){
        return linkTo(controller).slash(id).withSelfRel();
    }
}
