package com.morssscoding.transit.staticgtfs.api.spec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.Link;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApiResource<T> extends ApiDocument{
    private ApiData<T> data;

    public ApiResource(String type, T data, Link... links) {
        this.data = new ApiData<>(type, data, links);
    }

}
