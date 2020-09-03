package com.phakk.transit.staticgtfs.api.spec;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApiResources<T> extends ApiDocument{
    private List<T> data;
    private long count;
    private Link[] links;

    public ApiResources(List<T> data, long count, Link... links) {
        this.data = data;
        this.count = count;
        this.links = links;
    }
}
